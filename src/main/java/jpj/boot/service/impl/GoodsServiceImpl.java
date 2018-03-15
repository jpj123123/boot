package jpj.boot.service.impl;

import jpj.boot.dao.GoodsMapper;
import jpj.boot.entity.Goods;
import jpj.boot.entity.OutLib;
import jpj.boot.entity.User;
import jpj.boot.service.GoodsService;
import jpj.boot.service.OutLibService;
import jpj.boot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/12
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private OutLibService outLibService;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Long userId,Goods record) {
        Date current = new Date();
        record.setUpdateTime(current);
        record.setCreateTime(current);
        int InsertCou = goodsMapper.insertSelective(record);
        if(record.getCount() > 0L){
            outLibService.insert(false, record.getId(),record.getName(), record.getCount(), userId, userId, "添加商品时添加的初始库存记录");
        }
        return InsertCou;
    }

    @Override
    public Goods selectByPrimaryKey(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        Date current = new Date();
        record.setUpdateTime(current);
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        Date current = new Date();
        record.setUpdateTime(current);
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Goods> listAll() {
        return goodsMapper.listAll();
    }

    @Override
    public Goods selectByCode(String code) {
        return goodsMapper.selectByCode(code);
    }

    @Override
    public int deleteGoodsByPrimaryKey(Long id) {
        return goodsMapper.deleteGoodsByPrimaryKey(id);
    }
}
