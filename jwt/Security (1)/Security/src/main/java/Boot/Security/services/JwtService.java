package Boot.Security.services;

import Boot.Security.mode.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public JwtService()
    {
        try {
            KeyGenerator keygen =KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keygen.generateKey();
         SECRET_KEY= Base64.getEncoder().encodeToString(key.getEncoded());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    private static String SECRET_KEY = ""; // Secure 256-bit Base64 key

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }



    public String verify(Users user)
    {
        Map<String,Object> claims = new HashMap<>();


        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt( new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis())))
                .and()
                .signWith(getSigningKey())
                .compact();

    }

}