package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;

import java.util.List;

public interface HttpInterfaceSnapshotDao {
    void saveSnapshot(HttpInterface httpInterface, HttpInterfaceOpType httpInterfaceOpType);
    List<HttpInterfaceSnapshot> selectSnapshotByHttpInterfaceId(Long httpInterfaceId);
    HttpInterfaceSnapshot selectSnapshotById(Long id);
}
