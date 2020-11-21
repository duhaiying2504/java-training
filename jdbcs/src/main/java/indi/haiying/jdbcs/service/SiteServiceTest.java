package indi.haiying.jdbcs.service;

import com.zaxxer.hikari.HikariDataSource;
import indi.haiying.jdbcs.dao.JdbcDao;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SiteServiceTest {


    public static void main(String[] args) {

        ResourceBundle dataSourceConfig = ResourceBundle.getBundle("config");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceConfig.getString("dataSource.url"));
        dataSource.setUsername(dataSourceConfig.getString("dataSource.username"));
        dataSource.setPassword(dataSourceConfig.getString("dataSource.password"));
        dataSource.setDriverClassName(dataSourceConfig.getString("dataSource.driver"));

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
