package com.dxm.anymock.common.dal.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpHeader;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;
import com.dxm.anymock.common.base.utils.ConvertUtils;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOWithBLOBs;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceSnapshotPOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class HttpInterfaceSnapshotDaoImpl implements HttpInterfaceSnapshotDao {

    @Autowired
    private HttpInterfaceSnapshotPOMapper httpInterfaceSnapshotPOMapper;

    @Override
    public void saveSnapshot(HttpInterface httpInterface, HttpInterfaceOpType httpInterfaceOpType) {
        int resultSize;

        HttpInterfaceSnapshotPOWithBLOBs snapshot = new HttpInterfaceSnapshotPOWithBLOBs();
        snapshot.setHttpInterfaceId(httpInterface.getId());
        snapshot.setHttpInterfaceRequestUri(httpInterface.getRequestUri());
        snapshot.setHttpInterfaceRequestMethod(httpInterface.getRequestMethod());
        snapshot.setHttpInterfaceDescription(httpInterface.getDescription());
        snapshot.setHttpInterfaceNeedAsyncCallback(httpInterface.getNeedAsyncCallback());
        snapshot.setHttpInterfaceConfigMode(httpInterface.getConfigMode());
        snapshot.setHttpInterfaceResponseBody(httpInterface.getResponseBody());
        snapshot.setHttpInterfaceCallbackRequestUrl(httpInterface.getCallbackRequestUrl());
        snapshot.setHttpInterfaceCallbackRequestMethod(httpInterface.getCallbackRequestMethod());
        snapshot.setHttpInterfaceCallbackRequestBody(httpInterface.getCallbackRequestBody());
        snapshot.setHttpInterfaceBranchJumpScript(httpInterface.getBranchJumpScript());
        snapshot.setHttpInterfaceSyncScript(httpInterface.getSyncScript());
        snapshot.setHttpInterfaceAsyncScript(httpInterface.getAsyncScript());
        snapshot.setHttpInterfaceSyncDelay(httpInterface.getSyncDelay());
        snapshot.setHttpInterfaceAsyncDelay(httpInterface.getAsyncDelay());
        snapshot.setHttpInterfaceStart(httpInterface.getStart());
        snapshot.setHttpInterfaceSubSpaceId(httpInterface.getSubSpaceId());
        snapshot.setHttpInterfaceResponseHeaderList(JSONObject.toJSONString(httpInterface.getResponseHeaderList()));
        snapshot.setHttpInterfaceCallbackRequestHeaderList(JSONObject.toJSONString(httpInterface.getCallbackRequestHeaderList()));
        snapshot.setHttpInterfaceBranchScriptList(JSONObject.toJSONString(httpInterface.getBranchScriptList()));
        snapshot.setHttpInterfaceLastUpdateUser(httpInterface.getLastUpdateUser());
        snapshot.setHttpInterfaceLastUpdateTime(httpInterface.getLastUpdateTime());
        snapshot.setOpType(httpInterfaceOpType.getCode());
        snapshot.setOpUser(GlobalConstant.DEFAULT_USER);
        snapshot.setSnapshotTime(new Date());

        resultSize = httpInterfaceSnapshotPOMapper.insert(snapshot);
        if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public List<HttpInterfaceSnapshot> selectSnapshotByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceSnapshotPOExample example = new HttpInterfaceSnapshotPOExample();
        example.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);

        List<HttpInterfaceSnapshotPO> snapshotPOList = httpInterfaceSnapshotPOMapper.selectByExample(example);
        List<HttpInterfaceSnapshot> snapshotList = new LinkedList<>();
        snapshotPOList.forEach(snapshotPO -> {
            HttpInterfaceSnapshot httpInterfaceSnapshot = new HttpInterfaceSnapshot();
            BeanUtils.copyProperties(snapshotPO, httpInterfaceSnapshot, "httpInterfaceResponseHeaderList",
                    "httpInterfaceCallbackRequestHeaderList",
                    "httpInterfaceBranchScriptList");
            snapshotList.add(httpInterfaceSnapshot);
        });
        return snapshotList;
    }

    @Override
    public HttpInterfaceSnapshot selectSnapshotById(Long id) {
        HttpInterfaceSnapshotPOWithBLOBs snapshotPOWithBLOBs = httpInterfaceSnapshotPOMapper.selectByPrimaryKey(id);
        HttpInterfaceSnapshot httpInterfaceSnapshot = new HttpInterfaceSnapshot();
        BeanUtils.copyProperties(snapshotPOWithBLOBs, httpInterfaceSnapshot,
                "httpInterfaceResponseHeaderList",
                "httpInterfaceCallbackRequestHeaderList",
                "httpInterfaceBranchScriptList");

        httpInterfaceSnapshot.setHttpInterfaceResponseHeaderList(
                JSON.parseArray(snapshotPOWithBLOBs.getHttpInterfaceResponseHeaderList(), HttpHeader.class)
        );
        httpInterfaceSnapshot.setHttpInterfaceCallbackRequestHeaderList(
                JSON.parseArray(snapshotPOWithBLOBs.getHttpInterfaceCallbackRequestHeaderList(), HttpHeader.class)
        );
        httpInterfaceSnapshot.setHttpInterfaceBranchScriptList(
                JSON.parseArray(snapshotPOWithBLOBs.getHttpInterfaceBranchScriptList(), BranchScript.class)
        );
        return httpInterfaceSnapshot;
    }
}
