package source.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import source.entity.News;
import source.model.NewsForm;
import source.service.ModeratedNewsService;
import source.service.NewsService;
import source.utils.TransformModeratedNewsToNews;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final ModeratedNewsService moderatedNewsService;

    public NewsController(NewsService newsService, ModeratedNewsService moderatedNewsService) {
        this.newsService = newsService;
        this.moderatedNewsService = moderatedNewsService;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("newsList", TransformModeratedNewsToNews.
                transform(moderatedNewsService.findAll()));

        model.addAttribute("newsForm", new NewsForm());
        return "index";
    }
    @PostMapping("/")
    public String addNews(@ModelAttribute("newsForm") @Validated NewsForm form,
                          BindingResult result, Model model){
        boolean error=result.hasErrors();

        //mapstruct should be
        News news = new News();
        news.setBody(form.getBody());
        news.setTitle(form.getTitle());

        if (!error && !newsService.saveNews(news)) {
            model.addAttribute("message", "Title already taken");
        }
        model.addAttribute("newsForm",error?form:new NewsForm());

        model.addAttribute("newsList", TransformModeratedNewsToNews.
                transform(moderatedNewsService.findAll()));

        return "index";
    }
}
