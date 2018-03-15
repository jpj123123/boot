package jpj.boot.controller;

import jpj.boot.dto.SubmitGoodsDto;
import jpj.boot.dto.SubmitOutLibDto;
import jpj.boot.entity.Goods;
import jpj.boot.entity.TEnum;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.GoodsService;
import jpj.boot.service.OutLibService;
import jpj.boot.service.TEnumService;
import jpj.boot.util.BeanValidator;
import jpj.boot.util.HttpSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
@Controller
@RequestMapping("/outlib")
public class OutLibController {
    @Resource
    private OutLibService outLibService;
    @Resource
    private TEnumService tEnumService;
    @Resource
    private GoodsService goodsService;
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/outlib/list");
        TEnum tEnum = tEnumService.selectByCode("outlibroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        return mav;
    }
    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/goods/add");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addOutLibSubmit")
    public boolean addOutLibSubmit(HttpServletRequest request, SubmitOutLibDto dto) {
        BeanValidator.validator(dto);
        Long createUserId = HttpSessionUtil.getUserId(request.getSession());
        Goods goods = goodsService.selectByPrimaryKey(dto.getGoodsId());
        if(goods == null){
            throw new BuisnessException("商品不存在 ！");
        }
        outLibService.insert(dto.isOut(), dto.getGoodsId(), goods.getName(), dto.getGoodsCount(), dto.getUserId(), createUserId, dto.getRemark());
        return false;
    }
}
