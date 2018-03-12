package jpj.boot.service;

import jpj.boot.entity.Goods;

import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/12
 */
public interface GoodsService {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> listAll();

    Goods selectByCode(String code);

    int deleteGoodsByPrimaryKey(Integer id);
}
