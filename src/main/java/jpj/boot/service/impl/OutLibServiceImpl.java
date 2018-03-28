package jpj.boot.service.impl;

import jpj.boot.cache.UserCache;
import jpj.boot.dao.OutLibMapper;
import jpj.boot.dto.query.OutLibQuery;
import jpj.boot.entity.OutLib;
import jpj.boot.entity.User;
import jpj.boot.service.GoodsService;
import jpj.boot.service.OutLibService;
import jpj.boot.service.UserGoodsService;
import jpj.boot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
@Service
public class OutLibServiceImpl implements OutLibService {
    @Resource
    private OutLibMapper outLibMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserGoodsService userGoodsService;
    @Resource
    private GoodsService goodsService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return outLibMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int insert(boolean isOut, Long goodsId, String goodsName, Long goodsCount, Long userId, Long createUserId, String remark) {
        OutLib outLib = new OutLib();
        outLib.setGoodsId(goodsId);
        outLib.setGoodsName(goodsName);
        goodsCount = Math.abs(goodsCount);
        if (isOut) {//出库数据为负数
            goodsCount = -goodsCount;
        }
        outLib.setGoodsCount(goodsCount);
        outLib.setUserId(userId);
        User user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            outLib.setUserName(user.getName());
        }

        outLib.setIsout(isOut);//入库
        outLib.setRemark(remark);
        outLib.setCreateUserId(createUserId);
        outLib.setCreateTime(new Date());
        if (UserCache.Contain(userId)) {
            userGoodsService.updateUserGoods(goodsId, userId, -goodsCount, 0L);
        }
        return this.insert(outLib);
    }

    @Override
    public int insert(OutLib record) {
        return outLibMapper.insert(record);
    }

    @Override
    public int insertSelective(OutLib record) {
        return outLibMapper.insertSelective(record);
    }

    @Override
    public OutLib selectByPrimaryKey(Long id) {
        return outLibMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OutLib record) {
        return outLibMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OutLib record) {
        return outLibMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<OutLib> listByQuery(OutLibQuery outLibQuery) {
        return outLibMapper.listByQuery(outLibQuery);
    }

    @Transactional
    @Override
    public int insertSubmit(boolean isOut, Long goodsId, String name, Long goodsCount, Long userId, Long createUserId, String remark) {
        goodsCount = Math.abs(goodsCount);
        if (isOut) {//出库数据为负数
            goodsCount = -goodsCount;
        }
        goodsService.updateCount(goodsId, goodsCount);
        return insert(isOut, goodsId, name, goodsCount, userId, createUserId, remark);
    }
}
