package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.bean.BaseAttrValue;

import java.util.List;

public interface AttrService {
    List<BaseAttrInfo> getAttrList(String catelog3Id);

    void saveAttr(BaseAttrInfo baseAttrInfo);

    List<BaseAttrValue> getAttrValueList(String attrInfoId);

    void updateAttrValue(BaseAttrInfo baseAttrInfo);

    void deleteAttr(BaseAttrInfo baseAttrInfo);

    void deleteAttrValue(BaseAttrValue baseAttrValue);
}
