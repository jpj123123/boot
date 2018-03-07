package jpj.boot.dao;

import jpj.boot.entity.Customer;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> listAll();

    Customer selectByPhone(String phone);

    int deleteCustomerByPrimaryKey(Integer id);
}