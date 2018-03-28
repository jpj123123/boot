package jpj.boot.dao;

import jpj.boot.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> listAll();

    Goods selectByCode(String code);

    int deleteGoodsByPrimaryKey(Long id);

    int updateCount(@Param("goodsId") Long goodsId, @Param("goodsCount") Long goodsCount);
}