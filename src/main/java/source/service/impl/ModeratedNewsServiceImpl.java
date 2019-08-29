package source.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import source.entity.ModeratedNews;
import source.entity.News;
import source.entity.User;
import source.repository.ModeratedNewsRepository;
import source.security.utils.SecurityUtils;
import source.service.ModeratedNewsService;
import source.service.UserService;

import java.util.Set;


@Service
public class ModeratedNewsServiceImpl implements ModeratedNewsService {

    private final ModeratedNewsRepository moderatedNewsRepo;
    private final UserService userService;
    private final SecurityUtils securityUtils;

    public ModeratedNewsServiceImpl(ModeratedNewsRepository moderatedNewsRepo, UserService userService, SecurityUtils securityUtils) {
        this.moderatedNewsRepo = moderatedNewsRepo;
        this.userService = userService;
        this.securityUtils = securityUtils;
    }

    @Override
    public Iterable<ModeratedNews> findAll() {
        return moderatedNewsRepo.findAll();
    }

    @Override
    public Set<News> findUnmoderatedNews() {
        return moderatedNewsRepo.findUnmoderatedNews();
    }

    @Override
    public boolean save(ModeratedNews moderatedNews) {
        User userDb = securityUtils.getAuthenticatedUserFromDB();
        moderatedNews.setModerator(userDb);
        moderatedNewsRepo.save(moderatedNews);
        return true;

    }

    @Override
    public ModeratedNews findById(int id) {
        if(id>0)
            return moderatedNewsRepo.findById(id).orElse(null);
        return null;
    }

    @Override
    public ModeratedNews findByNews(News news) {
        if(news!=null)
            return moderatedNewsRepo.findByNews(news);
        return null;
    }
}
