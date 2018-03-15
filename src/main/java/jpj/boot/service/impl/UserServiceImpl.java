package jpj.boot.service.impl;

import jpj.boot.cache.UserCache;
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
import java.util.Date;
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
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        Date current = new Date();
        record.setCreateTime(current);
        record.setUpdateTime(current);
        int count = userMapper.insertSelective(record);

        //更新缓存
        UserCache.UPCACHE(record);
        return count;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        record.setUpdateTime(new Date());
        int count= userMapper.updateByPrimaryKeySelective(record);
        //更新缓存
        UserCache.UPCACHE(record);
        return count;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public Role selectRoleByUserId(Long id) {
        return roleMapper.selectRoleByUserId(id);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Override
    public boolean deleteUser(HttpServletRequest request, Long id) {
        User user = selectByPrimaryKey(id);
        if(user == null)
            return true;
        roleMapper.deleteUserRoleByUserId(id);
        HttpSessionUtil.clearLoginSessionByUserName(request.getSession(), user.getName());
        //更新缓存
        UserCache.UPCACHE(user);
        //软删除
        User upUser = new User();
        upUser.setId(id);
        return userMapper.updateDelUser(upUser) > 0;
    }
    @Transactional
    @Override
    public boolean insertUserRole(Long userId, Long roleId) {
        roleMapper.deleteUserRoleByUserId(userId);
        roleMapper.insertUserRole(userId, roleId);
        return true;
    }

    @Override
    public List<User> listAllSaleUser() {
        return userMapper.listAllSaleUser();
    }
}
