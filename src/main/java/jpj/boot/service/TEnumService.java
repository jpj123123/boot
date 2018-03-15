package jpj.boot.service;

import jpj.boot.dto.EnumDto;
import jpj.boot.dto.SubmitEnumDto;
import jpj.boot.entity.TEnum;

import java.util.List;

public interface TEnumService {
    int deleteByPrimaryKey(Long id);

    int insert(TEnum record);

    int insertSelective(TEnum record);

    TEnum selectByPrimaryKey(Long id);

    TEnum selectByCode(String code);

    int updateByPrimaryKeySelective(TEnum record);

    int updateByPrimaryKey(TEnum record);

    List<TEnum> listByPid(Long id);

    List<EnumDto> listChildByPidAndRoleId(Long pid, Long roleId);

    boolean addEnumSubmit(SubmitEnumDto dto);

    boolean deleteEnum(Long id, Byte type);

    List<EnumDto> listEnumDtoAllByPid(Long pid, Long roleId);
}