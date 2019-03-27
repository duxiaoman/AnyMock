package com.dxm.anymock.common.dal.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.OpType;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotDO;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceSnapshotDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class HttpInterfaceSnapshotDaoImpl implements HttpInterfaceSnapshotDao {

    @Autowired
    private HttpInterfaceSnapshotDOMapper httpInterfaceSnapshotDOMapper;

    @Override
    public void create(HttpInterfaceBO httpInterfaceBO, OpType opType) {
        HttpInterfaceSnapshotDO httpInterfaceSnapshotDO = new HttpInterfaceSnapshotDO();
        httpInterfaceSnapshotDO.setHttpInterfaceId(httpInterfaceBO.getId());
        httpInterfaceSnapshotDO.setHttpInterfaceRequestMethod(httpInterfaceBO.getRequestMethod());
        httpInterfaceSnapshotDO.setHttpInterfaceRequestUri(httpInterfaceBO.getRequestUri());
        httpInterfaceSnapshotDO.setHttpInterfaceSpaceId(httpInterfaceBO.getSpaceId());
        httpInterfaceSnapshotDO.setHttpInterface(JSONObject.toJSONString(httpInterfaceBO));

        Date now = new Date();
        httpInterfaceSnapshotDO.setGmtCreate(now);
        httpInterfaceSnapshotDO.setGmtModified(now);
        httpInterfaceSnapshotDO.setOpType(opType.name());

        int resultSize = httpInterfaceSnapshotDOMapper.insert(httpInterfaceSnapshotDO);
        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }
}
