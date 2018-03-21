package jpj.boot.dao;

import jpj.boot.dto.query.OutLibQuery;
import jpj.boot.entity.OutLib;

import java.util.List;

public interface OutLibMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutLib record);

    int insertSelective(OutLib record);

    OutLib selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutLib record);

    int updateByPrimaryKey(OutLib record);

    List<OutLib> listByQuery(OutLibQuery outLibQuery);
}