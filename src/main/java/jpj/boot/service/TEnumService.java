package jpj.boot.service;

import jpj.boot.dto.EnumDto;
import jpj.boot.dto.SubmitEnumDto;
import jpj.boot.entity.TEnum;

import java.util.List;

public interface TEnumService {
    int deleteByPrimaryKey(Integer id);

    int insert(TEnum record);

    int insertSelective(TEnum record);

    TEnum selectByPrimaryKey(Integer id);

    TEnum selectByCode(String code);

    int updateByPrimaryKeySelective(TEnum record);

    int updateByPrimaryKey(TEnum record);

    List<TEnum> listByPid(Integer id);

    List<EnumDto> listChildByPidAndRoleId(Integer pid, Integer roleId);

    boolean addEnumSubmit(SubmitEnumDto dto);

    boolean deleteEnum(Integer id, Byte type);

    List<EnumDto> listEnumDtoAllByPid(Integer pid, Integer roleId);
}