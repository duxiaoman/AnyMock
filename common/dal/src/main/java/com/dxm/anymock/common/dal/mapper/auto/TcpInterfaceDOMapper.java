package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.TcpInterfaceDO;
import com.dxm.anymock.common.dal.entity.TcpInterfaceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TcpInterfaceDOMapper {
    long countByExample(TcpInterfaceDOExample example);

    int deleteByExample(TcpInterfaceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TcpInterfaceDO record);

    int insertSelective(TcpInterfaceDO record);

    List<TcpInterfaceDO> selectByExampleWithRowbounds(TcpInterfaceDOExample example, RowBounds rowBounds);

    List<TcpInterfaceDO> selectByExample(TcpInterfaceDOExample example);

    TcpInterfaceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TcpInterfaceDO record, @Param("example") TcpInterfaceDOExample example);

    int updateByExample(@Param("record") TcpInterfaceDO record, @Param("example") TcpInterfaceDOExample example);

    int updateByPrimaryKeySelective(TcpInterfaceDO record);

    int updateByPrimaryKey(TcpInterfaceDO record);
}