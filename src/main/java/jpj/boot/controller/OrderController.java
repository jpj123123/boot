package jpj.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/7
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/order/list");
        return mav;
    }
}
