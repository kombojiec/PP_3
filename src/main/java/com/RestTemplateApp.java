package com;

import com.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestTemplateApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RestTemplateApp.class, args);
        UserService userService = ctx.getBean(UserService.class);

        System.out.println(userService.getAllUsers());
        System.out.println(userService.addUser());
        System.out.println(userService.editUser());
        System.out.println(userService.deleteUser());
    }
}