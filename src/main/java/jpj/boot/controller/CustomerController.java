package jpj.boot.controller;

import jpj.boot.dto.SubmitCustomerDto;
import jpj.boot.entity.Customer;
import jpj.boot.entity.TEnum;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Resource
    private CustomerService customerService;
    @Resource
    private TEnumService tEnumService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/customer/list");
        TEnum tEnum = tEnumService.selectByCode("customerroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        return mav;
    }

    @ResponseBody
    @RequestMapping("/listAll")
    public List<Customer> listAll() {
        return customerService.listAll();
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/customer/add");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addCustomerSubmit")
    public boolean addCustomerSubmit(HttpServletRequest request, SubmitCustomerDto dto) {
        BeanValidator.validator(dto);
        Customer selCustomer = customerService.selectByPhone(dto.getPhone().trim());
        if (selCustomer != null) {
            throw new BuisnessException("用户已存在！");
        }
        logger.info("userId:"+ HttpSessionUtil.getUserId(request.getSession())+"新增用户--手机号："+ dto.getPhone());
        Customer customer = new Customer();
        customer.setAddress(dto.getAddress().trim());
        customer.setName(dto.getName().trim());
        customer.setPhone(dto.getPhone().trim());
        //customer.setUserId(dto.getUserId());
        customer.setMoney(dto.getMoney());
        return customerService.insertSelective(customer) > 0;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mav = new ModelAndView("/customer/edit");
        Customer customer = customerService.selectByPrimaryKey(id);
        if (customer == null) {
            throw new BuisnessException("用户不存在！");
        }
        mav.addObject("customer", customer);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/editCustomerSubmit")
    public boolean editCustomerSubmit(HttpServletRequest request, SubmitCustomerDto dto) {
        BeanValidator.validator(dto);
        Customer selCustomer = customerService.selectByPrimaryKey(dto.getId());
        if (selCustomer == null) {
            throw new BuisnessException("用户不存在！");
        }
        if (!StringUtils.equals(dto.getPhone(), selCustomer.getPhone())) {
            Customer phoneSelCustomer = customerService.selectByPhone(dto.getPhone().trim());
            if (phoneSelCustomer != null) {
                throw new BuisnessException("用户已存在！");
            }
        }
        logger.info("userId:"+ HttpSessionUtil.getUserId(request.getSession())+"更新用户：id"+ dto.getId());
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setAddress(dto.getAddress().trim());
        customer.setName(dto.getName().trim());
        customer.setPhone(dto.getPhone().trim());
        //customer.setUserId(dto.getUserId());
        customer.setMoney(dto.getMoney());
        return customerService.updateByPrimaryKeySelective(customer) > 0;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(HttpServletRequest request, Integer id) {
        logger.info("userId:"+ HttpSessionUtil.getUserId(request.getSession())+"删除用户：id"+ id);
        return customerService.deleteCustomerByPrimaryKey(id) > 0;
    }
}
