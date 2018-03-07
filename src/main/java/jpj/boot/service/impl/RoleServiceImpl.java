package jpj.boot.service.impl;

import jpj.boot.dao.RoleMapper;
import jpj.boot.dto.AddRoleEnumSubmitDto;
import jpj.boot.entity.Role;
import jpj.boot.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/5
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public Role selectRoleByUserId(Integer id) {
        return roleMapper.selectRoleByUserId(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }
    @Transactional
    @Override
    public boolean addRoleEnumSubmit(Integer roleId, Integer[] enumIds) {
        roleMapper.deleteRoleEnumByRoleId(roleId);
        roleMapper.insertRoleEnums(roleId, enumIds);
        return true;
    }
}
