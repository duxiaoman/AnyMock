package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.TcpInterfaceSnapshotDO;
import com.dxm.anymock.common.dal.entity.TcpInterfaceSnapshotDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TcpInterfaceSnapshotDOMapper {
    long countByExample(TcpInterfaceSnapshotDOExample example);

    int deleteByExample(TcpInterfaceSnapshotDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TcpInterfaceSnapshotDO record);

    int insertSelective(TcpInterfaceSnapshotDO record);

    List<TcpInterfaceSnapshotDO> selectByExampleWithRowbounds(TcpInterfaceSnapshotDOExample example, RowBounds rowBounds);

    List<TcpInterfaceSnapshotDO> selectByExample(TcpInterfaceSnapshotDOExample example);

    TcpInterfaceSnapshotDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TcpInterfaceSnapshotDO record, @Param("example") TcpInterfaceSnapshotDOExample example);

    int updateByExample(@Param("record") TcpInterfaceSnapshotDO record, @Param("example") TcpInterfaceSnapshotDOExample example);

    int updateByPrimaryKeySelective(TcpInterfaceSnapshotDO record);

    int updateByPrimaryKey(TcpInterfaceSnapshotDO record);
}