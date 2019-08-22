package source.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import source.entity.Role;
import source.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        source.entity.User userDb = userRepo.findByName(userName);
        if(userDb==null || userDb.getName()==null)
            throw new UsernameNotFoundException("User doesn't exist with such username "+userName);
        return buildUserDetails(userDb);
    }

    //converts source.entity.User user into
    //org.springframework.security.core.userdetails.User
    private User buildUserDetails(source.entity.User userDb){
        return new User(
                userDb.getName(),
                userDb.getPassword(),
                buildGrantedAuthorities(userDb.getRoles())
        );
    }

    private Set<GrantedAuthority> buildGrantedAuthorities(Set<Role> userRoles){
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(role->
                authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

}
