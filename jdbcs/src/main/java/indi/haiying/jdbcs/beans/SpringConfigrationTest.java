package indi.haiying.jdbcs.beans;

import com.zaxxer.hikari.HikariDataSource;
import indi.haiying.jdbcs.dao.Dao;
import indi.haiying.jdbcs.dao.JdbcDao;
import indi.haiying.jdbcs.service.SiteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ResourceBundle;

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

        ResourceBundle dataSourceConfig = ResourceBundle.getBundle("config");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceConfig.getString("dataSource.url"));
        dataSource.setUsername(dataSourceConfig.getString("dataSource.username"));
        dataSource.setPassword(dataSourceConfig.getString("dataSource.password"));
        dataSource.setDriverClassName(dataSourceConfig.getString("dataSource.driver"));

        return dataSource;
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
