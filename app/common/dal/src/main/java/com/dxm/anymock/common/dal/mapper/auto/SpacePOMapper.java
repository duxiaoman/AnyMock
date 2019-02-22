package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.SpacePO;
import com.dxm.anymock.common.dal.entity.SpacePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpacePOMapper {
    long countByExample(SpacePOExample example);

    int deleteByExample(SpacePOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpacePO record);

    int insertSelective(SpacePO record);

    List<SpacePO> selectByExample(SpacePOExample example);

    SpacePO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpacePO record, @Param("example") SpacePOExample example);

    int updateByExample(@Param("record") SpacePO record, @Param("example") SpacePOExample example);

    int updateByPrimaryKeySelective(SpacePO record);

    int updateByPrimaryKey(SpacePO record);
}