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
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchDOExample;
import com.dxm.anymock.common.dal.entity.InterfaceBranchDO;
import com.dxm.anymock.common.dal.entity.InterfaceBranchDOExample;
import com.dxm.anymock.common.dal.mapper.auto.InterfaceBranchDOMapper;
import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;
import com.dxm.anymock.common.dal.model.InterfaceBranchBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jff on 2019/8/26.
 */
@Repository
@Transactional
public class InterfaceBranchDaoImpl implements InterfaceBranchDao {

    @Autowired
    private InterfaceBranchDOMapper interfaceBranchDOMapper;

    @Override
    public void batchCreate(List<InterfaceBranchBO> interfaceBranchBOList, Long interfaceId) {
        interfaceBranchBOList.forEach(interfaceBranchBO -> {
            InterfaceBranchDO interfaceBranchDO = convertToDO(interfaceBranchBO, interfaceId);

            int resultSize;
            try {
                resultSize = interfaceBranchDOMapper.insert(interfaceBranchDO);
            } catch (DuplicateKeyException e) {
                throw new BizException(ResultCode.DUPLICATE_KEY_HTTP_INTERFACE_BRANCH);
            }

            if (resultSize != 1) {
                throw new IncorrectResultSizeException(resultSize);
            }
        });
    }


    @Override
    public List<InterfaceBranchBO> batchQuery(Long interfaceId) {
        InterfaceBranchDOExample example = new InterfaceBranchDOExample();
        example.createCriteria().andInterfaceIdEqualTo(interfaceId);
        return convertToBOList(interfaceBranchDOMapper.selectByExample(example));
    }


    @Override
    public InterfaceBranchBO findBranch(String branchName, List<InterfaceBranchBO> branchBOList) {

        for (InterfaceBranchBO branchBO : branchBOList) {
            if (StringUtils.equals(branchBO.getName(), branchName)) {
                return branchBO;
            }
        }
        return null;

    }

    @Override
    public void batchDelete(Long httpInterfaceId) {
        InterfaceBranchDOExample example = new InterfaceBranchDOExample();
        example.createCriteria()
                .andInterfaceIdEqualTo(httpInterfaceId);
        interfaceBranchDOMapper.deleteByExample(example);
    }

    public InterfaceBranchDO convertToDO(InterfaceBranchBO interfaceBranchBO, Long interfaceId) {
        InterfaceBranchDO interfaceBranchDO = new InterfaceBranchDO();
        BeanUtils.copyProperties(interfaceBranchBO, interfaceBranchDO);
        interfaceBranchDO.setInterfaceId(interfaceId);
        return interfaceBranchDO;
    }


    private InterfaceBranchBO convertToBO(InterfaceBranchDO interfaceBranchDO) {
        InterfaceBranchBO interfaceBranchBO = new InterfaceBranchBO();
        BeanUtils.copyProperties(interfaceBranchDO, interfaceBranchBO);
        return interfaceBranchBO;
    }

    public List<InterfaceBranchBO> convertToBOList(List<InterfaceBranchDO> interfaceBranchDOs) {
        List<InterfaceBranchBO> interfaceBranchBOs = new LinkedList<>();
        interfaceBranchDOs.forEach(interfaceBranchDO -> interfaceBranchBOs.add(convertToBO(interfaceBranchDO)));
        return interfaceBranchBOs;
    }
}
