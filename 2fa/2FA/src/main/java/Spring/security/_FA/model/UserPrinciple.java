package Spring.security._FA.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrinciple implements UserDetails {

    private final Users user; // ✅ Correct field

    public UserPrinciple(Users user) { // ✅ Correct constructor
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole())); // ✅ Dynamic role
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // ✅ Corrected method
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // ✅ Fixed infinite recursion
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
