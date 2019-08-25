package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source.entity.Comment;
import source.entity.News;
import source.model.CommentForm;
import source.service.CommentService;
import source.service.NewsService;
import source.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final NewsService newsService;

    public CommentController(CommentService commentService, NewsService newsService) {
        this.commentService = commentService;
        this.newsService = newsService;
    }

    @GetMapping("/delete/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") int commentId,
                                @RequestHeader String referer){
        System.out.println("GET /comment/delete/{comment_id}");
        commentService.deleteById(commentId);
        return "redirect:"+referer;
    }

    @PostMapping("/add/{news_id}")
    public String addComment(@ModelAttribute("commentForm")@Validated CommentForm form,
                             BindingResult result,
                             @PathVariable("news_id")int id,
                             @RequestHeader String referer){
        News newsDb = newsService.finById(id);

        if(result.hasErrors()||newsDb==null)
            return "redirect:"+referer;

        //mapstruct should be used below
        Comment comment=new Comment();
        comment.setBody(form.getBody());
        comment.setNews(newsDb);

        commentService.save(comment);
        return "redirect:"+referer;
    }

    @GetMapping("/update/{comment_id}")
    public String updateCommentPage(@PathVariable("comment_id") int commentId,
                                    @RequestHeader String referer,
                                    Model model){
        Comment commentDb = commentService.findById(commentId);

        if(commentDb==null)
            return "redirect:"+referer;

        CommentForm form=new CommentForm();
        form.setBody(commentDb.getBody());
        form.setId(commentDb.getId());

        model.addAttribute("commentForm",form);
        return "comment/update";
    }

    @PostMapping("/update")
    public String updateComment(@ModelAttribute("commentForm")@Validated CommentForm form,
                                BindingResult result,
                                @RequestHeader String referer){
        if(result.hasErrors())
            return "redirect:"+referer;
        Comment commentDb = commentService.findById(form.getId());
        commentDb.setBody(form.getBody());
        commentService.save(commentDb);
        return "redirect:/news/"+commentDb.getNews().getId();
    }
}
