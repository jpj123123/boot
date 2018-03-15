package jpj.boot.service.impl;

import jpj.boot.dao.TEnumMapper;
import jpj.boot.dto.EnumDto;
import jpj.boot.dto.SubmitEnumDto;
import jpj.boot.entity.TEnum;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.TEnumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
@Service
public class TEnumServiceImpl implements TEnumService {
    @Resource
    private TEnumMapper tEnumMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        tEnumMapper.deleteRoleEnumByEnumId(id);
        return tEnumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TEnum record) {
        return tEnumMapper.insert(record);
    }

    @Override
    public int insertSelective(TEnum record) {
        return tEnumMapper.insertSelective(record);
    }

    @Override
    public TEnum selectByPrimaryKey(Long id) {
        return tEnumMapper.selectByPrimaryKey(id);
    }

    @Override
    public TEnum selectByCode(String code) {
        return tEnumMapper.selectByCode(code);
    }

    @Override
    public int updateByPrimaryKeySelective(TEnum record) {
        return tEnumMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TEnum record) {
        return tEnumMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TEnum> listByPid(Long pid) {
        List<TEnum> lists = tEnumMapper.listByPid(pid);

        lists.stream().filter(enumDto -> enumDto.getType() != 3).forEach(enumDto -> {
            Integer count = tEnumMapper.countByPid(enumDto.getId());
            if (count > 0) {
                enumDto.setChildren(listByPid(enumDto.getId()));
            }
        });
        return lists;
    }

    @Override
    public List<EnumDto> listChildByPidAndRoleId(Long pid, Long roleId) {
        List<EnumDto> lists = tEnumMapper.listChildByPidAndRoleId(pid, roleId);

        lists.stream().forEach(enumDto -> {
            Map<String, Object> map = new HashMap<>();
            map.put("url", enumDto.getUrl());
            map.put("type", enumDto.getType());
            enumDto.setAttributes(map);
            enumDto.setState("close");
            if (enumDto.getType() == 1) {
                //Long count = tEnumMapper.countChildByPidAndRoleId(enumDto.getPid(), roleId);
                TEnum tEnum = this.selectByCode(enumDto.getId());
                enumDto.setChildren(this.listChildByPidAndRoleId(tEnum.getId(), roleId));
            }
        });
        return lists;
    }

    @Override
    public List<EnumDto> listEnumDtoAllByPid(Long pid, Long roleId) {
        List<EnumDto> lists = null;
        if (roleId == 0) {
            lists = tEnumMapper.listEnumDtoAllByPid(pid);
        } else {
            lists = tEnumMapper.listChildByPidAndRoleId(pid, roleId);
        }
        lists.stream().forEach(enumDto -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", enumDto.getPid());
            map.put("type", enumDto.getType());
            enumDto.setAttributes(map);
            if (enumDto.getType() == 1 || enumDto.getType() == 2) {
                TEnum tEnum = this.selectByCode(enumDto.getId());
                enumDto.setChildren(this.listEnumDtoAllByPid(tEnum.getId(), roleId));
            }
        });
        return lists;
    }

    @Override
    public boolean addEnumSubmit(SubmitEnumDto dto) {
        ModelAndView mav = new ModelAndView();
        TEnum checkTEnum = tEnumMapper.selectByCode(dto.getCode());
        if (checkTEnum != null)
            throw new BuisnessException("编号已存在");

        TEnum tEnum = new TEnum();
        tEnum.setName(dto.getName());
        tEnum.setCode(dto.getCode());
        tEnum.setPid(dto.getPid());
        tEnum.setIcons(dto.getIcons());
        tEnum.setUrl(dto.getUrl());
        tEnum.setType(dto.getType());
        tEnum.setDesc(dto.getDesc());

        return this.insert(tEnum) > 0;
    }

    @Transactional
    @Override
    public boolean deleteEnum(Long id, Byte type) {
        if (type != 3) {
            List<TEnum> tEnums = listByPid(id);
            for (TEnum tEnum : tEnums)
                deleteEnum(tEnum.getId(), tEnum.getType());
        }
        tEnumMapper.deleteByPrimaryKey(id);
        return true;
    }

}
