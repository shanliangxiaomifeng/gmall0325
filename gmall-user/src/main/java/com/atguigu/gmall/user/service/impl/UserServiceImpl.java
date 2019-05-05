package com.atguigu.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.user.mapper.UserAddressMapper;
import com.atguigu.gmall.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public List<UserInfo> userInfoList() {
        return userInfoMapper.selectAll();
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public int deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.delete(userInfo);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {

        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    //关于UserAddress的增删改查方法
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> userAddressList() {
        return userAddressMapper.selectAll();
    }

    @Override
    public int addUserAddress(UserAddress userAddress) {
        return userAddressMapper.insert(userAddress);
    }

    @Override
    public int deleteUserAddress(UserAddress userAddress) {
        return userAddressMapper.delete(userAddress);
    }

    @Override
    public int updateUserAddress(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }




}
