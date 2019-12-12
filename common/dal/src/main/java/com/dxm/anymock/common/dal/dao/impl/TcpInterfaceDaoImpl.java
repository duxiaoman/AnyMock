/*
*  Copyright 2018-2019 Duxiaoman Group Holding Ltd.

*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at

*  http://www.apache.org/licenses/LICENSE-2.0

*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.

*/


package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.InterfaceBranchDao;
import com.dxm.anymock.common.dal.dao.TcpInterfaceDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceDOExample;
import com.dxm.anymock.common.dal.entity.TcpInterfaceDO;
import com.dxm.anymock.common.dal.entity.TcpInterfaceDOExample;
import com.dxm.anymock.common.dal.mapper.auto.TcpInterfaceDOMapper;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceKeyBO;
import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;
import com.dxm.anymock.common.dal.model.enums.HttpHeaderType;
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

/**
 * Created by jff on 2019/8/26.
 */

@Repository
@Transactional
public class TcpInterfaceDaoImpl implements TcpInterfaceDao {

    @Autowired
    private TcpInterfaceDOMapper tcpInterfaceDOMapper;

    @Autowired
    private InterfaceBranchDao interfaceBranchDao;


    @Override
    public Long create(TcpInterfaceBO tcpInterfaceBO) {
        TcpInterfaceDO tcpInterfaceDO = convertToDO(tcpInterfaceBO);
        Date date = new Date();
        tcpInterfaceDO.setGmtCreate(date);
        tcpInterfaceDO.setGmtModified(date);

        int resultSize;
        try {
            resultSize = tcpInterfaceDOMapper.insert(tcpInterfaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_TCP_INTERFACE);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }

        // get tcp interface id
        Long tcpInterfaceId = tcpInterfaceDO.getId() ;
        interfaceBranchDao.batchCreate(tcpInterfaceBO.getBranchScriptList(), tcpInterfaceId);

        return tcpInterfaceId;
    }

    @Override
    public TcpInterfaceBO queryById(Long interfaceId) {
        TcpInterfaceDO tcpInterfaceDO = tcpInterfaceDOMapper.selectByPrimaryKey(interfaceId);
        if (tcpInterfaceDO == null) {
            return null;
        }
        return convertToBO(tcpInterfaceDO);
    }

    @Override
    public List<TcpInterfaceBO> queryBySpaceIdWithRowBoundsOrderByClause(Long spaceId, RowBounds rowBounds,
                                                                         String orderByClause) {
        TcpInterfaceDOExample example = new TcpInterfaceDOExample();
        example.createCriteria().andSpaceIdEqualTo(spaceId);
        example.setOrderByClause(orderByClause);
        return convertToBOList(tcpInterfaceDOMapper.selectByExampleWithRowbounds(example, rowBounds));
    }

    @Override
    public List<TcpInterfaceBO> queryAllWithRowBoundsOrderByClause(RowBounds rowBounds, String orderByClause) {
        TcpInterfaceDOExample example = new TcpInterfaceDOExample();
        example.setOrderByClause(orderByClause);
        return convertToBOList(tcpInterfaceDOMapper.selectByExampleWithRowbounds(example, rowBounds));
    }


    @Override
    public Long countAll() {
        return tcpInterfaceDOMapper.countByExample(new TcpInterfaceDOExample());
    }


    @Override
    public Long countBySpaceId(Long spaceId) {
        TcpInterfaceDOExample example = new TcpInterfaceDOExample();
        example.createCriteria().andSpaceIdEqualTo(spaceId);
        return tcpInterfaceDOMapper.countByExample(example);
    }

    @Override
    public TcpInterfaceDO queryByKey(TcpInterfaceDO tcpInterfaceDO) {
        TcpInterfaceDOExample tcpInterfaceDOExample = new TcpInterfaceDOExample();
        tcpInterfaceDOExample.createCriteria().andRequestUriEqualTo(tcpInterfaceDO.getRequestUri());
        List<TcpInterfaceDO> tcpInterfaceDOList = tcpInterfaceDOMapper.selectByExample(tcpInterfaceDOExample);

        int resultSize = tcpInterfaceDOList.size();
        if (resultSize == 0) {
            return null;
        } else if (resultSize == 1) {
            return tcpInterfaceDOList.get(0);
        } else {
            throw new IncorrectResultSizeException(resultSize);
        }
    }


