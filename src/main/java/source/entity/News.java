package source.entity;

import javax.persistence.*;

@Entity
@Table(name = "news",uniqueConstraints = {@UniqueConstraint(name="NEWS_TITLE_UQ",
        columnNames = "news_title")})
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="news_id")
    private int id;

    @Column(name="news_title",nullable = false, length = 32)
    private String title;

    @Column(name="news_body",nullable = false, length = 500)
    private String body;

    public News(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
