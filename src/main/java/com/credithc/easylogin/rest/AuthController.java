package com.credithc.easylogin.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-04-06 14:12
 * @Description: 认证
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Value("${login.username}")
    private String username;

    @Value("${login.password}")
    private String password;

    @Value("${login.token}")
    private String token;

    @RequestMapping()
    public ModelAndView view() {
        log.info("logining--------");
        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }


    @GetMapping("/do")
    public ResponseEntity<?> auth(@CookieValue(name = "AccessToken",required = false) String token) {
        if (StringUtils.equals(this.token, token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("token is illegal, token:{}", token);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/login")
    public void login(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                        HttpServletResponse response) throws IOException {
        if (StringUtils.equals(this.username, username) && StringUtils.equals(this.password, password)) {
            log.info("login success, username:{}", username);
            Cookie cookie = new Cookie("AccessToken", this.token);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            log.error("login fail, username/password is illegal, username:{},password:{}", username, password);
        }
        response.sendRedirect("/");
    }


}