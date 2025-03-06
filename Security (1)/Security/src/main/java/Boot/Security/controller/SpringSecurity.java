package Boot.Security.controller;

import Boot.Security.mode.Users;
import Boot.Security.services.AuthService;
import Boot.Security.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurity {
    @Autowired
    MyUserDetailsService service;
    @Autowired
    AuthService s;
    @GetMapping("/")
    public String intro()
    {
        return  "SK";
    }



    @PostMapping("/register")
    public Users toRegister( @RequestBody Users user)
    {
        return  service.registerUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user)
    {

        return s.verify( user);
    }
}
