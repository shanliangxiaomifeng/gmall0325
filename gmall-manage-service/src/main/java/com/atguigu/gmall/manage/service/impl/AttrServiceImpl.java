package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.bean.BaseAttrValue;
import com.atguigu.gmall.manage.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.manage.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);

        List<BaseAttrInfo> list = baseAttrInfoMapper.select(baseAttrInfo);
        return list;
    }

    @Override
    public void saveAttr(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.insertSelective(baseAttrInfo);

        List<BaseAttrValue> list = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue baseAttrValue : list) {
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insert(baseAttrValue);
        }
    }

    /**
     * 根据属性信息的id，查询具体的属性值
     * @param attrInfoId
     * @return
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrInfoId) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrInfoId);
        baseAttrValueMapper.select(baseAttrValue);
        return baseAttrValueMapper.select(baseAttrValue);
    }

    @Override
    public void updateAttrValue(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        for (BaseAttrValue baseAttrValue : baseAttrInfo.getAttrValueList()) {
            if (baseAttrValue.getId() == null) {
                baseAttrValueMapper.insert(baseAttrValue);
            } else {
                baseAttrValueMapper.updateByPrimaryKey(baseAttrValue);
            }
        }
    }

    @Override
    public void deleteAttr(BaseAttrInfo baseAttrInfo){
        baseAttrInfoMapper.delete(baseAttrInfo);
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);
    }

    @Override
    public void deleteAttrValue(BaseAttrValue baseAttrValue) {
        baseAttrValueMapper.delete(baseAttrValue);
    }
}
