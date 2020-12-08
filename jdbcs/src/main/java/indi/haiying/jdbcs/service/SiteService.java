package indi.haiying.jdbcs.service;

import indi.haiying.jdbcs.dao.Dao;
import indi.haiying.jdbcs.datasource.DS;
import indi.haiying.jdbcs.datasource.DataSourceHelper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
public class SiteService {

    @Autowired
    private Dao dao;

    public SiteService() {
    }

    public SiteService(Dao dao) {
        this.dao = dao;
    }

    public Site getSite(Integer siteID) {

        DataSourceHelper.setName("readonly");

        Map<String, Object> object = dao.selectOne(
                "select siteID, siteName, creator, createTime from data_site where siteID = ?", siteID);

        DataSourceHelper.clear();

        return this.toSite(object);
    }

    @DS("readonly")
    public List<Site> getAllSites() {

        List<Map<String, Object>> objects = dao.select(
                "select siteID, siteName, creator, createTime from data_site");

        return this.toSites(objects);
    }

    public int updateSiteName(Integer siteID, String name) {

        return this.dao.update("update data_site set siteName = ? where siteID = ?", name, siteID);
    }

    public int[] updateSiteNames(List<Site> sites) {

        Object[][] params = new Object[sites.size()][2];
        for (int i = 0; i < sites.size(); i++) {
            params[i][0] = sites.get(i).getSiteName();
            params[i][1] = sites.get(i).getSiteID();
        }

        return this.dao.updateBatch("update data_site set siteName = ? where siteID = ?", params);
    }

    private List<Site> toSites(List<Map<String, Object>> objects) {

        List<Site> sites = new ArrayList<>();
        for (Map<String, Object> object : objects) {
            sites.add(this.toSite(object));
        }
        return sites;
    }

    private Site toSite(Map<String, Object> object) {
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
