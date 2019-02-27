package com.dxm.anymock.common.dal.dao.httpinterface.impl;

import com.dxm.anymock.common.base.entity.HttpHeader;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.base.util.ConvertUtil;
import com.dxm.anymock.common.dal.dao.httpinterface.ResponseHeaderDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceResponseHeaderPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceResponseHeaderPOExample;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceResponseHeaderPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResponseHeaderDaoImpl implements ResponseHeaderDao {

    @Autowired
    HttpInterfaceResponseHeaderPOMapper httpInterfaceResponseHeaderPOMapper;

    @Override
    public void insert(Long httpInterfaceId, List<HttpHeader> httpHeaders) {
        httpHeaders.forEach(responseHeader -> {
            HttpInterfaceResponseHeaderPO responseHeaderPO
                    = ConvertUtil.convert(responseHeader, HttpInterfaceResponseHeaderPO.class);
            responseHeaderPO.setHttpInterfaceId(httpInterfaceId);

            int localResultSize;
            try {
                localResultSize = httpInterfaceResponseHeaderPOMapper.insert(responseHeaderPO);
            } catch (DuplicateKeyException e) {
                throw new BaseException(ErrorCode.HTTP_INTERFACE_RESPONSE_HEADER_DUPLICATE_KEY);
            }

            if (localResultSize != 1) {
                throw new IncorrectResultSizeDataAccessException(1, localResultSize);
            }
        });
    }

    @Override
    public void deleteByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceResponseHeaderPOExample example = new HttpInterfaceResponseHeaderPOExample();
        example.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        httpInterfaceResponseHeaderPOMapper.deleteByExample(example);
    }

    @Override
    public List<HttpHeader> selectByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceResponseHeaderPOExample responseHeaderExample = new HttpInterfaceResponseHeaderPOExample();
        responseHeaderExample.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        return ConvertUtil.convert(
                httpInterfaceResponseHeaderPOMapper.selectByExample(responseHeaderExample), HttpHeader.class);
    }
}
