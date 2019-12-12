package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.OpType;

/**
 * Created by jff on 2019/9/3.
 */
public interface TcpInterfaceSnapshotDao {
    void create(TcpInterfaceBO httpInterfaceBO, OpType opType);

}
