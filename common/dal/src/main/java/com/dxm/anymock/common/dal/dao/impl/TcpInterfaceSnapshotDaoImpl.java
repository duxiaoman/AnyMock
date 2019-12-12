/*
*  Copyright 2018-2019 Duxiaoman Group Holding Ltd.

*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at

*  http://www.apache.org/licenses/LICENSE-2.0

*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.

*/


package com.dxm.anymock.common.dal.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.TcpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.entity.TcpInterfaceSnapshotDO;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceBranchDOMapper;
import com.dxm.anymock.common.dal.mapper.auto.TcpInterfaceSnapshotDOMapper;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jff on 2019/9/3.
 */
@Repository
public class TcpInterfaceSnapshotDaoImpl implements TcpInterfaceSnapshotDao {

    @Autowired
    private TcpInterfaceSnapshotDOMapper tcpInterfaceSnapshotDOMapper;

    @Override
    public void create(TcpInterfaceBO tcpInterfaceBO, OpType opType) {
        TcpInterfaceSnapshotDO tcpInterfaceSnapshotDO = new TcpInterfaceSnapshotDO();
        tcpInterfaceSnapshotDO.setTcpInterfaceId(tcpInterfaceBO.getId());
        tcpInterfaceSnapshotDO.setTcpInterfaceRequestUri(tcpInterfaceBO.getRequestUri());
        tcpInterfaceSnapshotDO.setTcpInterfaceSpaceId(tcpInterfaceBO.getSpaceId());
        tcpInterfaceSnapshotDO.setTcpInterface(JSONObject.toJSONString(tcpInterfaceBO));

        Date now = new Date();
        tcpInterfaceSnapshotDO.setGmtCreate(now);
        tcpInterfaceSnapshotDO.setGmtModified(now);
        tcpInterfaceSnapshotDO.setOpType(opType.name());

        int resultSize = tcpInterfaceSnapshotDOMapper.insert(tcpInterfaceSnapshotDO);
        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }
}

