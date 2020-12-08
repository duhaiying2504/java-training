package indi.haiying.jdbcs.beans;

import indi.haiying.jdbcs.dao.Dao;
import indi.haiying.jdbcs.dao.JdbcDao;
import indi.haiying.jdbcs.datasource.DataSourceHelper;
import indi.haiying.jdbcs.datasource.DynamicDataSourceAspect;
import indi.haiying.jdbcs.datasource.SpringDynamicDataSource;
import indi.haiying.jdbcs.service.SiteService;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@Import({DynamicDataSourceAspect.class})
@EnableAspectJAutoProxy
public class SpringDynamicDataSourceConfigrationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringDynamicDataSourceConfigrationTest.class);//加载配置类
        context.refresh();//刷新并创建容器

        SiteService siteService = context.getBean(SiteService.class);
        System.out.println(siteService.getAllSites());
    }

    @Bean
    public DataSource dataSource() {

        Map<Object, Object> targetDataSources = DataSourceHelper.newDataSourcesFromProperties("config");
        SpringDynamicDataSource dynamicDataSource = new SpringDynamicDataSource(
                targetDataSources.get("default"), targetDataSources);

        return dynamicDataSource;
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
