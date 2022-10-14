package com.otp.otpverification.controller;

import com.otp.otpverification.dto.UserDto;
import com.otp.otpverification.model.ResponseObj;
import com.otp.otpverification.model.User;
import com.otp.otpverification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ResponseEntity<ResponseObj> userLogin(@RequestBody UserDto userDto) throws MessagingException {
        return new ResponseEntity<>(userService.userLogin(userDto),HttpStatus.ACCEPTED);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam("otp") Integer otp, @RequestParam("username") String username){
        userService.verifyOtp(otp,username);
        ResponseObj responseObj= new ResponseObj("OTP is verified successfully","welcome to user");
        return new ResponseEntity<>(userService.verifyOtp(otp,username), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObj> insert (@RequestBody UserDto userDto) throws MessagingException {
        User user = userService.insert(userDto);
        ResponseObj responseObj= new ResponseObj("user details is submitted",user);
        return new ResponseEntity<>(userService.userLogin(userDto),HttpStatus.ACCEPTED);
    }
}
