package source.model;

import source.entity.News;
import source.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class CommentForm {
    private int id;
    private Date date;
    private User user;
    private News news;
    @NotBlank(message = "Comment cannot be empty")
    @Pattern(regexp = "[A-Za-z0-9, .!?;:]*"
            ,message = "Comment must only consist of regular symbols (without '<','/' for e.g)")
    private String body;

    public CommentForm(){}

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

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
