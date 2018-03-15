package jpj.boot.dao;

import jpj.boot.dto.EnumDto;
import jpj.boot.entity.TEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TEnumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TEnum record);

    int insertSelective(TEnum record);

    TEnum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TEnum record);

    int updateByPrimaryKey(TEnum record);

    List<EnumDto> listChildByPidAndRoleId(@Param("pid") Long pid, @Param("roleId") Long roleId);

    Integer countChildByPidAndRoleId(@Param("pid") Long pid, @Param("roleId") Long roleId);

    List<TEnum> listByPid(Long pid);

    Integer countByPid(Long pid);

    TEnum selectByCode(String code);

    int deleteRoleEnumByEnumId(Long enumId);

    List<EnumDto> listEnumDtoAllByPid(Long pid);
}