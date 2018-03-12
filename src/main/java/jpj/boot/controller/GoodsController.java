package jpj.boot.controller;

import jpj.boot.dto.SubmitGoodsDto;
import jpj.boot.entity.Goods;
import jpj.boot.entity.TEnum;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.GoodsService;
import jpj.boot.service.TEnumService;
import jpj.boot.util.BeanValidator;
import jpj.boot.util.HttpSessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/7
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private GoodsService goodsService;
    @Resource
    private TEnumService tEnumService;
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/goods/list");
        TEnum tEnum = tEnumService.selectByCode("goodsroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        return mav;
    }
    @ResponseBody
    @RequestMapping("/listAll")
    public List<Goods> listAll(){
        return goodsService.listAll();
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/goods/add");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addGoodsSubmit")
    public boolean addGoodsSubmit(HttpServletRequest request, SubmitGoodsDto dto) {
        BeanValidator.validator(dto);
        goodsService.selectByCode(dto.getCode());
        logger.info("userId:"+ HttpSessionUtil.getUserId(request.getSession())+"新增商品--手机号："+ dto.getName());
       Goods goods = new Goods();
       //todo
        return goodsService.insertSelective(goods) > 0;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mav = new ModelAndView("/goods/edit");
        Goods goods = goodsService.selectByPrimaryKey(id);
        if (goods == null) {
            throw new BuisnessException("用户不存在！");
        }
        mav.addObject("goods", goods);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/editGoodsSubmit")
    public boolean editGoodsSubmit(HttpServletRequest request, SubmitGoodsDto dto) {
        BeanValidator.validator(dto);
        Goods selGoods = goodsService.selectByPrimaryKey(dto.getId());
        if (selGoods == null) {
            throw new BuisnessException("用户不存在！");
        }
        if (StringUtils.equals(selGoods.getCode(), dto.getCode())) {
            throw new BuisnessException("编码不允许修改！");
        }
        logger.info("userId:"+ HttpSessionUtil.getUserId(request.getSession())+"更新用户：id"+ dto.getId());
        Goods goods = new Goods();
        //todo
        return goodsService.updateByPrimaryKeySelective(goods) > 0;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(HttpServletRequest request, Integer id) {
        logger.info("userId:"+ HttpSessionUtil.getUserId(request.getSession())+"删除用户：id"+ id);
        return goodsService.deleteGoodsByPrimaryKey(id) > 0;
    }
}
