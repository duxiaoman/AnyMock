package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.SubSpacePO;
import com.dxm.anymock.common.dal.entity.SubSpacePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubSpacePOMapper {
    long countByExample(SubSpacePOExample example);

    int deleteByExample(SubSpacePOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubSpacePO record);

    int insertSelective(SubSpacePO record);

    List<SubSpacePO> selectByExample(SubSpacePOExample example);

    SubSpacePO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubSpacePO record, @Param("example") SubSpacePOExample example);

    int updateByExample(@Param("record") SubSpacePO record, @Param("example") SubSpacePOExample example);

    int updateByPrimaryKeySelective(SubSpacePO record);

    int updateByPrimaryKey(SubSpacePO record);
}