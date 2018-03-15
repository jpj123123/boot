package jpj.boot.service;

import jpj.boot.entity.OutLib;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
public interface OutLibService {
    int deleteByPrimaryKey(Long id);

    /**
     * @param isOut        出入库 true 出库
     * @param goodsId      商品id
     * @param goodsName     商品名
     * @param goodsCount   商品数量
     * @param userId       用户Id 责任人id 入库归库管 出库归业务员
     * @param createUserId 操作人Id
     * @param remark       备注
     * @return
     */
    int insert(boolean isOut, Long goodsId, String goodsName, Long goodsCount, Long userId, Long createUserId, String remark);


    int insert(OutLib record);

    int insertSelective(OutLib record);

    OutLib selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutLib record);

    int updateByPrimaryKey(OutLib record);
}
