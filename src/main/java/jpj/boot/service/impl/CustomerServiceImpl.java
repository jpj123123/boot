package jpj.boot.service.impl;

import jpj.boot.dao.CustomerMapper;
import jpj.boot.entity.Customer;
import jpj.boot.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/7
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Customer record) {
        return customerMapper.insert(record);
    }

    @Override
    public int insertSelective(Customer record) {
        return customerMapper.insertSelective(record);
    }

    @Override
    public Customer selectByPrimaryKey(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Customer record) {
        return customerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Customer record) {
        return customerMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Customer> listAll() {
        return customerMapper.listAll();
    }

    @Override
    public Customer selectByPhone(String phone) {
        return customerMapper.selectByPhone(phone);
    }

    @Override
    public int deleteCustomerByPrimaryKey(Integer id) {
        return customerMapper.deleteCustomerByPrimaryKey(id);
    }
}
