package com.jwt.demo.controller;

import java.util.Arrays;

import com.jwt.demo.base.model.BaseMessage;
import com.jwt.demo.base.model.BaseResponse;
import com.jwt.demo.model.UserAuthRequest;
import com.jwt.demo.model.UserAuthResponse;
import com.jwt.demo.service.CustomUserDetailsService;
import com.jwt.demo.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

        private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

        @Autowired
        private JwtUtil jwtUtil;
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        @PostMapping("/token")
        public ResponseEntity<BaseResponse<UserAuthResponse>> generateToken(
                        @RequestBody UserAuthRequest userLoginRequest) throws Exception {
                LOGGER.trace("inside generateToken...");
                try {
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                        userLoginRequest.getUsername(), userLoginRequest.getPassword()));
                } catch (Exception ex) {
                        return new ResponseEntity<>(
                                        BaseResponse.<UserAuthResponse>builder().success(false)
                                                        .messages(Arrays.asList(BaseMessage.builder()
                                                                        .message("inavalid username or password")
                                                                        .type("N").code("403").build()))
                                                        .data(null).build(),
                                        HttpStatus.OK);
                }
                UserAuthResponse userAuthResponse = new UserAuthResponse();
                final UserDetails userDetails = customUserDetailsService
                                .loadUserByUsername(userLoginRequest.getUsername());
                userAuthResponse.setToken("Bearer " + jwtUtil.generateToken(userDetails));
                return new ResponseEntity<>(BaseResponse.<UserAuthResponse>builder().success(true).messages(null)
                                .data(userAuthResponse).build(), HttpStatus.OK);
        }
}