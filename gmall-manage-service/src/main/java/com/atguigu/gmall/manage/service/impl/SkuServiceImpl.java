package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.SkuImage;
import com.atguigu.gmall.bean.SkuInfo;
import com.atguigu.gmall.manage.mapper.SkuImageMapper;
import com.atguigu.gmall.manage.mapper.SkuInfoMapper;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public SkuInfo getSkuById(String skuId) {
        Jedis jedis = redisUtil.getJedis();
        SkuInfo skuInfo = null;
        //先从缓存中查询
        String key = "sku:" + skuId + ":info";
        String valu = jedis.get(key);
        if ("empty".equals(valu)) {
            return skuInfo;
        }


        if (StringUtils.isBlank(valu)) {
            //申请缓存锁
            String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 30000);
            if ("OK".equals(OK)) {
                //查询DB
                skuInfo = getSkuByIdFromDb(skuId);
                if (skuInfo != null) {
                    //同步缓存
                    jedis.set(key, JSON.toJSONString(skuInfo));
                } else {
                    //通知伙伴
                    jedis.setex("sku:" + skuId + ":info", 10, "empty");
                }

                //归还锁
                jedis.del("sku:" + skuId + ":lock");
            } else { //没有拿到缓存锁
                //自旋
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getSkuById(skuId);
            }
        } else {
            skuInfo = JSON.parseObject(valu, SkuInfo.class);
        }

        return skuInfo;
    }

    private SkuInfo getSkuByIdFromDb(String skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        SkuInfo skuInfo1 = skuInfoMapper.selectOne(skuInfo);

        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        List<SkuImage> skuImageList = skuImageMapper.select(skuImage);
        SkuImage skuImage1 = skuImageMapper.selectOne(skuImage);

        skuInfo1.setSkuImageList(skuImageList);

        return skuInfo1;
    }
}
