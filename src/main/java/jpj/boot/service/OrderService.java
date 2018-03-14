package jpj.boot.service;

import jpj.boot.entity.Order;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/14
 */
public interface OrderService {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
