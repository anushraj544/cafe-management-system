package com.cafe.controller;

import com.cafe.dtos.SignupRequest;
import com.cafe.dtos.UserDto;
import com.cafe.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
    }

    @PostMapping("signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UserDto createdUserDto = authService.createUser(signupRequest);
        if (createdUserDto == null){
            return new ResponseEntity<>("User not created. come again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }


}
