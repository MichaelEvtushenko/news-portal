package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import source.entity.News;
import source.model.NewsForm;
import source.service.ModeratedNewsService;
import source.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final ModeratedNewsService moderatedNewsService;

    public NewsController(NewsService newsService, ModeratedNewsService moderatedNewsService) {
        this.newsService = newsService;
        this.moderatedNewsService = moderatedNewsService;
    }

    @GetMapping("/news/{id}")
    public String getNewsPage(@PathVariable("id")int id, Model model) {
        model.addAttribute("news",newsService.finById(id));
        return "news-page";
    }

    @GetMapping("/")
    public String getAll(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();

        model.addAttribute("newsList",moderatedNewsService.findAll());
        model.addAttribute("message",session.getAttribute("message"));
        model.addAttribute("org.springframework.validation.BindingResult.newsForm",
                ((BindingResult) session.getAttribute("org.springframework.validation.BindingResult.newsForm")));
                
        model.addAttribute("newsForm",
                session.getAttribute("newsForm")==null?new NewsForm():
                session.getAttribute("newsForm"));

        invalidateAttributes(session,"message","newsForm","org.springframework.validation.BindingResult.newsForm");
        return "index";
    }
    @PostMapping("/")
    public String addNews(@ModelAttribute("newsForm") @Validated NewsForm form,
                          BindingResult result, ModelMap model, HttpServletRequest req){
        HttpSession session = req.getSession();

        boolean error=result.hasErrors();

        //mapstruct should be
        News news = new News();
        news.setBody(form.getBody());
        news.setTitle(form.getTitle());

        if (!error && !newsService.saveNews(news)) {
            req.getSession().setAttribute("message","Title already taken");
            error=true;
        }
        req.getSession().setAttribute("newsForm",error?form:new NewsForm());

        if(!error)
           req.getSession().setAttribute("message", "Your news is moderating...");

        session.setAttribute("org.springframework.validation.BindingResult.newsForm",
                model.get("org.springframework.validation.BindingResult.newsForm"));

        return "redirect:/";
    }
    private void invalidateAttributes(HttpSession session, String...attrs){
        for(String attr:attrs)
            session.removeAttribute(attr);
    }
}
