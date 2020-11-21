package indi.haiying.jdbcs.dao;


import java.util.List;
import java.util.Map;

public interface Dao {

    public List<Map<String, Object>> select(String sql, Object... params);

    public Map<String, Object> selectOne(String sql, Object... params);

    public int update(String sql, Object... params);

    public int insert(String sql, Object... params);

    public int delete(String sql, Object... params);

    public int[] updateBatch(String sql, Object[]... paramss);
}
