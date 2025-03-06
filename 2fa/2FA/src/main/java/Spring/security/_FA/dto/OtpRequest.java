package Spring.security._FA.dto;



public class OtpRequest {
    private String email;
    private int otp;

    // Constructors
    public OtpRequest() {}

    public OtpRequest(String email, int  otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }
}
