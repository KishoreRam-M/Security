package Boot.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurity {
    @GetMapping("/")
    public String intro()
    {
        return  "SK";
    }

}
