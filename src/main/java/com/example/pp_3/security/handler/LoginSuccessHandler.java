package com.example.pp_3.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> auths = authentication.getAuthorities().stream()
                .map(el -> el.getAuthority())
                .collect(Collectors.toSet());
        if(auths.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin");
        } else if(auths.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user");
        }
    }

}
