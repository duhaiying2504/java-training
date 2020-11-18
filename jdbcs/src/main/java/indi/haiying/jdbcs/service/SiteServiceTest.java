package indi.haiying.jdbcs.service;

import java.util.Arrays;
import java.util.List;

public class SiteServiceTest {

    public static void main(String[] args) {

        SiteService siteService = new SiteService();

        Site site0 = siteService.getSite0(1);
        System.out.println(site0);

        Site site = siteService.getSite(2);
        System.out.println(site);

        List<Site> sites = siteService.getAllSites();
        System.out.println(sites);

        int updated = siteService.updateSiteName(site0.getSiteID(), site0.getSiteName());
        System.out.println(updated);

        int[] updateds = siteService.updateSiteNames(sites);
        System.out.println(Arrays.stream(updateds).sum());
    }

}
