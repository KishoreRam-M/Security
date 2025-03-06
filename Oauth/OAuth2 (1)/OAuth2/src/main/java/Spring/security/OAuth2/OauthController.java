package Spring.security.OAuth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {
    @GetMapping("/hi")
    public String hi()
    {
        return "  hiii kishore ";
    }

}
