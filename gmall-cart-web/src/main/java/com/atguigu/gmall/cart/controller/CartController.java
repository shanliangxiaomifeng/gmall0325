package com.atguigu.gmall.cart.controller;

import com.atguigu.gmall.annotation.LoginRequire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CartController {

    @RequestMapping("/toTrade")
    @ResponseBody
    @LoginRequire
//    public String toTrade(HttpServletRequest request, HttpServletResponse response, ModelMap map){
    public String toTrade(){
        String userId = "";

        //需要被单点登录的拦截器
        return "toTrade";
    }
}
