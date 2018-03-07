package jpj.boot.service;

import jpj.boot.dto.AddRoleEnumSubmitDto;
import jpj.boot.entity.Role;

import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/5
 */
public interface RoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role selectRoleByUserId(Integer id);

    List<Role> listAll();

    boolean addRoleEnumSubmit(Integer roleId, Integer[] enumIds);
}
