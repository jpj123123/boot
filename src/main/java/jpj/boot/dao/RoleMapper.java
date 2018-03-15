package jpj.boot.dao;

import jpj.boot.dto.EnumIdDto;
import jpj.boot.entity.Role;
import jpj.boot.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role selectRoleByUserId(Long id);

    List<Role> listAll();

    void deleteRoleEnumByRoleId(Long roleId);

    void insertRoleEnums(@Param("roleId") Long roleId, @Param("enumIds") Long[] enumIds);

    int deleteUserRoleByUserId(Long userId);

    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}