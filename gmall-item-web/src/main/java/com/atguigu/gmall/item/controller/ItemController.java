package com.atguigu.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.SkuInfo;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    @Reference
    SkuService skuService;

    @RequestMapping("/index")
    public String demo(ModelMap map){

        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (int i = 0; i < 6; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("小" + i);
            userInfo.setPhoneNum("122222222222");
            userInfos.add(userInfo);
        }

        map.put("userInfos", userInfos);
        map.put("hello", "hello thymeleaf");

        return "item";
    }

    @RequestMapping("/{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap map){
        SkuInfo skuInfo = skuService.getSkuById(skuId);
        map.put("skuInfo", skuInfo);
        return "item";
    }
}
