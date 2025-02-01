### **What Does Spring Security Do By Default?**  

When you add **Spring Security** to your project, it automatically provides several security features without any extra configuration. Here’s what it does in simple terms:

---

### **1️⃣ Requires Login for Every Page**  
👉 **Every page in your app is protected**. If you try to visit a page without logging in, Spring Security will **redirect you to a login page**.

---

### **2️⃣ Generates a Login Page for You**  
👉 You **don’t have to create a login page**. Spring Security will **generate one automatically**.

---

### **3️⃣ Creates a Default User**  
👉 By default, Spring Security creates a **user** with:  
   - **Username:** `user`  
   - **Password:** A random password (printed in the console)  

🔹 You can use this to log in.  

---

### **4️⃣ Allows Logging Out**  
👉 A **logout option is provided**. When a user logs out, their session is ended.

---

### **5️⃣ Protects Against CSRF Attacks**  
👉 **CSRF (Cross-Site Request Forgery)** is when a hacker tricks you into making a request on a website where you're logged in.  
🔹 Spring Security **blocks these attacks by default**.

---

### **6️⃣ Prevents Session Fixation Attacks**  
👉 Attackers sometimes try to **steal your session** (so they can pretend to be you).  
🔹 Spring Security **prevents this** by creating a **new session** after login.

---

### **7️⃣ Adds Important Security Headers**  
When your app sends responses, Spring Security **adds extra security rules** in the background:  

- ✅ **Forces HTTPS (Secure Connections)**  
  Prevents hackers from intercepting your data.  

- ✅ **Blocks Malicious Scripts (XSS Protection)**  
  Stops attackers from injecting dangerous JavaScript into your site.  

- ✅ **Prevents Clickjacking**  
  Stops other websites from embedding your site in a hidden frame and tricking users into clicking something harmful.

- ✅ **Prevents Caching Sensitive Data**  
  Browsers are told **not to store** sensitive data (like passwords) in cache.

---

### **8️⃣ Works With Java’s `HttpServletRequest` Methods**  
Spring Security **integrates with Java’s built-in web request system**, so you can do things like:  

- **Check who is logged in:** `request.getRemoteUser()`  
- **Get user details:** `request.getUserPrincipal()`  
- **Check user roles:** `request.isUserInRole("ADMIN")`  
- **Manually log in a user:** `request.login("username", "password")`  
- **Log out a user:** `request.logout()`  

---

### **Summary (Simple Table)**  

| Feature | What It Does |
|---------|-------------|
| **Requires Login** | Every page needs authentication. |
| **Provides a Login Page** | A default login page is generated. |
| **Creates a Default User** | Username: `user`, Password: (printed in console). |
| **Logout Option** | Allows users to log out. |
| **CSRF Protection** | Blocks cross-site request forgery attacks. |
| **Session Security** | Prevents session hijacking. |
| **Security Headers** | Protects against clickjacking, XSS, and more. |
| **Works With Java Requests** | Lets you check logged-in users, roles, etc. |
