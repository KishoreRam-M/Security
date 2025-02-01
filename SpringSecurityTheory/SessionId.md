###  How Spring Security Handles Session IDs**  

1️⃣ **User Visits Website** → No session exists; server creates a **new session ID**.  
2️⃣ **Server Sends Session ID** → Stored in a **cookie (JSESSIONID)** and sent to the browser.  
3️⃣ **Browser Stores Session ID** → Used for future requests to identify the user.  
4️⃣ **User Logs In** → Server **authenticates** user and updates session data.  
5️⃣ **Spring Security Replaces Session ID** → **Prevents session fixation attacks** by generating a **new session ID** after login.  
6️⃣ **User Navigates the Website** → Server recognizes the session ID and serves personalized content.  
7️⃣ **User Logs Out** → **Session is destroyed**, and the session ID is removed.  

### **🔐 Security Features**  
✅ **Random Session ID Generation** – Prevents guessing.  
✅ **HttpOnly Cookies** – Blocks JavaScript access (prevents XSS).  
✅ **Session ID Regeneration** – Stops **session fixation attacks**.  
✅ **Session Timeout & Logout** – Automatically destroys inactive sessions.  

