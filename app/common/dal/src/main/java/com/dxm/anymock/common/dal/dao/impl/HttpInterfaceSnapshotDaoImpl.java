package com.dxm.anymock.common.dal.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOWithBLOBs;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceSnapshotPOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class HttpInterfaceSnapshotDaoImpl implements HttpInterfaceSnapshotDao {

    @Autowired
    private HttpInterfaceSnapshotPOMapper httpInterfaceSnapshotPOMapper;

    @Override
    public void saveSnapshot(HttpInterface httpInterfaceSnapshot, HttpInterfaceOpType httpInterfaceOpType) {
        int resultSize;
        resultSize = httpInterfaceSnapshotPOMapper.insert(buildSnapshot(httpInterfaceSnapshot, httpInterfaceOpType));
        if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    private HttpInterfaceSnapshotPOWithBLOBs buildSnapshot(
            HttpInterface httpInterface,
            HttpInterfaceOpType httpInterfaceOpType
    ) {
        HttpInterfaceSnapshotPOWithBLOBs snapshot = new HttpInterfaceSnapshotPOWithBLOBs();
        BeanUtils.copyProperties(httpInterface, snapshot);
        snapshot.setResponseHeaderList(JSONObject.toJSONString(httpInterface.getResponseHeaderList()));
        snapshot.setCallbackRequestHeaderList(JSONObject.toJSONString(httpInterface.getCallbackRequestHeaderList()));
        snapshot.setBranchScriptList(JSONObject.toJSONString(httpInterface.getBranchScriptList()));
        snapshot.setOpType(httpInterfaceOpType.getCode());
        snapshot.setOpUser(GlobalConstant.DEFAULT_USER);
        snapshot.setSnapshotTime(new Date());
        return snapshot;
    }
}
