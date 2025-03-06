package Boot.Security.services;

import Boot.Security.mode.UserPrinciple;
import Boot.Security.mode.Users;
import Boot.Security.repo.UserREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserREPO repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user.equals(null))
        {
            System.out.println("User does not exists ");
            throw  new UsernameNotFoundException("User not found");
        }
        return  new UserPrinciple(user.getUsername(),user.getPassword());
    }
}
