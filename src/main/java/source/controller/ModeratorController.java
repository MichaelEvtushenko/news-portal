package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import source.entity.ModeratedNews;
import source.service.ModeratedNewsService;
import source.service.NewsService;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    private final NewsService newsService;
    private final ModeratedNewsService moderatedNewsService;

    public ModeratorController(NewsService newsService, ModeratedNewsService moderatedNewsService) {
        this.newsService = newsService;
        this.moderatedNewsService = moderatedNewsService;
    }

    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute("allNews",moderatedNewsService.findUnmoderatedNews());
        return "moderator/home";
    }
    @GetMapping("/post/{id}")
    public String postNews(@PathVariable("id")int id, Model model){
        ModeratedNews moderatedNews=new ModeratedNews();
        moderatedNews.setNews(newsService.finById(id));
        moderatedNewsService.save(moderatedNews);
        return "redirect:/moderator";
    }

    @GetMapping("/delete/{id}")
    public String deleteNews(@PathVariable("id")int id, Model model){
        newsService.deleteById(id);
        return "redirect:/moderator";
    }
}
