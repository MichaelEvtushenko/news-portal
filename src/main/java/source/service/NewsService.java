package source.service;

import org.springframework.stereotype.Service;
import source.entity.News;
import source.repository.NewsRepository;


@Service
public class NewsService {
    private final NewsRepository newsRepo;

    public NewsService(NewsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    public News findNewsByTitle(String title){
        return newsRepo.findByTitle(title);
    }

    public Iterable<News> findAll(){
        return newsRepo.findAll();
    }

    public void saveNews(News news){
        newsRepo.save(news);
    }
}
