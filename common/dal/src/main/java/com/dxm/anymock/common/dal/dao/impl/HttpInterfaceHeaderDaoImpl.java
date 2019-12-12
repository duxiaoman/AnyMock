package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceHeaderDao;
import com.dxm.anymock.common.dal.model.HttpInterfaceHeaderBO;
import com.dxm.anymock.common.dal.model.enums.HttpHeaderType;
import com.dxm.anymock.common.dal.entity.HttpInterfaceHeaderDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceHeaderDOExample;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceHeaderDOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class HttpInterfaceHeaderDaoImpl implements HttpInterfaceHeaderDao {

    @Autowired
    private HttpInterfaceHeaderDOMapper httpInterfaceHeaderDOMapper;

    @Override
    public void batchCreate(List<HttpInterfaceHeaderBO> httpInterfaceHeaderBOListList, Long httpInterfaceId, HttpHeaderType type) {
        httpInterfaceHeaderBOListList.forEach(httpInterfaceHeaderBO -> {
            HttpInterfaceHeaderDO httpInterfaceHeaderDO = convertToDO(httpInterfaceHeaderBO, httpInterfaceId, type);

            int resultSize;
            try {
                resultSize = httpInterfaceHeaderDOMapper.insert(httpInterfaceHeaderDO);
            } catch (DuplicateKeyException e) {
                throw new BizException(ResultCode.DUPLICATE_KEY_HTTP_INTERFACE_HEADER);
            }

            if (resultSize != 1) {
                throw new IncorrectResultSizeException(resultSize);
            }
        });
    }

    @Override
    public void batchDelete(Long httpInterfaceId, HttpHeaderType type) {
        HttpInterfaceHeaderDOExample example = new HttpInterfaceHeaderDOExample();
        example.createCriteria()
                .andHttpInterfaceIdEqualTo(httpInterfaceId)
                .andTypeEqualTo(type.name());
        httpInterfaceHeaderDOMapper.deleteByExample(example);
    }

    @Override
    public List<HttpInterfaceHeaderBO> batchQuery(Long httpInterfaceId, HttpHeaderType type) {
        HttpInterfaceHeaderDOExample example = new HttpInterfaceHeaderDOExample();
        example.createCriteria()
                .andHttpInterfaceIdEqualTo(httpInterfaceId)
                .andTypeEqualTo(type.name());
        return convertToBOList(httpInterfaceHeaderDOMapper.selectByExample(example));
    }

    private HttpInterfaceHeaderDO convertToDO(HttpInterfaceHeaderBO httpInterfaceHeaderBO,
                                              Long httpInterfaceId,
                                              HttpHeaderType httpHeaderType) {
        HttpInterfaceHeaderDO httpInterfaceHeaderDO = new HttpInterfaceHeaderDO();
        BeanUtils.copyProperties(httpInterfaceHeaderBO, httpInterfaceHeaderDO);
        httpInterfaceHeaderDO.setHttpInterfaceId(httpInterfaceId);
        httpInterfaceHeaderDO.setType(httpHeaderType.name());
        return httpInterfaceHeaderDO;
    }

    private HttpInterfaceHeaderBO convertToBO(HttpInterfaceHeaderDO httpInterfaceHeaderDO) {
        HttpInterfaceHeaderBO httpInterfaceHeaderBO = new HttpInterfaceHeaderBO();
        BeanUtils.copyProperties(httpInterfaceHeaderDO, httpInterfaceHeaderBO);
        return httpInterfaceHeaderBO;
    }

    private List<HttpInterfaceHeaderBO> convertToBOList(List<HttpInterfaceHeaderDO> httpInterfaceHeaderDOList) {
        List<HttpInterfaceHeaderBO> httpInterfaceHeaderBOList = new LinkedList<>();
        httpInterfaceHeaderDOList.forEach(httpInterfaceHeaderDO
                -> httpInterfaceHeaderBOList.add(convertToBO(httpInterfaceHeaderDO)));
        return httpInterfaceHeaderBOList;
    }
}
