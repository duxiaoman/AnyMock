package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceBranchDao;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.HttpInterfaceHeaderDao;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;
import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;
import com.dxm.anymock.common.dal.model.enums.HttpHeaderType;
import com.dxm.anymock.common.dal.entity.HttpInterfaceDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceDOExample;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceDOMapper;
import org.apache.commons.lang3.EnumUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class HttpInterfaceDaoImpl implements HttpInterfaceDao {

    @Autowired
    private HttpInterfaceDOMapper httpInterfaceDOMapper;

    @Autowired
    private HttpInterfaceHeaderDao httpInterfaceHeaderDao;

    @Autowired
    private HttpInterfaceBranchDao httpInterfaceBranchDao;

    @Override
    public HttpInterfaceBO queryById(Long id) {
        HttpInterfaceDO httpInterfaceDO = httpInterfaceDOMapper.selectByPrimaryKey(id);
        if (httpInterfaceDO == null) {
            return null;
        }
        return convertToBO(httpInterfaceDO);
    }

    @Override
    public List<HttpInterfaceBO> queryAllWithRowBoundsOrderByClause(RowBounds rowBounds, String orderByClause) {
        HttpInterfaceDOExample example = new HttpInterfaceDOExample();
        example.setOrderByClause(orderByClause);
        return convertToBOList(httpInterfaceDOMapper.selectByExampleWithRowbounds(example, rowBounds));
    }

    @Override
    public List<HttpInterfaceBO> queryBySpaceIdWithRowBoundsOrderByClause(Long spaceId, RowBounds rowBounds, String orderByClause) {
        HttpInterfaceDOExample example = new HttpInterfaceDOExample();
        example.createCriteria().andSpaceIdEqualTo(spaceId);
        example.setOrderByClause(orderByClause);
        return convertToBOList(httpInterfaceDOMapper.selectByExampleWithRowbounds(example, rowBounds));
    }

    @Override
    public List<HttpInterfaceBO> queryBySpaceId(Long spaceId) {
        return queryBySpaceIdWithRowBoundsOrderByClause(spaceId, new RowBounds(), null);
    }

    @Override
    public HttpInterfaceBO queryByKey(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        HttpInterfaceDOExample example = new HttpInterfaceDOExample();
        example.createCriteria()
                .andRequestUriEqualTo(httpInterfaceKeyBO.getRequestUri())
                .andRequestMethodEqualTo(httpInterfaceKeyBO.getRequestMethod());
        List<HttpInterfaceDO> httpInterfaceDOList = httpInterfaceDOMapper.selectByExample(example);
        int resultSize = httpInterfaceDOList.size();
        if (resultSize == 0) {
            return null;
        } else if (resultSize == 1) {
            return convertToBO(httpInterfaceDOList.get(0));
        } else {
            throw new IncorrectResultSizeException(resultSize);
        }
    }

    @Override
    public Long create(HttpInterfaceBO httpInterfaceBO) {
        HttpInterfaceDO httpInterfaceDO = convertToDO(httpInterfaceBO);
        Date now = new Date();
        httpInterfaceDO.setId(null);
        httpInterfaceDO.setGmtCreate(now);
        httpInterfaceDO.setGmtModified(now);

        int resultSize;
        try {
            resultSize = httpInterfaceDOMapper.insert(httpInterfaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_HTTP_INTERFACE);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }

        // get httpInterface.id
        HttpInterfaceDOExample example = new HttpInterfaceDOExample();
        example.createCriteria()
                .andRequestMethodEqualTo(httpInterfaceDO.getRequestMethod())
                .andRequestUriEqualTo(httpInterfaceDO.getRequestUri());
        List<HttpInterfaceDO> httpInterfaceDOList
                = httpInterfaceDOMapper.selectByExample(example);
        if (httpInterfaceDOList.size() != 1) {
            throw new IncorrectResultSizeException(httpInterfaceDOList.size());
        }

        Long httpInterfaceId = httpInterfaceDOList.get(0).getId();
        httpInterfaceHeaderDao.batchCreate(
                httpInterfaceBO.getResponseHeaderList(), httpInterfaceId, HttpHeaderType.RESPONSE);
        httpInterfaceHeaderDao.batchCreate(
                httpInterfaceBO.getCallbackRequestHeaderList(), httpInterfaceId, HttpHeaderType.CALLBACK_REQUEST);
        httpInterfaceBranchDao.batchCreate(
                httpInterfaceBO.getBranchScriptList(), httpInterfaceId);
        return httpInterfaceId;
    }

    @Override
    public void update(HttpInterfaceBO httpInterfaceBO) {
        HttpInterfaceDO httpInterfaceDO = convertToDO(httpInterfaceBO);
        httpInterfaceDO.setGmtCreate(null);
        httpInterfaceDO.setGmtModified(new Date());

        int resultSize;
        try {
            resultSize = httpInterfaceDOMapper.updateByPrimaryKeySelective(httpInterfaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_HTTP_INTERFACE);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }

        // get httpInterface.id
        Long httpInterfaceId = httpInterfaceBO.getId();

        // todo 若完全相同则不更新
        httpInterfaceHeaderDao.batchDelete(httpInterfaceId, HttpHeaderType.RESPONSE);
        httpInterfaceHeaderDao.batchCreate(
                httpInterfaceBO.getResponseHeaderList(), httpInterfaceId, HttpHeaderType.RESPONSE);

        httpInterfaceHeaderDao.batchDelete(httpInterfaceId, HttpHeaderType.CALLBACK_REQUEST);
        httpInterfaceHeaderDao.batchCreate(
                httpInterfaceBO.getCallbackRequestHeaderList(), httpInterfaceId, HttpHeaderType.CALLBACK_REQUEST);

        httpInterfaceBranchDao.batchDelete(httpInterfaceId);
        httpInterfaceBranchDao.batchCreate(
                httpInterfaceBO.getBranchScriptList(), httpInterfaceId);
    }

    @Override
    public void delete(Long id) {
        httpInterfaceHeaderDao.batchDelete(id, HttpHeaderType.RESPONSE);
        httpInterfaceHeaderDao.batchDelete(id, HttpHeaderType.CALLBACK_REQUEST);
        httpInterfaceBranchDao.batchDelete(id);

        int resultSize = httpInterfaceDOMapper.deleteByPrimaryKey(id);
        if (resultSize == 0) {
            throw new BizException(ResultCode.NOT_FOUND_HTTP_INTERFACE);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }

    @Override
    public Long countAll() {
        return httpInterfaceDOMapper.countByExample(new HttpInterfaceDOExample());
    }

    @Override
    public Long countBySpaceId(Long spaceId) {
        HttpInterfaceDOExample example = new HttpInterfaceDOExample();
        example.createCriteria().andSpaceIdEqualTo(spaceId);
        return httpInterfaceDOMapper.countByExample(example);
    }

    private HttpInterfaceDO convertToDO(HttpInterfaceBO httpInterfaceBO) {
        HttpInterfaceDO httpInterfaceDO = new HttpInterfaceDO();
        BeanUtils.copyProperties(httpInterfaceBO, httpInterfaceDO);
        httpInterfaceDO.setConfigMode(httpInterfaceBO.getConfigMode().name());
        httpInterfaceDO.setAccessAuthority(httpInterfaceBO.getAccessAuthority().name());
        return httpInterfaceDO;
    }

    private HttpInterfaceBO convertToBO(HttpInterfaceDO httpInterfaceDO) {
        HttpInterfaceBO httpInterfaceBO = new HttpInterfaceBO();
        BeanUtils.copyProperties(httpInterfaceDO, httpInterfaceBO);
        httpInterfaceBO.setConfigMode(EnumUtils.getEnum(ConfigMode.class, httpInterfaceDO.getConfigMode()));
        httpInterfaceBO.setAccessAuthority(EnumUtils.getEnum(AccessAuthority.class, httpInterfaceDO.getAccessAuthority()));

        Long id = httpInterfaceDO.getId();
        httpInterfaceBO.setResponseHeaderList(httpInterfaceHeaderDao.batchQuery(id, HttpHeaderType.RESPONSE));
        httpInterfaceBO.setCallbackRequestHeaderList(httpInterfaceHeaderDao.batchQuery(id, HttpHeaderType.CALLBACK_REQUEST));
        httpInterfaceBO.setBranchScriptList(httpInterfaceBranchDao.batchQuery(id));
        return httpInterfaceBO;
    }

    private List<HttpInterfaceBO> convertToBOList(List<HttpInterfaceDO> httpInterfaceDOList) {
        List<HttpInterfaceBO> httpInterfaceBOList = new LinkedList<>();
        httpInterfaceDOList.forEach(httpInterfaceDO -> httpInterfaceBOList.add(convertToBO(httpInterfaceDO)));
        return httpInterfaceBOList;
    }
}
