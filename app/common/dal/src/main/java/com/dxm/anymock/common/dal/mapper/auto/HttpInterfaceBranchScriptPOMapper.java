package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchScriptPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchScriptPOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchScriptPOWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HttpInterfaceBranchScriptPOMapper {
    long countByExample(HttpInterfaceBranchScriptPOExample example);

    int deleteByExample(HttpInterfaceBranchScriptPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceBranchScriptPOWithBLOBs record);

    int insertSelective(HttpInterfaceBranchScriptPOWithBLOBs record);

    List<HttpInterfaceBranchScriptPOWithBLOBs> selectByExampleWithBLOBs(HttpInterfaceBranchScriptPOExample example);

    List<HttpInterfaceBranchScriptPO> selectByExample(HttpInterfaceBranchScriptPOExample example);

    HttpInterfaceBranchScriptPOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceBranchScriptPOWithBLOBs record, @Param("example") HttpInterfaceBranchScriptPOExample example);

    int updateByExampleWithBLOBs(@Param("record") HttpInterfaceBranchScriptPOWithBLOBs record, @Param("example") HttpInterfaceBranchScriptPOExample example);

    int updateByExample(@Param("record") HttpInterfaceBranchScriptPO record, @Param("example") HttpInterfaceBranchScriptPOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceBranchScriptPOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HttpInterfaceBranchScriptPOWithBLOBs record);

    int updateByPrimaryKey(HttpInterfaceBranchScriptPO record);
}