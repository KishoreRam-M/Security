package Boot.Security.services;

import Boot.Security.mode.UserPrinciple;
import Boot.Security.mode.Users;
import Boot.Security.repo.UserREPO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserREPO repo;
    private final BCryptPasswordEncoder passwordEncoder;

    public MyUserDetailsService(UserREPO repo) {
        this.repo = repo;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null) {  // Fixed null check
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(user.getUsername(), user.getPassword());
    }

    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password before saving
        return repo.save(user);
    }
}
