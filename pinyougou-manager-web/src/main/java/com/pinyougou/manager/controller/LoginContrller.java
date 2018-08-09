package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录请求信息处理类
 *
 * @Author Giovani
 * @Create: 2018/8/9 14:41
 */
@RestController()
@RequestMapping("/login")
public class LoginContrller {

    /**
     * 获取当前登录的用户名
     *
     * @param
     * @return java.util.Map
     * @Author Giovani
     * @Date 2018/8/9 14:43
     */
    @RequestMapping("/name")
    public Map name() {
        // 是通过spring-security框架进行的登录认证，所以通过该框架获取当前登录的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Map map = new HashMap();
        map.put("loginName", name);
        return map;
    }
}
