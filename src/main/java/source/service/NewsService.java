package source.service;


import source.entity.News;
import source.repository.NewsRepository;

public interface NewsService {
    News findNewsByTitle(String title);
    Iterable<News> findAll();
    boolean saveNews(News news);
    News finById(int id);
    void deleteById(int id);
}
