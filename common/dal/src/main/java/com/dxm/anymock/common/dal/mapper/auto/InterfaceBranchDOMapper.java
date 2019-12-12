package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.InterfaceBranchDO;
import com.dxm.anymock.common.dal.entity.InterfaceBranchDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface InterfaceBranchDOMapper {
    long countByExample(InterfaceBranchDOExample example);

    int deleteByExample(InterfaceBranchDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InterfaceBranchDO record);

    int insertSelective(InterfaceBranchDO record);

    List<InterfaceBranchDO> selectByExampleWithRowbounds(InterfaceBranchDOExample example, RowBounds rowBounds);

    List<InterfaceBranchDO> selectByExample(InterfaceBranchDOExample example);

    InterfaceBranchDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InterfaceBranchDO record, @Param("example") InterfaceBranchDOExample example);

    int updateByExample(@Param("record") InterfaceBranchDO record, @Param("example") InterfaceBranchDOExample example);

    int updateByPrimaryKeySelective(InterfaceBranchDO record);

    int updateByPrimaryKey(InterfaceBranchDO record);
}