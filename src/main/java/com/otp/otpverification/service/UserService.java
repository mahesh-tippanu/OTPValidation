package com.otp.otpverification.service;

import com.otp.otpverification.controller.OtpController;
import com.otp.otpverification.dto.UserDto;
import com.otp.otpverification.model.ResponseObj;
import com.otp.otpverification.model.User;
import com.otp.otpverification.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ResponseObj responseObj;
    @Autowired
    OtpController otpController;
    public ResponseObj userLogin(UserDto userDto) throws MessagingException {
        User user = userRepo.findByUsername(userDto.getUsername());
        if(user != null){
            if(user.getPassword().equals(userDto.getPassword())){
                return new ResponseObj(otpController.generateOTP(userDto.getUsername()),"Otp sent successfully");
            }else{
                return new ResponseObj(null,"Invalid password");
            }
        }else{
            return new ResponseObj(null,"User doesn't exist");
        }
    }
    public String verifyOtp(Integer otp, String username){
        return otpController.validateOtp(otp,username);
    }
    public  User insert(UserDto userDto){
        User user = new User(userDto);
        userRepo.save(user);
        return user;
    }
}
