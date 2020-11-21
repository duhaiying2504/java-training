package indi.haiying.jdbcs.beans;

import indi.haiying.jdbcs.service.SiteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SiteService siteService = context.getBean(SiteService.class);

        System.out.println(siteService.getAllSites());
    }

}
