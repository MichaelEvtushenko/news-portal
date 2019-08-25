package source.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments;

    public News(){}

    public News(int id,String title, String body) {
        this.id=id;
        this.title = title;
        this.body = body;
    }


    public News(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof News)
            return Objects.equals(this.getTitle(), ((News) obj).getTitle());
        return false;
    }

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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
