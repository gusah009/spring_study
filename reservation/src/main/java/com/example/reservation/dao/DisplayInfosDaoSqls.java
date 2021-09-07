package com.example.reservation.dao;

public class DisplayInfosDaoSqls {
    public static final String SELECT_ALL = "select\n" +
            "product.id,\n" +
            "category_id,\n" +
            "di.id as \"display_info_id\",\n" +
            "name,\n" +
            "description,\n" +
            "content,\n" +
            "event,\n" +
            "opening_hours,\n" +
            "place_name,\n" +
            "place_lot,\n" +
            "place_street,\n" +
            "tel,\n" +
            "homepage,\n" +
            "email,\n" +
            "di.create_date as \"create_date\",\n" +
            "di.modify_date as \"modify_date\"\n" +
            "from product\n" +
            "         left outer join\n" +
            "     display_info di on product.id = di.product_id\n" +
            "         left outer join\n" +
            "    category c on product.category_id = c.id\n" +
            "limit :start, 18446744073709551615";

    public static final String SELECT_CATEGORY = "select\n" +
            "product.id,\n" +
            "category_id,\n" +
            "di.id as \"display_info_id\",\n" +
            "name,\n" +
            "description,\n" +
            "content,\n" +
            "event,\n" +
            "opening_hours,\n" +
            "place_name,\n" +
            "place_lot,\n" +
            "place_street,\n" +
            "tel,\n" +
            "homepage,\n" +
            "email,\n" +
            "di.create_date as \"create_date\",\n" +
            "di.modify_date as \"modify_date\"\n" +
            "from product\n" +
            "         left outer join\n" +
            "     display_info di on product.id = di.product_id\n" +
            "         left outer join\n" +
            "    category c on product.category_id = c.id\n" +
            "where category_id = :categoryId\n" +
            "limit :start, 18446744073709551615";
}
