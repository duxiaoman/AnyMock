package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.SpaceDO;
import com.dxm.anymock.common.dal.entity.SpaceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpaceDOMapper {
    long countByExample(SpaceDOExample example);

    int deleteByExample(SpaceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpaceDO record);

    int insertSelective(SpaceDO record);

    List<SpaceDO> selectByExampleWithRowbounds(SpaceDOExample example, RowBounds rowBounds);

    List<SpaceDO> selectByExample(SpaceDOExample example);

    SpaceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpaceDO record, @Param("example") SpaceDOExample example);

    int updateByExample(@Param("record") SpaceDO record, @Param("example") SpaceDOExample example);

    int updateByPrimaryKeySelective(SpaceDO record);

    int updateByPrimaryKey(SpaceDO record);
}