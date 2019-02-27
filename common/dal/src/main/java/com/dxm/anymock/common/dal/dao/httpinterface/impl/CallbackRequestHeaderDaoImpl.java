package com.dxm.anymock.common.dal.dao.httpinterface.impl;

import com.dxm.anymock.common.base.entity.HttpHeader;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.base.util.ConvertUtil;
import com.dxm.anymock.common.dal.dao.httpinterface.CallbackRequestHeaderDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceCallbackRequestHeaderPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceCallbackRequestHeaderPOExample;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceCallbackRequestHeaderPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CallbackRequestHeaderDaoImpl implements CallbackRequestHeaderDao {

    @Autowired
    private HttpInterfaceCallbackRequestHeaderPOMapper httpInterfaceCallbackRequestHeaderPOMapper;

    @Override
    public void insert(Long httpInterfaceId, List<HttpHeader> httpHeaders) {
        httpHeaders.forEach(callbackRequestHeader -> {
            HttpInterfaceCallbackRequestHeaderPO callbackRequestHeaderPO
                    = ConvertUtil.convert(callbackRequestHeader, HttpInterfaceCallbackRequestHeaderPO.class);
            callbackRequestHeaderPO.setHttpInterfaceId(httpInterfaceId);

            int localResultSize;
            try {
                localResultSize = httpInterfaceCallbackRequestHeaderPOMapper.insert(callbackRequestHeaderPO);
            } catch (DuplicateKeyException e) {
                throw new BaseException(ErrorCode.HTTP_INTERFACE_CALLBACK_REQUEST_HEADER_DUPLICATE_KEY);
            }

            if (localResultSize != 1) {
                throw new IncorrectResultSizeDataAccessException(1, localResultSize);
            }
        });
    }

    @Override
    public void deleteByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceCallbackRequestHeaderPOExample example = new HttpInterfaceCallbackRequestHeaderPOExample();
        example.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        httpInterfaceCallbackRequestHeaderPOMapper.deleteByExample(example);
    }

    @Override
    public List<HttpHeader> selectByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceCallbackRequestHeaderPOExample callbackRequestHeaderExample
                = new HttpInterfaceCallbackRequestHeaderPOExample();
        callbackRequestHeaderExample.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        return ConvertUtil.convert(httpInterfaceCallbackRequestHeaderPOMapper.selectByExample(
                callbackRequestHeaderExample),  HttpHeader.class);
    }
}
