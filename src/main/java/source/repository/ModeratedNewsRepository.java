package source.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import source.entity.ModeratedNews;
import source.entity.News;
import source.entity.User;

import java.util.Set;

@Repository
public interface ModeratedNewsRepository extends CrudRepository<ModeratedNews,Integer> {
    Set<ModeratedNews> findAllByModerator(User moderator);
    @Query("select n from source.entity.News n left join " +
            "source.entity.ModeratedNews mn on mn.news.id=n.id where mn.id=null")
    Set<News> findUnmoderatedNews();
}
