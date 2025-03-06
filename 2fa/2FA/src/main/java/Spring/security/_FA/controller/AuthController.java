package Spring.security._FA.controller;

import Spring.security._FA.dto.LoginRequest;
import Spring.security._FA.dto.OtpRequest;
import Spring.security._FA.model.Users;
import Spring.security._FA.repo.UsersRepo;
import Spring.security._FA.service.JwtService;
import Spring.security._FA.service.TwoFactorAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TwoFactorAuthService service;

    @Autowired
    private JwtService Jservice;

    @Autowired
    private UsersRepo repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder(12);

    // Register User
    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        String key = service.generateSecretKey();
        user.setSecretKey(key);
        user.set2FAEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Corrected

        repo.save(user);

        String qrCodeUrl = "https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=otpauth://totp/"
                + user.getEmail() + "?secret=" + key + "&issuer=YourApp";

        return "Scan this QR Code: " + qrCodeUrl;
    }

    // Login - Step 1: Validate Email & Password
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Users user = repo.findByEmail(request.getEmail());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return "Enter OTP";
    }

    // Verify OTP and Generate JWT Token
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody OtpRequest request) {
        Users user = repo.findByEmail(request.getEmail()); // Fixed variable name
        if (user == null || !service.validateOtp(request.getOtp(), user.getSecretKey())) { // Fixed variable name
            throw new RuntimeException("Invalid OTP");
        }
        return Jservice.generateToken(user);
    }
}
