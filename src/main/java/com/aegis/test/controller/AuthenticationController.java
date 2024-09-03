package com.aegis.test.controller;

import com.aegis.test.model.User;
import com.aegis.test.dto.LoginUserDto;
import com.aegis.test.dto.RegisterUserDto;
import com.aegis.test.dto.ResetPasswordDto;
import com.aegis.test.response.LoginResponse;
import com.aegis.test.service.AuthenticationService;
import com.aegis.test.service.EmailService;
import com.aegis.test.service.JwtService;
import com.aegis.test.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    @Autowired
	private UserService _service;

    @Autowired
	private EmailService emailService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        emailService.sendSimpleEmail(registeredUser.getEmail(),
         "Account Successfully Register",
          "please reset your password before you login, your password is: "+registerUserDto.getPassword());

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUserData(authenticatedUser);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/resetPassword")
	public Map<String, Object> resetPassword(@RequestBody ResetPasswordDto dto) {
		String message = _service.resetPassword(dto);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("status", 200);
		
		return result;
	}
}