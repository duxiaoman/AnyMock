package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfacePO;
import com.dxm.anymock.common.dal.entity.HttpInterfacePOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfacePOWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HttpInterfacePOMapper {
    long countByExample(HttpInterfacePOExample example);

    int deleteByExample(HttpInterfacePOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfacePOWithBLOBs record);

    int insertSelective(HttpInterfacePOWithBLOBs record);

    List<HttpInterfacePOWithBLOBs> selectByExampleWithBLOBsWithRowbounds(HttpInterfacePOExample example, RowBounds rowBounds);

    List<HttpInterfacePOWithBLOBs> selectByExampleWithBLOBs(HttpInterfacePOExample example);

    List<HttpInterfacePO> selectByExampleWithRowbounds(HttpInterfacePOExample example, RowBounds rowBounds);

    List<HttpInterfacePO> selectByExample(HttpInterfacePOExample example);

    HttpInterfacePOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfacePOWithBLOBs record, @Param("example") HttpInterfacePOExample example);

    int updateByExampleWithBLOBs(@Param("record") HttpInterfacePOWithBLOBs record, @Param("example") HttpInterfacePOExample example);

    int updateByExample(@Param("record") HttpInterfacePO record, @Param("example") HttpInterfacePOExample example);

    int updateByPrimaryKeySelective(HttpInterfacePOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HttpInterfacePOWithBLOBs record);

    int updateByPrimaryKey(HttpInterfacePO record);
}