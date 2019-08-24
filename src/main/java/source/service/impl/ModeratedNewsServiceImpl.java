package source.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import source.entity.ModeratedNews;
import source.entity.News;
import source.entity.User;
import source.repository.ModeratedNewsRepository;
import source.service.ModeratedNewsService;
import source.service.UserService;

import java.util.Set;


@Service
public class ModeratedNewsServiceImpl implements ModeratedNewsService {

    private final ModeratedNewsRepository moderatedNewsRepo;
    private final UserService userService;

    public ModeratedNewsServiceImpl(ModeratedNewsRepository moderatedNewsRepo, UserService userService) {
        this.moderatedNewsRepo = moderatedNewsRepo;
        this.userService = userService;
    }

    @Override
    public Set<ModeratedNews> findAllByModerator(User moderator) {
        if(moderator!=null)
            return moderatedNewsRepo.findAllByModerator(moderator);
        return null;
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
        org.springframework.security.core.userdetails.User principal = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User userDb = userService.findUserByName(principal.getUsername());
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
}
