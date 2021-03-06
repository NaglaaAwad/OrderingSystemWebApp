package tutorial.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tutorial.core.models.entities.User;
import tutorial.core.services.UserService;

/**
 * Created by Naglaa on 10/17/15.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User myUser = service.findByUserName(name);
        if(myUser == null) {
            throw new UsernameNotFoundException("no user found with " + name);
        }
        return new MyUserDetails(myUser);
    }
}
