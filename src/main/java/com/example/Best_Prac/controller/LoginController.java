package com.example.Best_Prac.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Best_Prac.JWT.JwtUtil;
import com.example.Best_Prac.Service.LoginService;
import com.example.Best_Prac.dto.LoginDTO;

//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.Best_Prac.JWT.JwtUtil;
//import com.example.Best_Prac.JWT.Service.LoginService;
//import com.example.Best_Prac.dto.LoginDTO;
//
//@RestController
//public class LoginController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<Object> authenticateUser(@RequestBody LoginDTO loginDTO) {
//        try {
//            authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
//            );
//
//            UserDetails userDetails = loginService.loadUserByUsername(loginDTO.getEmail());
//            String jwt = jwtUtil.generateToken(userDetails);
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("jwt", jwt);
//            response.put("status", HttpStatus.OK.value());
//            return ResponseEntity.ok(response);
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
//        }
//    }
//}


//---------------------------------------------------------------------------------------------------------------------------------
@RestController
public class LoginController{
	public LoginController(AuthenticationManager auth, JwtUtil jwtUtil, LoginService loginService) {
		this.auth = auth;
		this.jwtUtil = jwtUtil;
		this.loginService = loginService;
	}

	AuthenticationManager auth;

	JwtUtil jwtUtil;

	LoginService loginService;
	@PostMapping("/login")
	public ResponseEntity<Object> authenticateUser(@RequestBody LoginDTO loginDto) {
		try {
			Authentication authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
					loginDto.getPassword()));

			if (authentication.isAuthenticated()) {

				UserDetails userdetails = loginService.loadUserByUsername(loginDto.getEmail());

				String token = jwtUtil.generateToken(userdetails);

				Map<String, Object> resp = new HashMap<>();
				resp.put("token", token);
				resp.put("status", HttpStatus.OK.value());
				return ResponseEntity.ok(resp);
			} else {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
			}
		} catch (UsernameNotFoundException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
		}
	}

}


