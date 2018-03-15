package jpj.boot.dao;

import jpj.boot.entity.OutLib;

public interface OutLibMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutLib record);

    int insertSelective(OutLib record);

    OutLib selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutLib record);

    int updateByPrimaryKey(OutLib record);
}