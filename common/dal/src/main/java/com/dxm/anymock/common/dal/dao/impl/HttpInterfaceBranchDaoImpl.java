package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceBranchDao;
import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchDOExample;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceBranchDOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class HttpInterfaceBranchDaoImpl implements HttpInterfaceBranchDao {

    @Autowired
    private HttpInterfaceBranchDOMapper httpInterfaceBranchDOMapper;

    @Override
    public void batchCreate(List<HttpInterfaceBranchBO> httpInterfaceBranchBOList, Long httpInterfaceId) {
        httpInterfaceBranchBOList.forEach(httpInterfaceBranchBO -> {
            HttpInterfaceBranchDO httpInterfaceBranchDO = convertToDO(httpInterfaceBranchBO, httpInterfaceId);

            int resultSize;
            try {
                resultSize = httpInterfaceBranchDOMapper.insert(httpInterfaceBranchDO);
            } catch (DuplicateKeyException e) {
                throw new BizException(ResultCode.DUPLICATE_KEY_HTTP_INTERFACE_BRANCH);
            }

            if (resultSize != 1) {
                throw new IncorrectResultSizeException(resultSize);
            }
        });
    }

    @Override
    public void batchDelete(Long httpInterfaceId) {
        HttpInterfaceBranchDOExample example = new HttpInterfaceBranchDOExample();
        example.createCriteria()
                .andHttpInterfaceIdEqualTo(httpInterfaceId);
        httpInterfaceBranchDOMapper.deleteByExample(example);
    }

    @Override
    public List<HttpInterfaceBranchBO> batchQuery(Long httpInterfaceId) {
        HttpInterfaceBranchDOExample example = new HttpInterfaceBranchDOExample();
        example.createCriteria()
                .andHttpInterfaceIdEqualTo(httpInterfaceId);
        return convertToBOList(httpInterfaceBranchDOMapper.selectByExample(example));
    }

    private HttpInterfaceBranchDO convertToDO(HttpInterfaceBranchBO httpInterfaceBranchBO,
                                              Long httpInterfaceId) {
        HttpInterfaceBranchDO httpInterfaceBranchDO = new HttpInterfaceBranchDO();
        BeanUtils.copyProperties(httpInterfaceBranchBO, httpInterfaceBranchDO);
        httpInterfaceBranchDO.setHttpInterfaceId(httpInterfaceId);
        return httpInterfaceBranchDO;
    }

    private HttpInterfaceBranchBO convertToBO(HttpInterfaceBranchDO httpInterfaceBranchDO) {
        HttpInterfaceBranchBO httpInterfaceBranchBO = new HttpInterfaceBranchBO();
        BeanUtils.copyProperties(httpInterfaceBranchDO, httpInterfaceBranchBO);
        return httpInterfaceBranchBO;
    }

    private List<HttpInterfaceBranchBO> convertToBOList(List<HttpInterfaceBranchDO> httpInterfaceBranchDOList) {
        List<HttpInterfaceBranchBO> httpInterfaceBranchBOList = new LinkedList<>();
        httpInterfaceBranchDOList.forEach(httpInterfaceBranchDO
                -> httpInterfaceBranchBOList.add(convertToBO(httpInterfaceBranchDO)));
        return httpInterfaceBranchBOList;
    }
}
