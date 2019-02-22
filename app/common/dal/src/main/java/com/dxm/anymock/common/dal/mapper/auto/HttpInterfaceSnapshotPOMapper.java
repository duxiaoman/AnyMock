package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HttpInterfaceSnapshotPOMapper {
    long countByExample(HttpInterfaceSnapshotPOExample example);

    int deleteByExample(HttpInterfaceSnapshotPOExample example);

    int deleteByPrimaryKey(Long snapshotId);

    int insert(HttpInterfaceSnapshotPOWithBLOBs record);

    int insertSelective(HttpInterfaceSnapshotPOWithBLOBs record);

    List<HttpInterfaceSnapshotPOWithBLOBs> selectByExampleWithBLOBs(HttpInterfaceSnapshotPOExample example);

    List<HttpInterfaceSnapshotPO> selectByExample(HttpInterfaceSnapshotPOExample example);

    HttpInterfaceSnapshotPOWithBLOBs selectByPrimaryKey(Long snapshotId);

    int updateByExampleSelective(@Param("record") HttpInterfaceSnapshotPOWithBLOBs record, @Param("example") HttpInterfaceSnapshotPOExample example);

    int updateByExampleWithBLOBs(@Param("record") HttpInterfaceSnapshotPOWithBLOBs record, @Param("example") HttpInterfaceSnapshotPOExample example);

    int updateByExample(@Param("record") HttpInterfaceSnapshotPO record, @Param("example") HttpInterfaceSnapshotPOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceSnapshotPOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HttpInterfaceSnapshotPOWithBLOBs record);

    int updateByPrimaryKey(HttpInterfaceSnapshotPO record);
}