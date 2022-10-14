package com.otp.otpverification.controller;
import com.otp.otpverification.service.EmailService;
import com.otp.otpverification.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class OtpController {
    @Autowired
    public OtpService otpService;
    @Autowired
    public EmailService emailService;
    public Integer generateOTP(String username) throws MessagingException {
        int otp = otpService.generateOTP(username);
//        EmailTemplate template = new EmailTemplate("SendOtp.html");
//        Map<String,String> replacements = new HashMap<String,String>();
//        replacements.put("user", username);
//        replacements.put("otpnum", String.valueOf(otp));
//        String message = template.getTemplate(replacements);
        emailService.sendOtpMessage("maheshkumartippanu@gmail.com", "OTP-SpringBoot", String.valueOf(otp));
        return otp;
    }
    public String validateOtp(Integer otp, String username){
        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
        //Validate the Otp
        if(otp >= 0){
            int serverOtp = otpService.getOtp(username);
            if(serverOtp > 0){
                if(otp == serverOtp){
                    otpService.clearOTP(username);
                    return (SUCCESS);
                }
                else {
                    return FAIL;
                }
            }else {
                return FAIL;
            }
        }else {
            return FAIL;
        }
    }
}
