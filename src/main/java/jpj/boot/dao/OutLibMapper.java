package jpj.boot.dao;

import jpj.boot.entity.OutLib;

public interface OutLibMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OutLib record);

    int insertSelective(OutLib record);

    OutLib selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutLib record);

    int updateByPrimaryKey(OutLib record);
}