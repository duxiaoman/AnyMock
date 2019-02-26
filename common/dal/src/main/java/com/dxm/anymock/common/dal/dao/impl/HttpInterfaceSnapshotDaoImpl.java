package com.dxm.anymock.common.dal.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpHeader;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfaceSnapshotPOWithBLOBs;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceSnapshotPOMapper;
import org.apache.ibatis.session.RowBounds;
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
        snapshot.setHttpInterfaceSpaceId(httpInterface.getSpaceId());
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
    public List<HttpInterfaceSnapshot> selectSnapshotByHttpInterfaceId(Long httpInterfaceId, RowBounds rowBounds) {
        HttpInterfaceSnapshotPOExample example = new HttpInterfaceSnapshotPOExample();
        example.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        example.setOrderByClause("snapshot_time desc");
        httpInterfaceSnapshotPOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<HttpInterfaceSnapshotPO> snapshotPOList = httpInterfaceSnapshotPOMapper.selectByExample(example);
        List<HttpInterfaceSnapshot> snapshotList = new LinkedList<>();
        snapshotPOList.forEach(snapshotPO -> snapshotList.add(convertPO2BO(snapshotPO)));
        return snapshotList;
    }

    @Override
    public Long countSnapshotByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceSnapshotPOExample example = new HttpInterfaceSnapshotPOExample();
        example.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        return httpInterfaceSnapshotPOMapper.countByExample(example);
    }

    @Override
    public HttpInterfaceSnapshot selectSnapshotById(Long id) {
        return convertPO2BOWithBLOBs(httpInterfaceSnapshotPOMapper.selectByPrimaryKey(id));
    }

    private HttpInterfaceSnapshot convertPO2BO(HttpInterfaceSnapshotPO source) {
        HttpInterfaceSnapshot target = new HttpInterfaceSnapshot();

        target.setId(source.getId());
        target.setOpType(source.getOpType());
        target.setOpUser(source.getOpUser());
        target.setSnapshotTime(source.getSnapshotTime());

        HttpInterface httpInterface = new HttpInterface();
        httpInterface.setId(source.getHttpInterfaceId());
        httpInterface.setRequestUri(source.getHttpInterfaceRequestUri());
        httpInterface.setRequestMethod(source.getHttpInterfaceRequestMethod());
        httpInterface.setDescription(source.getHttpInterfaceDescription());
        httpInterface.setNeedAsyncCallback(source.getHttpInterfaceNeedAsyncCallback());
        httpInterface.setConfigMode(source.getHttpInterfaceConfigMode());
        httpInterface.setCallbackRequestUrl(source.getHttpInterfaceCallbackRequestUrl());
        httpInterface.setCallbackRequestMethod(source.getHttpInterfaceCallbackRequestMethod());
        httpInterface.setSyncDelay(source.getHttpInterfaceSyncDelay());
        httpInterface.setAsyncDelay(source.getHttpInterfaceAsyncDelay());
        httpInterface.setStart(source.getHttpInterfaceStart());
        httpInterface.setSpaceId(source.getHttpInterfaceSpaceId());
        httpInterface.setLastUpdateUser(source.getHttpInterfaceLastUpdateUser());
        httpInterface.setLastUpdateTime(source.getHttpInterfaceLastUpdateTime());
        target.setHttpInterface(httpInterface);
        return target;
    }

    private HttpInterfaceSnapshot convertPO2BOWithBLOBs(HttpInterfaceSnapshotPOWithBLOBs source) {
        HttpInterfaceSnapshot target = convertPO2BO(source);
        HttpInterface httpInterface = target.getHttpInterface();

        httpInterface.setResponseBody(source.getHttpInterfaceResponseBody());
        httpInterface.setCallbackRequestBody(source.getHttpInterfaceCallbackRequestBody());
        httpInterface.setBranchJumpScript(source.getHttpInterfaceBranchJumpScript());
        httpInterface.setSyncScript(source.getHttpInterfaceSyncScript());
        httpInterface.setAsyncScript(source.getHttpInterfaceAsyncScript());

        httpInterface.setResponseHeaderList(
                JSON.parseArray(source.getHttpInterfaceResponseHeaderList(), HttpHeader.class));
        httpInterface.setCallbackRequestHeaderList(
                JSON.parseArray(source.getHttpInterfaceCallbackRequestHeaderList(), HttpHeader.class));
        httpInterface.setBranchScriptList(
                JSON.parseArray(source.getHttpInterfaceBranchScriptList(), BranchScript.class));
        return target;
    }
}
