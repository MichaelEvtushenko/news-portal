package source.service.impl;

import org.springframework.stereotype.Service;
import source.entity.News;
import source.repository.NewsRepository;
import source.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepo;

    public NewsServiceImpl(NewsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    public News findNewsByTitle(String title){
        return null;
    }

    public Iterable<News> findAll(){
        return newsRepo.findAll();
    }

    public boolean saveNews(News news){
        News newsDb = newsRepo.findByTitle(news.getTitle());
        if(newsDb!=null)
            return false;
        newsRepo.save(news);
        return true;
    }

    @Override
    public News finById(int id) {
        return newsRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        newsRepo.deleteById(id);
    }
}
