package source.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import source.entity.News;
import source.model.NewsForm;
import source.service.NewsService;

import java.io.IOException;


@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("newsList", newsService.findAll());
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

        model.addAttribute("newsList", newsService.findAll());

        return "index";
    }
}
