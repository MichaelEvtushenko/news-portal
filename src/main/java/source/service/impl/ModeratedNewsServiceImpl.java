package source.service.impl;

import org.springframework.stereotype.Service;
import source.entity.ModeratedNews;
import source.entity.User;
import source.repository.ModeratedNewsRepository;
import source.service.ModeratedNewsService;

import java.util.Set;


@Service
public class ModeratedNewsServiceImpl implements ModeratedNewsService {
    private final ModeratedNewsRepository moderatedNewsRepo;

    public ModeratedNewsServiceImpl(ModeratedNewsRepository moderatedNewsRepo) {
        this.moderatedNewsRepo = moderatedNewsRepo;
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
}
