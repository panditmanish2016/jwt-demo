package com.jwt.demo.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.demo.base.model.BaseMessage;
import com.jwt.demo.service.CustomUserDetailsService;
import com.jwt.demo.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String username = null;
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(token);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {                                                                                   
                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (SignatureException signatureException) {
            LOGGER.trace("Invalid Token Exeption");
            writeErrorToWeb(httpServletRequest, httpServletResponse, "403", "Invalid Token");
        } catch (ExpiredJwtException expiredJwtException) {
            LOGGER.trace("Token is Expired");
            writeErrorToWeb(httpServletRequest, httpServletResponse, "401", "Token is Expired");
        } catch (NullPointerException nullPointerException) {
            LOGGER.trace("Token Not Found");
            writeErrorToWeb(httpServletRequest, httpServletResponse, "404", "Token Not Found");
        } catch (Exception exception) {
            LOGGER.trace("Internal Error");
            writeErrorToWeb(httpServletRequest, httpServletResponse, "500", "Internal Error");
        }
    }

    private void writeErrorToWeb(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            String code, String message) throws ServletException, JsonProcessingException, IOException {
        LOGGER.trace("inside writeErrorToWeb()...");
        httpServletResponse.setStatus(Integer.valueOf(code));
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setCode(code);
        if (httpServletRequest.getHeader("Authorization") == null) {
            baseMessage.setMessage("Please Provide Authorization Token");
        } else {
            baseMessage.setMessage(httpServletRequest.getHeader("Authorization"));
        }
        baseMessage.setType(message);
        httpServletResponse.setContentType("application/json");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(new ObjectMapper().writeValueAsString(baseMessage));
        printWriter.flush();
    }
}
