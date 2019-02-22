package com.dxm.anymock.web.biz.impl;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.HttpInterfaceSnapshotDao;
import com.dxm.anymock.common.dal.dao.RedisDao;
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
    private RedisDao redisDao;

    @Override
    public List<HttpInterface> selectBySubSpaceId(Long spaceId) {
        return httpInterfaceDao.selectBySubSpaceId(spaceId);
    }

    @Override
    public HttpInterface selectById(Long id) {
        return httpInterfaceDao.selectById(id);
    }

    @Override
    public void insert(HttpInterface httpInterface) {
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
    public List<HttpInterfaceSnapshot> selectSnapshotById(Long id) {
        // httpInterfaceSnapshotDao.
        return null;
    }

    private void beforeRecordChange(Long id, HttpInterfaceOpType httpInterfaceOpType) {
        HttpInterface httpInterface = selectById(id);
        redisDao.clearCache(new RequestType(httpInterface));
        httpInterfaceSnapshotDao.saveSnapshot(httpInterface, httpInterfaceOpType);
    }
}
