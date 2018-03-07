package jpj.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 收款管理
 * @Author: jingpj
 * @Date：creste in 2018/3/7
 */
@Controller
@RequestMapping("/receipt")
public class ReceiptController {
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/receipt/list");
        return mav;
    }
}
