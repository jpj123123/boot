package jpj.boot.service.impl;

import jpj.boot.dao.UserGoodsLogMapper;
import jpj.boot.dao.UserGoodsMapper;
import jpj.boot.entity.UserGoods;
import jpj.boot.entity.UserGoodsLog;
import jpj.boot.service.UserGoodsService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
@Service
public class UserGoodsServiceImpl implements UserGoodsService, ApplicationContextAware {
    @Resource
    private UserGoodsMapper userGoodsMapper;
    @Resource
    private UserGoodsLogMapper userGoodsLogMapper;
    @Transactional
    @Override
    public int updateUserGoods(Long goodsId, Long userId, Long count, Long customerId) {
        UserGoods userGoods = userGoodsMapper.selectByUserIdAndGoodsId(userId,goodsId);
        int upcount=0;
        if(userGoods == null){//新增
            userGoods = new UserGoods();
            userGoods.setGoodsId(goodsId);
            userGoods.setGoodsCount(count);
            userGoods.setUserId(userId);
            upcount = this.insert(userGoods);
        }else{//修改
            userGoodsMapper.updateUserGoods(userGoods.getId(), count);
        }
        this.insertLog(goodsId,userId,count,customerId);
        return upcount;
    }

    @Override
    public int insert(UserGoods record) {
        Date current = new Date();
        record.setCreateTime(current);
        record.setUpdateTime(current);
        return userGoodsMapper.insert(record);
    }

    private int insertLog(Long goodsId, Long userId, Long count, Long customerId) {
        Date current = new Date();
        UserGoodsLog userGoodsLog = new UserGoodsLog();
        userGoodsLog.setCustomerId(customerId);
        userGoodsLog.setGoodsId(goodsId);
        userGoodsLog.setUserId(userId);
        userGoodsLog.setGoodsCount(count);
        userGoodsLog.setCreateTime(current);
        userGoodsLog.setUpdateTime(current);

        return userGoodsLogMapper.insert(userGoodsLog);
    }


    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
