package indi.haiying.jdbcs.service;

import indi.haiying.jdbcs.dao.JdbcBadDao;
import indi.haiying.jdbcs.dao.JdbcDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SiteService {

    private JdbcBadDao jdbcBadDao = new JdbcBadDao();

    private JdbcDao jdbcDao = new JdbcDao();

    public Site getSite0(Integer siteID) {

        Map<String, Object> object = jdbcBadDao.selectOne(
                "select siteID, siteName, creator, createTime from data_site where siteID = " + siteID);

        return this.toSite(object);
    }

    public Site getSite(Integer siteID) {

        Map<String, Object> object = jdbcDao.selectOne(
                "select siteID, siteName, creator, createTime from data_site where siteID = ?", siteID);

        return this.toSite(object);
    }

    public List<Site> getAllSites() {

        List<Map<String, Object>> objects = jdbcDao.select(
                "select siteID, siteName, creator, createTime from data_site");

        return this.toSites(objects);
    }

    public int updateSiteName(Integer siteID, String name) {

        return this.jdbcDao.update("update data_site set siteName = ? where siteID = ?", name, siteID);
    }

    public int[] updateSiteNames(List<Site> sites) {

        Object[][] params = new Object[sites.size()][2];
        for (int i = 0; i < sites.size(); i++) {
            params[i][0] = sites.get(i).getSiteName();
            params[i][1] =  sites.get(i).getSiteID();
        }

        return this.jdbcDao.updateBatch("update data_site set siteName = ? where siteID = ?", params);
    }

    private List<Site> toSites(List<Map<String, Object>> objects){

        List<Site> sites = new ArrayList<>();
        for (Map<String, Object> object : objects) {
            sites.add(this.toSite(object));
        }
        return sites;
    }

    private Site toSite(Map<String, Object> object){
        Site site = null;
        if (object != null) {
            site = new Site();
            site.setSiteID((Integer) object.get("siteID"));
            site.setSiteName((String) object.get("siteName"));
            site.setCreator((String) object.get("creator"));
            site.setCreateTime(((java.sql.Timestamp) object.get("createTime")).toLocalDateTime());
        }
        return site;
    }
}
