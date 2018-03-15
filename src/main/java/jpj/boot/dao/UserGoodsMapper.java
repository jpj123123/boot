package jpj.boot.dao;

import jpj.boot.entity.UserGoods;
import org.apache.ibatis.annotations.Param;

public interface UserGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserGoods record);

    int insertSelective(UserGoods record);

    UserGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserGoods record);

    int updateByPrimaryKey(UserGoods record);

    UserGoods selectByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    void updateUserGoods(@Param("id") Long id, @Param("count") Long count);
}