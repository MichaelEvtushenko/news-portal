package source.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import source.entity.User;
import source.service.UserService;

@Service
public class SecurityUtils {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public User getAuthenticatedUserFromDB(){
        org.springframework.security.core.userdetails.User principal =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal());
        return userService.findUserByName(principal.getUsername());
    }
}
