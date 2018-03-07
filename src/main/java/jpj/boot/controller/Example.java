package jpj.boot.controller;

import jpj.boot.application.ApplicatoinConfig;
import jpj.boot.application.TestConfig;
import jpj.boot.domain.Customer;
import jpj.boot.dto.UserResetPasswordDto;
import jpj.boot.entity.User;
import jpj.boot.util.HttpSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/1/30
 */
//@ComponentScan
//@EnableAutoConfiguration
@Controller
//@ConfigurationProperties
//@EnableConfigurationProperties({ApplicatoinConfig.class, TestConfig.class})
@Slf4j
public class Example {
//    @Value("${my.test}")
//    private List<String> service;
//    @Resource
//    private ApplicatoinConfig config;
//    @Resource
//    private TestConfig testConfig;

    @RequestMapping("/login")
    public String home() {
        return "login";  //"Hello World!";
    }
    @RequestMapping("/")
    public String welcome() {
        return "redirect:login";  //"Hello World!";
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/login");
        //清除登录信息
        HttpSessionUtil.clearLoginSession(request.getSession());
        return mav;
    }

    @RequestMapping("/reset")
    public ModelAndView reset(UserResetPasswordDto dto) {
        ModelAndView mav = new ModelAndView();
        if(dto != null) {
            mav.addObject("dto", dto);
        }
        mav.setViewName("reset");
        return mav;
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, User user) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userName", user.getName());
        Integer id = (Integer) request.getSession().getAttribute(user.getName());
        log.info("-------index----" + user.getName()+"---id---"+ id);
        mav.setViewName("index");
        return mav;  //"Hello World!";
    }
}
