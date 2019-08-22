package source.service;

import source.entity.ModeratedNews;
import source.entity.User;

import java.util.Set;

public interface ModeratedNewsService {
    Set<ModeratedNews> findAllByModerator(User moderator);
    Iterable<ModeratedNews> findAll();
}
