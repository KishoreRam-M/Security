
In **Spring Security**, authentication is managed through a combination of key components:  

- **Authentication Provider**  
- **Authentication Manager**  
- **Authentication Filter**  

Each of these plays a crucial role in the authentication process. Let's break them down and see how they work together.

---

## **1. Authentication Provider**
An **AuthenticationProvider** is responsible for verifying user credentials. It takes an authentication request, checks the provided credentials, and returns a successful or failed authentication result.

🔹 **Key Points:**  
- Implements `AuthenticationProvider` interface.  
- Can handle multiple authentication mechanisms (e.g., username-password, OAuth, JWT, etc.).  
- Can have multiple implementations (e.g., `DaoAuthenticationProvider` for database authentication).  
- If authentication is successful, it returns an `Authentication` object with user details.  

🔹 **Example Implementation:**
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
```

---

## **2. Authentication Manager**
The **AuthenticationManager** is responsible for managing multiple authentication providers. It delegates the authentication request to the appropriate **AuthenticationProvider**.

🔹 **Key Points:**  
- Uses a list of `AuthenticationProvider` instances.  
- Calls each provider until one successfully authenticates the request.  
- If none succeed, it throws an authentication exception.  

🔹 **Example Implementation:**
```java
@Bean
public AuthenticationManager authenticationManager(List<AuthenticationProvider> authenticationProviders) {
    return new ProviderManager(authenticationProviders);
}
```

---

## **3. Authentication Filter**
The **AuthenticationFilter** intercepts incoming authentication requests, extracts credentials, and passes them to the **AuthenticationManager**.

🔹 **Key Points:**  
- Extracts credentials from HTTP requests (e.g., headers, body, tokens).  
- Calls `AuthenticationManager` to validate credentials.  
- If authentication is successful, it adds the authenticated user to the security context.  

🔹 **Example Implementation:**
```java
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
        throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }
}
```

---

## **How They Work Together**
1. **AuthenticationFilter** intercepts an authentication request.  
2. It extracts the credentials (username/password or token).  
3. The extracted credentials are sent to **AuthenticationManager**.  
4. The **AuthenticationManager** checks with available **AuthenticationProviders**.  
5. If an **AuthenticationProvider** successfully verifies the credentials, authentication is successful.  
6. The user is then authenticated and stored in the **SecurityContext**.  
7. If authentication fails, an exception is thrown, and access is denied.  

---

## **Relationship Between Them**
| Component | Role | Relationship |
|-----------|------|-------------|
| **Authentication Filter** | Captures user credentials | Calls AuthenticationManager |
| **Authentication Manager** | Delegates authentication | Uses AuthenticationProvider(s) |
| **Authentication Provider** | Performs authentication logic | Used by AuthenticationManager |

---

## **Diagram Representation**
```
Client → [Authentication Filter] → [Authentication Manager] → [Authentication Provider] → UserDetailsService (DB)
```

---

This structure allows **flexibility** because you can add different **AuthenticationProviders** for different authentication methods (e.g., JWT, OAuth, LDAP, etc.) without changing the `AuthenticationManager` or `AuthenticationFilter`.  

Let me know if you need further clarifications! 🚀
