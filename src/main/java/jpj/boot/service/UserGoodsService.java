package jpj.boot.service;

import jpj.boot.entity.UserGoods;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
public interface UserGoodsService {
    int updateUserGoods(Long goodsId,Long userId, Long count,Long customerId);
    int insert(UserGoods record);
}
