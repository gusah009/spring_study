package com.example.reservation.dao;

public class CategoriesDaoSqls {
    public static final String SELECT_ALL = "SELECT category.id as \"id\", name, count\n" +
            "FROM category,\n" +
            "     (SELECT product.category_id as \"id\", COUNT(*) as \"count\"\n" +
            "      FROM display_info\n" +
            "               LEFT OUTER JOIN product ON display_info.product_id = product.id\n" +
            "      GROUP BY product.category_id) as count_table\n" +
            "where category.id = count_table.id;";
}
