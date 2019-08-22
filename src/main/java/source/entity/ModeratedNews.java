package source.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="moderated_news",uniqueConstraints = @UniqueConstraint(
        name="MODERATED_NEWS_PAIR_FK_UQ",
        columnNames = "moderator_fk"))
public class ModeratedNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="moderated_news_id")
    private int id;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<News> news=new HashSet<>();

    @OneToOne
    @JoinColumn(name="moderator_fk")
    private User moderator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }
}
