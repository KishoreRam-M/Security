package Spring.security._FA.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorAuthService {
    private  final GoogleAuthenticator gAuth= new GoogleAuthenticator();
    public String generateSecretKey()
    {
        GoogleAuthenticatorKey Key =gAuth.createCredentials();
        return Key.getKey();
    }
    public boolean validateOtp(int otp,String secretKey)
    {
        return gAuth.authorize(secretKey,otp);
    }

}
