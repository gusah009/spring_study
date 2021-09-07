package com.example.reservation.dao;

import com.example.reservation.dto.Categories;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.example.reservation.dao.CategoriesDaoSqls.SELECT_ALL;

@Repository
public class CategoriesDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Categories> rowMapper = BeanPropertyRowMapper.newInstance(Categories.class);

    public CategoriesDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("category")
                .usingGeneratedKeyColumns("id");
    }

    public List<Categories> selectAll() {
        List<Categories> categoriesList = jdbc.query(SELECT_ALL, rowMapper);
        return categoriesList;
    }
}
