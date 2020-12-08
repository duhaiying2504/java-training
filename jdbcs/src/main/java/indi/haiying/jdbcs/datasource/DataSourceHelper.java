package indi.haiying.jdbcs.datasource;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DataSourceHelper {

    private static final ThreadLocal<String> dataSourceName = new ThreadLocal<>();

    public static void setName(String name) {

        if (name == null || "".equals(name.trim())) {
            return;
        }

        dataSourceName.set(name);
    }

    public static String getName() {
        return dataSourceName.get();
    }

    public static void clear() {
        dataSourceName.remove();
    }

    public static DataSource newDataSourceFromProperties(String fileName) {
        return newDataSourceFromProperties(fileName, null);
    }

    public static DataSource newDataSourceFromProperties(String fileName, String name) {

        ResourceBundle config = ResourceBundle.getBundle(fileName);

        String name0 = name == null ? "." : "." + name + ".";
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getString("dataSource" + name0 + "url"));
        dataSource.setUsername(config.getString("dataSource" + name0 + "username"));
        dataSource.setPassword(config.getString("dataSource" + name0 + "password"));
        dataSource.setDriverClassName(config.getString("dataSource" + name0 + "driver"));


        return dataSource;
    }

    public static Map<Object, Object> newDataSourcesFromProperties(String fileName) {

        ResourceBundle config = ResourceBundle.getBundle(fileName);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getString("dataSource.url"));
        dataSource.setUsername(config.getString("dataSource.username"));
        dataSource.setPassword(config.getString("dataSource.password"));
        dataSource.setDriverClassName(config.getString("dataSource.driver"));

        HikariDataSource readonlyDataSource = new HikariDataSource();
        readonlyDataSource.setJdbcUrl(config.getString("dataSource.readonly.url"));
        readonlyDataSource.setUsername(config.getString("dataSource.readonly.username"));
        readonlyDataSource.setPassword(config.getString("dataSource.readonly.password"));
        readonlyDataSource.setDriverClassName(config.getString("dataSource.readonly.driver"));

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(config.getString("dataSource.name"), dataSource);
        targetDataSources.put(config.getString("dataSource.readonly.name"), readonlyDataSource);

        return targetDataSources;
    }

}
