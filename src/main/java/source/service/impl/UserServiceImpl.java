package source.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source.entity.Role;
import source.entity.User;
import source.repository.UserRepository;
import source.service.RoleService;
import source.service.UserService;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepo, PasswordEncoder encoder, RoleService roleService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    public User findUserByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public boolean saveUser(User user) {
        User userDb = findUserByName(user.getName());
        if(userDb!=null)
            return false;
        Role defaultRole = roleService.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(defaultRole));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
}
