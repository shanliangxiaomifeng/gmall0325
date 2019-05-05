package com.atguigu.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/userInfoList")
    public ResponseEntity<List<UserInfo>> userInfoList(){
        List<UserInfo> userInfoList = userService.userInfoList();
        return ResponseEntity.ok(userInfoList);
    }

    @RequestMapping("/addUserInfo")
    public void addUserInfo(HttpServletResponse response) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName("lizan");
        userInfo.setNickName("Paul");
        userInfo.setPasswd("12345");
        userInfo.setName("赞哥");
        userInfo.setPhoneNum("123456789");
        userInfo.setEmail("163874@mali.com");
        userInfo.setUserLevel("1");

        userService.addUserInfo(userInfo);
        response.sendRedirect("/userInfoList");
    }

    @RequestMapping("/deleteUserInfo/{id}")
    public void deleteUserInfo(@PathVariable(value = "id") String id, HttpServletResponse response) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userService.deleteUserInfo(userInfo);
        response.sendRedirect("/userInfoList");
    }

    @RequestMapping("/updateUserInfo/{id}/{name}")
    public void updateUserInfo(@PathVariable("id") String id, @PathVariable("name") String name, HttpServletResponse response) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName(name);

        userService.updateUserInfo(userInfo);
        response.sendRedirect("/userInfoList");
    }

    @RequestMapping("/userAddressList")
    public ResponseEntity<List<UserAddress>> userAddressList(){
        return ResponseEntity.ok(userService.userAddressList());
    }

    @RequestMapping("/addUserAddress")
    public void addUserAddress(HttpServletResponse response){
        UserAddress userAddress = new UserAddress();
        userAddress.setUserAddress("朝阳区大望路1号");
        userAddress.setUserId("2");
        userAddress.setConsignee("晨叔");
        userAddress.setPhoneNum("13456789012");
        userAddress.setIsDefault("0");

        userService.addUserAddress(userAddress);
        try {
            response.sendRedirect("/userAddressList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteUserAddress/{id}")
    public void deleteUserAddress(@PathVariable("id") String id, HttpServletResponse response) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        userService.deleteUserAddress(userAddress);
        try {
            response.sendRedirect("/userAddressList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateUserAddress/{id}/{cosignee}")
    public void updateUserAddress(@PathVariable("id") String id, @PathVariable("cosignee") String consignee, HttpServletResponse response){
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        userAddress.setConsignee(consignee);
        userService.updateUserAddress(userAddress);

        try {
            response.sendRedirect("/userAddressList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
