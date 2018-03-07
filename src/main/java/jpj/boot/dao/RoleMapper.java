package jpj.boot.dao;

import jpj.boot.dto.EnumIdDto;
import jpj.boot.entity.Role;
import jpj.boot.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role selectRoleByUserId(Integer id);

    List<Role> listAll();

    void deleteRoleEnumByRoleId(Integer roleId);

    void insertRoleEnums(@Param("roleId") Integer roleId, @Param("enumIds") Integer[] enumIds);

    int deleteUserRoleByUserId(Integer userId);

    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}