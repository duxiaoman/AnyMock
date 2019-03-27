package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HttpInterfaceBranchDOMapper {
    long countByExample(HttpInterfaceBranchDOExample example);

    int deleteByExample(HttpInterfaceBranchDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceBranchDO record);

    int insertSelective(HttpInterfaceBranchDO record);

    List<HttpInterfaceBranchDO> selectByExampleWithRowbounds(HttpInterfaceBranchDOExample example, RowBounds rowBounds);

    List<HttpInterfaceBranchDO> selectByExample(HttpInterfaceBranchDOExample example);

    HttpInterfaceBranchDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceBranchDO record, @Param("example") HttpInterfaceBranchDOExample example);

    int updateByExample(@Param("record") HttpInterfaceBranchDO record, @Param("example") HttpInterfaceBranchDOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceBranchDO record);

    int updateByPrimaryKey(HttpInterfaceBranchDO record);
}