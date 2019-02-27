package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.dal.dao.RedisDao;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDaoImpl implements RedisDao {

    @Autowired
    private RedisTemplate<String, HttpInterface> httpInterfaceRedisTemplate;

    @Autowired
    private RedisTemplate<String, BranchScript> branchScriptRedisTemplate;

    private String buildRedisKey(String prefix, RequestType requestType) {
        return String.format("%s:%s:%s", prefix, requestType.getUri(), requestType.getMethod());
    }

    private String buildHttpInterfaceKey(RequestType requestType) {
        return buildRedisKey(GlobalConstant.REDIS_KEY_PREFIX_HTTP_INTERFACE, requestType);
    }

    private String buildBranchScriptKey(RequestType requestType) {
        return buildRedisKey(GlobalConstant.REDIS_KEY_PREFIX_HTTP_INTERFACE_BRANCH_SCRIPT, requestType);
    }

    @Override
    public void clearHttpInterfaceCache(RequestType requestType) {
        String httpInterfaceKey = buildHttpInterfaceKey(requestType);
        String branchScriptKey = buildBranchScriptKey(requestType);
        httpInterfaceRedisTemplate.delete(httpInterfaceKey);
        branchScriptRedisTemplate.delete(branchScriptKey);
    }

    @Override
    public HttpInterface getHttpInterface(RequestType requestType) {
        String httpInterfaceKey = buildHttpInterfaceKey(requestType);
        if (BooleanUtils.isTrue(httpInterfaceRedisTemplate.hasKey(httpInterfaceKey))) {
            return httpInterfaceRedisTemplate.opsForValue().get(httpInterfaceKey);
        } else {
            return null;
        }
    }

    @Override
    public void setHttpInterface(RequestType requestType, HttpInterface httpInterface) {
        String httpInterfaceKey = buildHttpInterfaceKey(requestType);
        httpInterfaceRedisTemplate.opsForValue().set(httpInterfaceKey, httpInterface);
    }

    @Override
    public BranchScript getBranchScript(RequestType requestType, String branchName) {
        String branchScriptKey = buildBranchScriptKey(requestType);
        if (BooleanUtils.isTrue(branchScriptRedisTemplate.hasKey(branchScriptKey))) {
            return (BranchScript)branchScriptRedisTemplate.opsForHash().get(branchScriptKey, branchName);
        } else {
            return null;
        }
    }

    @Override
    public void setBranchScript(RequestType requestType, String branchName, BranchScript branchScript) {
        String branchScriptKey = buildBranchScriptKey(requestType);
        branchScriptRedisTemplate.opsForHash().put(branchScriptKey, branchName, branchScript);
    }
}
