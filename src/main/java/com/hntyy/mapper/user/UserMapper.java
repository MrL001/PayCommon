package com.hntyy.mapper.user;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺表
 */
@Mapper
public interface UserMapper {

    /**
     * 通过用户名获取用户信息
     * @param userName
     * @return
     */
    UserEntity findUserByUserName(@Param("userName")String userName);


}
