package project.guestBook.dao;

import jdbc.dto.Role;
import project.guestBook.dto.Guestbook;
import project.guestBook.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();

        // 코드를 작성하세요.
        String sql = "SELECT id, name, content, regdate FROM guestbook";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String description = rs.getString(1);
                    long id = rs.getInt("id");
                    String name = rs.getString("name");
                    String content = rs.getString("content");
                    java.sql.Date regdate = rs.getDate("regdate");
                    Guestbook guestbook = new Guestbook(id, name, content, regdate);
                    list.add(guestbook); // list에 반복할때마다 Role인스턴스를 생성하여 list에 추가한다.
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
        String sql = "INSERT INTO guestbook (name, content, regdate) VALUES ( ?, ?, ? )";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, guestbook.getName());
            ps.setString(2, guestbook.getContent());
            ps.setDate(3, guestbook.getRegdate());

            ps.executeUpdate();
            System.out.println("INSERT!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
