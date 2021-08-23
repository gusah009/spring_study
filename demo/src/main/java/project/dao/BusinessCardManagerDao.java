package project.dao;

import project.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class BusinessCardManagerDao {
    private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
    private static String dbUser = "root";
    private static String dbpasswd = "akqoadl112#";

    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
        List<BusinessCard> cardList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql = "SELECT name, phone, companyName, createDate FROM BusinessCard WHERE name like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, keyword);
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String companyName = rs.getString("companyName");
                Date createDate = rs.getDate("createDate");
                BusinessCard card = new BusinessCard(name, phone, companyName);
                card.setCreateDate((java.sql.Date) createDate);
                cardList.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return cardList;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// 구현하세요.

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO BusinessCard (name, phone, companyName, createDate) VALUES ( ?, ?, ?, ? )";

        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, businessCard.getName());
            ps.setString(2, businessCard.getPhone());
            ps.setString(3, businessCard.getCompanyName());
            ps.setDate(4, businessCard.getCreateDate());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return businessCard;
    }
}
