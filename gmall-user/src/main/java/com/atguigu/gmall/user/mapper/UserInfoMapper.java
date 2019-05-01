package com.atguigu.gmall.user.mapper;

import com.atguigu.gmall.bean.UserInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 继承tk.mybatis.mapper.common.Mapper接口，然后传入所需的bean “UserInfo”，那么在调用Mapper接口的时候就可以不用编写SQL
 * 语句了，比如直接调用userInfoMapper.selectAll()
 */
public interface UserInfoMapper extends Mapper<UserInfo> {
}
