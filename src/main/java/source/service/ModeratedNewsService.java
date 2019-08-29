package source.service;

import source.entity.ModeratedNews;
import source.entity.News;
import source.entity.User;

import java.util.Optional;
import java.util.Set;

public interface ModeratedNewsService {
    ModeratedNews findByNews(News news);
    Iterable<ModeratedNews> findAll();
    Set<News> findUnmoderatedNews();
    boolean save(ModeratedNews moderatedNews);
    ModeratedNews findById(int id);
}
