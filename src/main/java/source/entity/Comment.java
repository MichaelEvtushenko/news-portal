package source.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private int id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="commented_at")
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_fk", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="news_fk",nullable = false)
    private News news;

    @Column(name="comment_body", length = 500, nullable = false)
    private String body;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Comment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
