package indi.haiying.jdbcs.beans;

import indi.haiying.jdbcs.dao.Dao;
import indi.haiying.jdbcs.dao.JdbcDao;
import indi.haiying.jdbcs.datasource.DataSourceHelper;
import indi.haiying.jdbcs.service.SiteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfigrationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringConfigrationTest.class);//加载配置类
        context.refresh();//刷新并创建容器

        SiteService siteService = context.getBean(SiteService.class);
        System.out.println(siteService.getAllSites());
    }

    @Bean
    public DataSource dataSource() {

        return DataSourceHelper.newDataSourceFromProperties("config");
    }

    @Bean
    public Dao dao() {

        return new JdbcDao();
    }

    @Bean
    public SiteService siteService() {

        return new SiteService();
    }
}
