package com.example.pp_3.security;

import com.example.pp_3.oauth.CustomOauthUser;
import com.example.pp_3.oauth.CustomOauthUserService;
import com.example.pp_3.security.handler.LoginSuccessHandler;
import com.example.pp_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    UserService userService;
    CustomOauthUserService oauthUserService;

    @Autowired
    public void setOauthUserService(CustomOauthUserService oauthUserService) {
        this.oauthUserService = oauthUserService;
    }

    @Autowired
    public void setUserService( UserService userService) {
        this.userService = userService;
    }


    @Bean
    public AuthenticationSuccessHandler successLoginHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/oauth2/**").permitAll();

        http.formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("email")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .and()
                .oauth2Login()
                    .loginPage("/login")
                    .userInfoEndpoint().userService(oauthUserService)
                    .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException {
                        CustomOauthUser oauthUser = (CustomOauthUser) authentication.getPrincipal();
                        System.out.println("=======================================  onAuthenticationSuccess =================================");
                        userService.processOAuthPostLogin(oauthUser.getEmail());
                        response.sendRedirect("/index");
                    }
                })
                .defaultSuccessUrl("/index")
                .and().cors()
                .and().csrf().disable();

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().csrf().disable();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
