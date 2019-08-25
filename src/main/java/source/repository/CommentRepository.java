package source.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import source.entity.Comment;
import source.entity.News;
import source.entity.User;

import java.util.Set;

public interface CommentRepository extends CrudRepository<Comment,Integer> {
    Set<Comment> findAllByNews(News news);

    @Query("delete from Comment c where c.id=:id")
    @Modifying
    void deleteById(@Param("id")int id);
}
