package jpj.boot.dao;

import jpj.boot.dto.EnumDto;
import jpj.boot.entity.TEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TEnumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TEnum record);

    int insertSelective(TEnum record);

    TEnum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TEnum record);

    int updateByPrimaryKey(TEnum record);

    List<EnumDto> listChildByPidAndRoleId(@Param("pid") Integer pid, @Param("roleId") Integer roleId);

    Integer countChildByPidAndRoleId(@Param("pid") Integer pid, @Param("roleId") Integer roleId);

    List<TEnum> listByPid(Integer pid);

    Integer countByPid(Integer pid);

    TEnum selectByCode(String code);

    int deleteRoleEnumByEnumId(Integer enumId);

    List<EnumDto> listEnumDtoAllByPid(Integer pid);
}