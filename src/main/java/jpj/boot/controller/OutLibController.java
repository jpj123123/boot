package jpj.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jpj.boot.cache.UserCache;
import jpj.boot.dto.SubmitGoodsDto;
import jpj.boot.dto.SubmitOutLibDto;
import jpj.boot.dto.query.OutLibQuery;
import jpj.boot.entity.Goods;
import jpj.boot.entity.OutLib;
import jpj.boot.entity.TEnum;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.GoodsService;
import jpj.boot.service.OutLibService;
import jpj.boot.service.TEnumService;
import jpj.boot.util.BeanValidator;
import jpj.boot.util.DateHelper;
import jpj.boot.util.HttpSessionUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/outlib/list");
        TEnum tEnum = tEnumService.selectByCode("outlibroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        mav.addObject("date", DateHelper.formatDate(new Date(), DateHelper.pattern_date));
//        if(UserCache.CACHE_SALES != null){
//            mav.addObject("sales", UserCache.CACHE_SALES);
//        }
        return mav;
    }

    @ResponseBody
    @RequestMapping("/listAll")
    public PageInfo listAll(HttpServletRequest request) {
        String startDate = request.getParameter("datestart");
        String endDate = request.getParameter("dateend");
        Date start = DateHelper.getDateStart(DateHelper.parseString(startDate, DateHelper.pattern_date));
        Date end = DateHelper.getDateEnd(DateHelper.parseString(endDate, DateHelper.pattern_date));
        int pageNumber = NumberUtils.toInt(request.getParameter("pageNumber"), 1);
        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 20);
        PageHelper.startPage(pageNumber, pageSize);
        OutLibQuery outLibQuery = new OutLibQuery();
        outLibQuery.setStart(start);
        outLibQuery.setEnd(end);
        PageInfo pageInfo = new PageInfo(outLibService.listByQuery(outLibQuery));
        return pageInfo;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/outlib/add");
        List<Goods> list = goodsService.listAll();
        Long createUserId = HttpSessionUtil.getUserId(request.getSession());
        String userName = (String)request.getSession().getAttribute(request.getSession().getId());
        if (UserCache.CACHE_SALES != null) {

            mav.addObject("defaultId", createUserId);
            mav.addObject("defaultName", userName);
            mav.addObject("sales", UserCache.CACHE_SALES);
            mav.addObject("goods", list);
        }
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addOutLibSubmit")
    public boolean addOutLibSubmit(HttpServletRequest request, SubmitOutLibDto dto) {
        BeanValidator.validator(dto);
        Long createUserId = HttpSessionUtil.getUserId(request.getSession());
        Goods goods = goodsService.selectByPrimaryKey(dto.getGoodsId());
        if (goods == null) {
            throw new BuisnessException("商品不存在 ！");
        }
        Long goodsCount = Math.abs(dto.getGoodsCount());
        if (dto.getIsOut() == 1) {//出库数据为负数
            goodsCount = -goodsCount;
        }
        if(goods.getCount() + goodsCount < 0){
            throw new BuisnessException("库存不足");
        }
        Long userId = dto.getUserId() == 0 ? createUserId : dto.getUserId();
        outLibService.insertSubmit((dto.getIsOut() == 1), dto.getGoodsId(), goods.getName(), dto.getGoodsCount(), userId, createUserId, dto.getRemark());
        return true;
    }
}
