package com.credithc.easylogin.rest;

import com.credithc.easylogin.common.Result;
import com.credithc.easylogin.common.ResultCode;
import com.credithc.easylogin.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-04-06 14:12
 * @Description: 认证
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AppConfig appConfig;

    @RequestMapping()
    public ModelAndView view() {
        log.info("logining--------");
        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }


    @GetMapping("/do")
    public ResponseEntity<?> auth(@CookieValue(name = "AccessToken", required = false) String token) {
        if (StringUtils.equals(appConfig.getToken(), token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("token is illegal, token:{}", token);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/login")
    public Result login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        HttpServletResponse response) throws IOException {
        Map<String, String> userInfoMap = appConfig.getUserInfoMap();
        if (userInfoMap.isEmpty()) {
            log.error("用户信息未配置");
        }
        if (userInfoMap.keySet().contains(username)
                && StringUtils.equals(userInfoMap.get(username), password)) {
            log.info("login success, username:{}", username);
            Cookie cookie = new Cookie("AccessToken", appConfig.getToken());
            cookie.setMaxAge(60 * 60 * 2);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("/");
            return Result.ok();
        } else {
            log.error("login fail, username/password is illegal, username:{},password:{}", username, password);
            return Result.fail(ResultCode.EC_400_ERROR);
        }
    }

}