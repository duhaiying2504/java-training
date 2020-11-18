package indi.haiying.jdbcs.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcBadDao {

    private String url = "jdbc:mysql://192.168.172.50:6008/csms";

    private String username = "dev_user";

    private String password = "dev_password";

    private String driver = "com.mysql.cj.jdbc.Driver";

    public JdbcBadDao() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> selectOne(String sql) {

        Connection connection = getConnection();
        ResultSet resultSet = null;
        Map<String, Object> result = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                result = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    result.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }

        return result;
    }

    public int update(String sql) {

        Connection connection = getConnection();
        int updated = 0;
        try {
            Statement statement = connection.createStatement();
            updated = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }

        return updated;
    }

    public int insert(String sql) {
        return this.update(sql);
    }

    public int delete(String sql) {
        return this.update(sql);
    }

    private Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private void closeConnection(Connection connection) {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
