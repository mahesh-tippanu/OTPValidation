package com.otp.otpverification.model;

import com.otp.otpverification.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private Long phoneNumber;

    public User(UserDto userDto) {
        this.username=userDto.getUsername();
        this.password=userDto.getPassword();
        this.fullName=userDto.getFullName();
        this.phoneNumber=userDto.getPhoneNumber();
    }
}
