package Spring.security._FA.service;

import Spring.security._FA.model.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "mySecretKeymySecretKeymySecretKeymySecretKey"; // Should be at least 256 bits
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generate JWT Token
     */
    public String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // Using email as the subject
                .setIssuedAt(new Date()) // Issue date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 min expiry
                .signWith(key, SignatureAlgorithm.HS256) // Use secure key
                .compact();
    }

    /**
     * Extract username (email) from JWT Token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract any claim from JWT Token
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from JWT Token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Validate JWT Token
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Check if token is expired
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
