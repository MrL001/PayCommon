package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 店铺表
 */
@Mapper
public interface ShopMapper {

    /**
     * 食堂id查店铺，分页
     * @param dcwmOrderQuery
     * @return
     */
    List<ShopEntity> findShopByCanteenIdPage(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 食堂id查店铺，分页。总数
     * @param dcwmOrderQuery
     * @return
     */
    int findShopCountByCanteenId(DcwmOrderQuery dcwmOrderQuery);

}
