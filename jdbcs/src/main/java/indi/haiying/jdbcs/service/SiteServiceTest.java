package indi.haiying.jdbcs.service;

import indi.haiying.jdbcs.dao.JdbcDao;
import indi.haiying.jdbcs.datasource.DataSourceHelper;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class SiteServiceTest {


    public static void main(String[] args) {

        DataSource dataSource = DataSourceHelper.newDataSourceFromProperties("config");

        SiteService siteService = new SiteService(new JdbcDao(dataSource));

        Site site = siteService.getSite(2);
        System.out.println(site);

        List<Site> sites = siteService.getAllSites();
        System.out.println(sites);

        int updated = siteService.updateSiteName(site.getSiteID(), site.getSiteName());
        System.out.println(updated);

        int[] updateds = siteService.updateSiteNames(sites);
        System.out.println(Arrays.stream(updateds).sum());
    }

}
