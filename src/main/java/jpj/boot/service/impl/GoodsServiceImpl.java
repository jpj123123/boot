package jpj.boot.service.impl;

import jpj.boot.dao.GoodsMapper;
import jpj.boot.entity.Goods;
import jpj.boot.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/12
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
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
    public int deleteGoodsByPrimaryKey(Integer id) {
        return goodsMapper.deleteGoodsByPrimaryKey(id);
    }
}
