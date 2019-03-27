package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.OpType;

public interface HttpInterfaceSnapshotDao {
    void create(HttpInterfaceBO httpInterfaceBO, OpType opType);
}
