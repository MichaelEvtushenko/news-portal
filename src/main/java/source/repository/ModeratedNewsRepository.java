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
}
