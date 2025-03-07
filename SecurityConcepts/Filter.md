### **Why Do We Need Filters in Spring Security?** 🚀  

Filters are used in **Spring Security** to handle security-related tasks **before and after** a request is processed.  

Think of filters like **security guards** who check requests before they enter your system and after they leave. 🚦  

---

## **🛑 Why Use Filters?**  

### **1️⃣ Cross-Cutting Concerns (Reusable Security Logic)**
- Instead of writing security checks inside every controller/service, filters handle common security tasks **once** in a central place.  
- Example: **Authentication, Logging, Exception Handling.**  

💡 **Example:** Instead of writing authentication in every API, a filter checks **who is making the request** before reaching any controller.  

---

### **2️⃣ Pre-Processing (Before Request Reaches Controller)**
- Filters **modify or validate** requests before the application processes them.  
- Example: **Checking JWT Token, Validating API Keys, or Logging Requests.**  

💡 **Example:**  
- If a request **has no valid token**, the filter **blocks** it before it reaches your service.  
- If the token is valid, the filter **adds user details** to the request and allows it.  

---

### **3️⃣ Post-Processing (After Controller Response)**
- Filters can **modify responses** before sending them back to the client.  
- Example: **Adding security headers, modifying response data, or logging responses.**  

💡 **Example:**  
- Adding a security header like `X-Content-Type-Options: nosniff` to prevent security attacks.  
- Logging **response status and time taken** for monitoring.  

---

### **4️⃣ Request and Response Manipulation**
- Filters can **change requests or responses** as needed.  
- Example: **Converting XML to JSON, adding extra headers, modifying request bodies.**  

💡 **Example:**  
- If your API expects **JSON but receives XML**, a filter **converts XML to JSON** before passing it to the controller.  
- If a response contains **sensitive data**, a filter **removes or masks** it before sending it to the client.  

---

### **5️⃣ Separation of Concerns (Clean Code)**
- Security logic is **kept separate** from business logic.  
- This makes the codebase **clean and easy to maintain**.  

💡 **Example:**  
- Instead of **writing authentication logic inside controllers**, a filter handles it in **one place** for all requests.  
- This ensures **less duplication** and **better maintainability**.  

---

## **🔹 How Filters Work in Spring Security?**  
1️⃣ A user sends a request.  
2️⃣ **Pre-Processing**: Filters **check security** (authentication, logging, validation).  
3️⃣ The request is processed by the controller.  
4️⃣ **Post-Processing**: Filters **modify the response** (headers, data modification).  
5️⃣ The response is sent to the user.  

---

## **🔹 Example: Authentication Filter in Spring Boot**
Here’s how a **JWT Authentication Filter** works in Spring Security:

```java
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing");
            return;
        }

        // Extract and validate JWT
        String jwt = token.substring(7);
        if (!validateToken(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            return;
        }

        // Continue processing the request
        filterChain.doFilter(request, response);
    }
}
```
**What this does:**  
✅ **Pre-Processing**: Checks if the token is valid before allowing the request.  
✅ **Post-Processing**: If invalid, **stops** request processing and sends an error.  

---
