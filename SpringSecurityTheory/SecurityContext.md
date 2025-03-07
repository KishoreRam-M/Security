## **What is SecurityContext in Spring Security?**
The **SecurityContext** in Spring Security is the container that holds authentication-related details for the current user (such as the authenticated principal, credentials, and granted authorities). It is used to store and retrieve the authenticated user’s information across the application.

### 🔹 **Key Points:**
- It holds an `Authentication` object representing the currently authenticated user.
- It is stored in a `ThreadLocal` variable, making it available throughout the request lifecycle.
- It allows security-related information to be accessed globally within a single request.
- It is automatically cleared after the request is processed to prevent security leaks.

---

## **How SecurityContext Works?**
1. **User sends a request**  
   - The request may contain authentication details (e.g., username/password, JWT token, OAuth token).
   
2. **Authentication Process**  
   - The request goes through an **AuthenticationFilter**.
   - The **AuthenticationManager** delegates authentication to an **AuthenticationProvider**.
   - If authentication is successful, an **Authentication** object is created.

3. **Storing Authentication in SecurityContext**  
   - If authentication is successful, Spring Security stores the `Authentication` object inside the `SecurityContext`.
   - The `SecurityContextHolder` manages this storage.

4. **Retrieving Authentication**  
   - Any part of the application (e.g., controllers, services) can retrieve the currently authenticated user from `SecurityContextHolder`.

5. **SecurityContext Clearance**  
   - After request processing is complete, Spring Security clears the `SecurityContext` to prevent security vulnerabilities.

---

## **How to Use SecurityContext?**

### ✅ **Getting the Authenticated User**
You can access the authenticated user from `SecurityContextHolder`:
```java
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String username = authentication.getName();
System.out.println("Authenticated user: " + username);
```

### ✅ **Setting Authentication Manually**
Sometimes, you may need to set authentication programmatically (e.g., after JWT authentication).
```java
UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

SecurityContextHolder.getContext().setAuthentication(authToken);
```

### ✅ **Clearing Security Context**
Spring Security automatically clears the `SecurityContext`, but you can manually clear it if needed:
```java
SecurityContextHolder.clearContext();
```

---

## **Where is SecurityContext Stored?**
By default, Spring Security stores the `SecurityContext` in a **ThreadLocal** variable. However, it can also be stored in:
- **Session (for stateful authentication)**
- **JWT tokens (for stateless authentication)**
- **Custom storage mechanisms (e.g., Redis, database)**

---

## **Flow Diagram**
```
User Request → Authentication Filter → Authentication Manager → Authentication Provider → SecurityContextHolder
```

---

