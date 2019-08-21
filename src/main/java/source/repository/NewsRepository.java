package source.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import source.entity.News;

@Repository
public interface NewsRepository extends CrudRepository<News,Integer> {
    News findByTitle(String title);
}
