## **What is a Session Fixation Attack?**  
A **session fixation attack** is when an attacker **forces a user to use a pre-set session ID**. If the attacker knows this session ID, they can hijack the user’s session and gain access to their account.  

---

### **1️⃣ How Does a Session Work in Web Apps?**  
When you log in to a website:  
1. The server creates a **session** for you.  
2. It assigns a **session ID** (a unique identifier).  
3. This session ID is stored in your browser (usually as a **cookie**).  
4. Every time you make a request (click a link, submit a form), your browser sends this **session ID** to the server.  
5. The server checks this **session ID** to identify you.  

🔹 **Problem:** If an attacker knows your **session ID**, they can **impersonate you**.

---

### **2️⃣ How Does a Session Fixation Attack Work?**  
Here’s how an attacker exploits **session fixation**:

### **🔴 Step 1: Attacker Gets a Session ID**  
- The attacker visits the website and gets a **valid session ID** (e.g., `123ABC`).  
- Example:  
  ```java
  session_id = "123ABC"
  ```

### **🔴 Step 2: Trick the User into Using This Session ID**  
- The attacker tricks a victim into **using the same session ID** (`123ABC`).  
- Methods:
  - **Phishing**: Sends a fake login link with a pre-set session ID.
  - **Cross-Site Scripting (XSS)**: Injects JavaScript that sets the session ID.  

- Example:
  ```html
  <a href="https://example.com/login;jsessionid=123ABC">Click here</a>
  ```
  Now, when the victim clicks the link and logs in, they use **session ID `123ABC`**.

### **🔴 Step 3: Attacker Takes Over the Session**  
- Since the victim is now **logged in with `123ABC`**, the attacker can **reuse the same session ID** to access the account.  
- The attacker can **browse the site as if they were the victim**.

---

### **3️⃣ How Does Spring Security Prevent This?**  
Spring Security prevents **session fixation attacks** by **changing the session ID after login**.  

✅ **Before Login:**  
  - Session ID = `123ABC` (attacker’s session ID)  
✅ **After Login:**  
  - New Session ID = `XYZ789` (completely different)  

So, even if the attacker **forces the user to use session ID `123ABC`**, it becomes **invalid after login**.  

---

### **4️⃣ How to Configure Session Fixation Protection?**  
Spring Security **enables this by default**, but you can configure it manually:

```java
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session
                .sessionFixation(SessionFixationConfigurer::newSession) // Changes session ID after login
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults());

        return http.build();
    }
}
```

🔹 **Options for `sessionFixation()`**:  
- `newSession()` → Creates a **completely new session** after login (**default**).  
- `none()` → **No session ID change** (not secure!).  
- `migrateSession()` → Changes session ID but **keeps session attributes**.  

---

### **5️⃣ Summary**  
| Feature | What It Does |
|---------|-------------|
| **Session Fixation Attack** | Attacker tricks a user into using a **known session ID**. |
| **How It Works** | Victim logs in → Attacker uses the same session ID → Hijacks the account. |
| **Spring Security Fix** | Changes the **session ID after login**, making old sessions useless. |
| **Default Setting** | Spring Security automatically **prevents session fixation attacks**. |

