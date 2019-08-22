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
        model.addAttribute("formPost",new ModeratedNews());
        return "moderator/home";
    }
    @GetMapping("/{id}")
    public String homePage(@PathVariable("id")int id, Model model){
       moderatedNewsService.save(moderatedNewsService.findById(id));
       return "redirect:/moderator";
    }
}
