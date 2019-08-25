package source.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import source.entity.Comment;
import source.entity.News;
import source.entity.User;
import source.repository.CommentRepository;
import source.security.utils.SecurityUtils;
import source.service.CommentService;
import source.service.NewsService;

import java.util.Date;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepo;
    private final NewsService newsService;
    private final SecurityUtils securityUtils;

    public CommentServiceImpl(CommentRepository commentRepo, NewsService newsService, SecurityUtils securityUtils) {
        this.commentRepo = commentRepo;
        this.newsService = newsService;
        this.securityUtils = securityUtils;
    }

    @Override
    public Set<Comment> findAllByNews(int id) {
        News news = newsService.finById(id);
        if(news==null)
            return null;
        return commentRepo.findAllByNews(news);
    }

    @Override
    public boolean save(Comment comment) {
        if(comment==null)
            return false;
        User userDb = securityUtils.getAuthenticatedUserFromDB();
        comment.setUser(userDb);
        //fix date
//        comment.setDate(new Date());
        commentRepo.save(comment);
        return true;
    }

    @Override
    public void deleteById(int id) {
        if(id>=1)
            commentRepo.deleteById(id);
    }

    @Override
    public Comment findById(int id) {
        return commentRepo.findById(id).orElse(null);
    }
}
