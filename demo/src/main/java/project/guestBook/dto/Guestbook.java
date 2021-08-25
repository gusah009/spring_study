package project.guestBook.dto;


import java.util.Date;

public class Guestbook {
    private Long id;
    private String name;
    private String content;
    private java.sql.Date regdate;

    public Guestbook(Long id, String name, String content, java.sql.Date regdate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.regdate = regdate;
    }

    public Guestbook(String name, String content) {
        this.name = name;
        this.content = content;
        Date date = new Date();
        this.regdate = new java.sql.Date(date.getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Date getRegdate() {
        return regdate;
    }

    public void setRegdate(java.sql.Date regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "Guestbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
