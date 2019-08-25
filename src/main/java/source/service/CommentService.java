package source.service;

import source.entity.Comment;
import source.entity.News;

import java.util.Set;

public interface CommentService {
    Set<Comment>  findAllByNews(int id);
    boolean save(Comment comment);
    void deleteById(int id);
    Comment findById(int id);
}
