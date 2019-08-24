package source.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source.entity.User;
import source.model.UserForm;
import source.repository.UserRepository;
import source.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String signUpView(Model model){
        model.addAttribute("userForm",new UserForm());
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String signUp(@Validated UserForm form, BindingResult result,Model model){
        boolean error=result.hasErrors();
        if (error)
            model.addAttribute(form);

        if(!error && !form.getPassword().equals(form.getCheckPassword())) {
            model.addAttribute("errorMessage", "Passwords must match");
            error=true;
        }

        //mapstruct should be
        User user = new User();
        user.setPassword(form.getPassword());
        user.setName(form.getUsername());
        if(!error && !userService.saveUser(user)) {
            model.addAttribute("errorMessage", "Username already exists");
            error=true;
        }
        if(error)
            return "sign-up";
        return "redirect:/";
    }
}
