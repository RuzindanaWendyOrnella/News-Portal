package models;

import org.sql2o.Connection;

import java.util.List;

public class GeneralNews {
    private String content;
    private String about;
    private String author;
    private int id;

    public GeneralNews(String content, String about, String author){
        this.content=content;
        this.about=about;
        this.author=author;

    }
    public String getContent() {
        return content;
    }

    public String getAbout() {
        return about;
    }

    public String getAuthor() {
        return author;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO generalnews (content,about,author) VALUES (:content,:about,:author);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("content", this.content)
                    .addParameter("about", this.about)
                    .addParameter("author", this.author)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<GeneralNews> all() {
        String sql = "SELECT * FROM generalnews";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(GeneralNews.class);
        }
    }
}