    @Override
    public TcpInterfaceBO queryByKey(TcpInterfaceKeyBO tcpInterfaceKeyBO) {
        TcpInterfaceDOExample tcpInterfaceDOExample = new TcpInterfaceDOExample();
        tcpInterfaceDOExample.createCriteria().andRequestUriEqualTo(tcpInterfaceKeyBO.getRequestUri());
        List<TcpInterfaceDO> tcpInterfaceDOList = tcpInterfaceDOMapper.selectByExample(tcpInterfaceDOExample);

        int resultSize = tcpInterfaceDOList.size();
        if (resultSize == 0) {
            return null;
        } else if (resultSize == 1) {
            return convertToBO(tcpInterfaceDOList.get(0));
        } else {
            throw new IncorrectResultSizeException(resultSize);
        }
    }


    @Override
    public TcpInterfaceDO convertToDO(TcpInterfaceBO tcpInterfaceBO) {
        TcpInterfaceDO tcpInterfaceDO = new TcpInterfaceDO();
        BeanUtils.copyProperties(tcpInterfaceBO, tcpInterfaceDO);
        tcpInterfaceDO.setConfigMode(tcpInterfaceBO.getConfigMode().name());
        tcpInterfaceDO.setAccessAuthority(tcpInterfaceBO.getAccessAuthority().name());
        return tcpInterfaceDO;
    }


    @Override
    public void updateStatus(TcpInterfaceBO tcpInterfaceBO, Boolean isStart) {
        TcpInterfaceDO tcpInterfaceDO = convertToDO(tcpInterfaceBO);
        tcpInterfaceDO.setGmtModified(new Date());
        tcpInterfaceDO.setStart(isStart);

        int resultSize;
        try {
            resultSize = tcpInterfaceDOMapper.updateByPrimaryKeySelective(tcpInterfaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_TCP_INTERFACE);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }

    }


    @Override
    public void update(TcpInterfaceBO tcpInterfaceBO) {
        TcpInterfaceDO tcpInterfaceDO = convertToDO(tcpInterfaceBO);
        tcpInterfaceDO.setGmtModified(new Date());

        int resultSize;
        try {
            resultSize = tcpInterfaceDOMapper.updateByPrimaryKeySelective(tcpInterfaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_TCP_INTERFACE);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }

        Long tcpInterfaceId = tcpInterfaceBO.getId();

        interfaceBranchDao.batchDelete(tcpInterfaceId);
        interfaceBranchDao.batchCreate(
                tcpInterfaceBO.getBranchScriptList(), tcpInterfaceId);

    }

    @Override
    public void delete(Long id) {
        interfaceBranchDao.batchDelete(id);

        int resultSize = tcpInterfaceDOMapper.deleteByPrimaryKey(id);
        if (resultSize == 0) {
            throw new BizException(ResultCode.NOT_FOUND_TCP_INTERFACE);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }

    public TcpInterfaceBO convertToBO(TcpInterfaceDO tcpInterfaceDO) {
        TcpInterfaceBO tcpInterfaceBO = new TcpInterfaceBO();
        BeanUtils.copyProperties(tcpInterfaceDO, tcpInterfaceBO);
        tcpInterfaceBO.setConfigMode(EnumUtils.getEnum(ConfigMode.class, tcpInterfaceDO.getConfigMode()));
        tcpInterfaceBO.setAccessAuthority(EnumUtils.getEnum(AccessAuthority.class, tcpInterfaceDO.getAccessAuthority()));
        tcpInterfaceBO.setBranchScriptList(interfaceBranchDao.batchQuery(tcpInterfaceDO.getId()));

        return tcpInterfaceBO;
    }

    public List<TcpInterfaceBO> convertToBOList(List<TcpInterfaceDO> tcpInterfaceDOList) {
        List<TcpInterfaceBO> tcpInterfaceBOs = new LinkedList<>();
        tcpInterfaceDOList.forEach(tcpInterfaceDO -> tcpInterfaceBOs.add(convertToBO(tcpInterfaceDO)));
        return tcpInterfaceBOs;
    }

    @Override
    public List<TcpInterfaceBO> getAllTcpInterface() {
        TcpInterfaceDOExample tcpInterfaceDOExample = new TcpInterfaceDOExample();
        return convertToBOList(tcpInterfaceDOMapper.selectByExample(tcpInterfaceDOExample));
    }

}
