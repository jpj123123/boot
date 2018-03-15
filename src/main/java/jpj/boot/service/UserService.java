package jpj.boot.service;

import jpj.boot.entity.Role;
import jpj.boot.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/8
 */
public interface UserService {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Role selectRoleByUserId(Long id);

    List<User> listUser();

    boolean deleteUser(HttpServletRequest request, Long id);

    boolean insertUserRole(Long userId, Long roleId);

    List<User> listAllSaleUser();
}
