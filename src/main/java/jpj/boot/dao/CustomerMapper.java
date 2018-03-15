package jpj.boot.dao;

import jpj.boot.entity.Customer;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> listAll();

    Customer selectByPhone(String phone);

    int deleteCustomerByPrimaryKey(Long id);
}