package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfacePO;
import com.dxm.anymock.common.dal.entity.HttpInterfacePOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfacePOWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HttpInterfacePOMapper {
    long countByExample(HttpInterfacePOExample example);

    int deleteByExample(HttpInterfacePOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfacePOWithBLOBs record);

    int insertSelective(HttpInterfacePOWithBLOBs record);

    List<HttpInterfacePOWithBLOBs> selectByExampleWithBLOBs(HttpInterfacePOExample example);

    List<HttpInterfacePO> selectByExample(HttpInterfacePOExample example);

    HttpInterfacePOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfacePOWithBLOBs record, @Param("example") HttpInterfacePOExample example);

    int updateByExampleWithBLOBs(@Param("record") HttpInterfacePOWithBLOBs record, @Param("example") HttpInterfacePOExample example);

    int updateByExample(@Param("record") HttpInterfacePO record, @Param("example") HttpInterfacePOExample example);

    int updateByPrimaryKeySelective(HttpInterfacePOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HttpInterfacePOWithBLOBs record);

    int updateByPrimaryKey(HttpInterfacePO record);
}