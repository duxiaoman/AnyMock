package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.dao.RedisDao;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.web.biz.HttpInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HttpInterfaceServiceImpl implements HttpInterfaceService {

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Autowired
    private HttpInterfaceSnapshotDao httpInterfaceSnapshotDao;

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<HttpInterface> selectBySpaceId(Long spaceId) {
        return httpInterfaceDao.selectBySpaceId(spaceId);
    }

    @Override
    public HttpInterface selectById(Long id) {
        return httpInterfaceDao.selectById(id);
    }

    @Override
    public void insert(HttpInterface httpInterface) {
        if (!spaceDao.level(httpInterface.getSpaceId()).equals(GlobalConstant.MAX_SPACE_LEVEL)) {
            throw new BaseException(ErrorCode.CANT_INSERT_INTERFACE_UNDER_THIS_SPACE_LEVEL);
        }
        httpInterfaceDao.insert(httpInterface);
    }

    @Override
    public void update(HttpInterface httpInterface) {
        beforeRecordChange(httpInterface.getId(), HttpInterfaceOpType.UPDATE);
        httpInterfaceDao.update(httpInterface);
    }

    @Override
    public void delete(Long id) {
        beforeRecordChange(id, HttpInterfaceOpType.DELETE);
        httpInterfaceDao.delete(id);
    }

    @Override
    public void start(Long id) {
        beforeRecordChange(id, HttpInterfaceOpType.START);
        httpInterfaceDao.start(id);
    }

    @Override
    public void shutdown(Long id) {
        beforeRecordChange(id, HttpInterfaceOpType.SHUTDOWN);
        httpInterfaceDao.shutdown(id);
    }

    @Override
    public HttpInterface selectByRequestType(RequestType requestType) {
        HttpInterface httpInterface = redisDao.getHttpInterface(requestType);
        if (httpInterface == null) {
            httpInterface = httpInterfaceDao.selectByRequestType(requestType);
            redisDao.setHttpInterface(requestType, httpInterface);
        }
        return httpInterface;
    }

    @Override
    public List<HttpInterfaceSnapshot> selectSnapshotByHttpInterfaceId(Long httpInterfaceId) {
        return httpInterfaceSnapshotDao.selectSnapshotByHttpInterfaceId(httpInterfaceId);
    }

    @Override
    public HttpInterfaceSnapshot selectSnapshotById(Long id) {
        return httpInterfaceSnapshotDao.selectSnapshotById(id);
    }

    private void beforeRecordChange(Long id, HttpInterfaceOpType httpInterfaceOpType) {
        HttpInterface httpInterface = selectById(id);
        redisDao.clearCache(new RequestType(httpInterface));
        httpInterfaceSnapshotDao.saveSnapshot(httpInterface, httpInterfaceOpType);
    }
}
