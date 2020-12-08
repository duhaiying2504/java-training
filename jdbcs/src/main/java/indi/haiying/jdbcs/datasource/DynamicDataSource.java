package indi.haiying.jdbcs.datasource;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@Setter
public class DynamicDataSource implements DataSource {

    private DataSource defaultDataSource;

    private Map<String, DataSource> dataSources;

    private DataSource getDataSource() {

        String name = DataSourceHelper.getName();
        log.error("the target datasource name: {}", name);
        if (name == null) {
            return this.defaultDataSource;
        } else {
            DataSource dataSource = this.dataSources.get(name);
            if (dataSource == null) {
                throw new RuntimeException("the target datasource is null, name: " + name);
            }

            return dataSource;
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return this.getDataSource().getConnection();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.getDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.getDataSource().isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return this.getDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        this.getDataSource().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        this.getDataSource().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return this.getDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.getDataSource().getParentLogger();
    }


}
