package indi.haiying.jdbcs.dao;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Setter
public class JdbcDao implements Dao {

    @Autowired
    DataSource dataSource = null;

    public JdbcDao() {
    }

    public JdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Map<String, Object>> select(String sql, Object... params) {

        Connection connection = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            connection = this.getConnection();
            PreparedStatement preparedStatement = this.preparedStatement(sql, connection, params);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(this.toMap(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }

        return result;
    }

    public Map<String, Object> selectOne(String sql, Object... params) {

        Connection connection = null;
        ResultSet resultSet = null;
        Map<String, Object> result = null;
        try {
            connection = this.getConnection();
            PreparedStatement preparedStatement = this.preparedStatement(sql, connection, params);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = this.toMap(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }

        return result;
    }

    public int update(String sql, Object... params) {

        Connection connection = null;

        int updated = 0;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = this.preparedStatement(sql, connection, params);
            updated = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }

        return updated;
    }

    public int insert(String sql, Object... params) {
        return this.update(sql, params);
    }

    public int delete(String sql, Object... params) {
        return this.update(sql, params);
    }

    public int[] updateBatch(String sql, Object[]... paramss) {
        Connection connection = null;

        int[] updated = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = this.batchPreparedStatement(sql, connection, paramss);
            updated = preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }

        return updated;

    }

    private Map<String, Object> toMap(ResultSet resultSet) throws SQLException {
        Map<String, Object> data = new HashMap<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            data.put(metaData.getColumnLabel(i), resultSet.getObject(i));
        }
        return data;
    }

    private PreparedStatement batchPreparedStatement(String sql, Connection connection, Object[]... paramss) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (paramss != null) {
            for (int i = 0; i < paramss.length; i++) {
                for (int j = 0; j < paramss[i].length; j++) {
                    preparedStatement.setObject(j + 1, paramss[i][j]);
                }
                preparedStatement.addBatch();
            }
        }
        return preparedStatement;
    }

    private PreparedStatement preparedStatement(String sql, Connection connection, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement;
    }

    private Connection getConnection() throws SQLException {

        //    return DriverManager.getConnection(url, username, password);

        return dataSource.getConnection();
    }

    private void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
