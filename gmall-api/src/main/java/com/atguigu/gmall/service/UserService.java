package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> userInfoList();
    int addUserInfo(UserInfo userInfo);
    int deleteUserInfo(UserInfo userInfo);
    int updateUserInfo(UserInfo userInfo);

    List<UserAddress> userAddressList();
    int addUserAddress(UserAddress userAddress);
    int deleteUserAddress(UserAddress userAddress);
    int updateUserAddress(UserAddress userAddress);
}
