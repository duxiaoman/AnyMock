package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;

public interface HttpInterfaceSnapshotDao {
    void saveSnapshot(HttpInterface httpInterfaceSnapshot, HttpInterfaceOpType httpInterfaceOpType);
}
