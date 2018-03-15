package jpj.boot.service;

import jpj.boot.entity.Order;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/14
 */
public interface OrderService {

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
