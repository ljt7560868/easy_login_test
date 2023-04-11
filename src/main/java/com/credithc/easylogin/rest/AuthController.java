package com.credithc.easylogin.rest;

import com.credithc.easylogin.common.Result;
import com.credithc.easylogin.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }


    @GetMapping("/do")
    public Result auth(@CookieValue(name = "AccessToken",required = false) String token) {
        if (StringUtils.equals(this.token, token)) {
            return Result.ok();
        }
        log.error("token is illegal, token:{}", token);
        return Result.fail(ResultCode.EC_401_ERROR);
    }


    @GetMapping("/login")
    public Result login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                        HttpServletResponse response) {
        if (StringUtils.equals(this.username, username) && StringUtils.equals(this.password, password)) {
            log.info("login success, username:{}", username);
            Cookie cookie = new Cookie("AccessToken", this.token);
            response.addCookie(cookie);
            return Result.ok();
        }
        log.error("login fail, username/password is illegal, username:{},password:{}", username, password);
        return Result.fail(ResultCode.EC_400_ERROR);
    }


}
