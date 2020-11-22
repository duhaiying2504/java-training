package indi.haiying.jdbcs.dao;

import com.zaxxer.hikari.HikariDataSource;
import indi.haiying.jdbcs.service.SiteService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

@Slf4j
public class DaoLogProxy implements InvocationHandler {

    private Dao target = null;

    public static void main(String[] args) {

        ResourceBundle dataSourceConfig = ResourceBundle.getBundle("config");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceConfig.getString("dataSource.url"));
        dataSource.setUsername(dataSourceConfig.getString("dataSource.username"));
        dataSource.setPassword(dataSourceConfig.getString("dataSource.password"));
        dataSource.setDriverClassName(dataSourceConfig.getString("dataSource.driver"));

        Dao proxyDao = DaoLogProxy.newProxyDao(new JdbcDao(dataSource));
        proxyDao.select("select * from data_site");

    }

    private DaoLogProxy(Dao target) {
        this.target = target;
    }

    public static Dao newProxyDao(Dao target) {
        return (Dao)Proxy.newProxyInstance(Dao.class.getClassLoader(), new Class[]{Dao.class}, new DaoLogProxy(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long begin = System.currentTimeMillis();

        Object result = method.invoke(target, args);

        Thread.sleep(1000);

        long end = System.currentTimeMillis();

        log.warn("method : {}, args: {}, time: {} ms", method.getName(), args, end - begin);

        return result;
    }


}
