package com.example.reservation.dao;

import com.example.reservation.dto.DisplayInfos;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.reservation.dao.DisplayInfosDaoSqls.SELECT_ALL;
import static com.example.reservation.dao.DisplayInfosDaoSqls.SELECT_CATEGORY;

@Repository
public class DisplayInfosDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<DisplayInfos> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfos.class);

    public DisplayInfosDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("category")
                .usingGeneratedKeyColumns("id");
    }

    public List<DisplayInfos> selectAll(Integer start) {
        Map<String, Integer> params = new HashMap<>();
        params.put("start", start);
        List<DisplayInfos> DisplayInfosLists = jdbc.query(SELECT_ALL, params, rowMapper);
        return DisplayInfosLists;
    }

    public List<DisplayInfos> selectCategory(Integer start, Integer categoryId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("start", start);
        params.put("categoryId", categoryId);
        List<DisplayInfos> DisplayInfosLists = jdbc.query(SELECT_CATEGORY, params, rowMapper);
        return DisplayInfosLists;
    }
}
