package com.hntyy.service.user;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.entity.user.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /**
     * 通过用户名获取用户信息
     * @param userName
     * @return
     */
    UserEntity findUserByUserName(@Param("userName")String userName);

}
