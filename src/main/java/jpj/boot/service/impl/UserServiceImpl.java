package jpj.boot.service.impl;

import jpj.boot.dao.RoleMapper;
import jpj.boot.dao.UserMapper;
import jpj.boot.entity.Role;
import jpj.boot.entity.User;
import jpj.boot.service.UserService;
import jpj.boot.util.HttpSessionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/8
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public Role selectRoleByUserId(Integer id) {
        return roleMapper.selectRoleByUserId(id);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Override
    public boolean deleteUser(HttpServletRequest request, Integer id) {
        User user = selectByPrimaryKey(id);
        if(user == null)
            return true;
        roleMapper.deleteUserRoleByUserId(id);
        HttpSessionUtil.clearLoginSessionByUserName(request.getSession(), user.getName());
        //软删除
        User upUser = new User();
        upUser.setId(id);
        return userMapper.updateDelUser(upUser) > 0;
    }
    @Transactional
    @Override
    public boolean insertUserRole(Integer userId, Integer roleId) {
        roleMapper.deleteUserRoleByUserId(userId);
        roleMapper.insertUserRole(userId, roleId);
        return true;
    }
}
