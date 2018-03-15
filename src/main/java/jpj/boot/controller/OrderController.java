package jpj.boot.controller;

import jpj.boot.entity.Goods;
import jpj.boot.entity.TEnum;
import jpj.boot.service.OrderService;
import jpj.boot.service.TEnumService;
import jpj.boot.util.HttpSessionUtil;
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
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private TEnumService tEnumService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/order/list");
        TEnum tEnum = tEnumService.selectByCode("orderroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        return mav;
    }
    @ResponseBody
    @RequestMapping("/listAll")
    public List<Goods> listAll(HttpServletRequest request){
        Long userId = HttpSessionUtil.getUserId(request.getSession());
        return null;//nuorderService.listAll();
    }
}
