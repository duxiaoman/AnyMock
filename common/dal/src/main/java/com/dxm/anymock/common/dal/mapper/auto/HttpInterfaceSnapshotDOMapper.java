package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HttpInterfaceSnapshotDOMapper {
    long countByExample(HttpInterfaceSnapshotDOExample example);

    int deleteByExample(HttpInterfaceSnapshotDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceSnapshotDO record);

    int insertSelective(HttpInterfaceSnapshotDO record);

    List<HttpInterfaceSnapshotDO> selectByExampleWithRowbounds(HttpInterfaceSnapshotDOExample example, RowBounds rowBounds);

    List<HttpInterfaceSnapshotDO> selectByExample(HttpInterfaceSnapshotDOExample example);

    HttpInterfaceSnapshotDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceSnapshotDO record, @Param("example") HttpInterfaceSnapshotDOExample example);

    int updateByExample(@Param("record") HttpInterfaceSnapshotDO record, @Param("example") HttpInterfaceSnapshotDOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceSnapshotDO record);

    int updateByPrimaryKey(HttpInterfaceSnapshotDO record);
}