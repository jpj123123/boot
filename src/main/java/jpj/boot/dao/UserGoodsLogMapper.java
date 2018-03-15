package jpj.boot.dao;

import jpj.boot.entity.UserGoodsLog;

public interface UserGoodsLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserGoodsLog record);

    int insertSelective(UserGoodsLog record);

    UserGoodsLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserGoodsLog record);

    int updateByPrimaryKey(UserGoodsLog record);
}