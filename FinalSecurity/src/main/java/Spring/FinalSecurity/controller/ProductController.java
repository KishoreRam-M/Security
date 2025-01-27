package Spring.FinalSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/greet")
    public String toGetGreet()
    {
        return "Greeting KRM ";
    }

}
