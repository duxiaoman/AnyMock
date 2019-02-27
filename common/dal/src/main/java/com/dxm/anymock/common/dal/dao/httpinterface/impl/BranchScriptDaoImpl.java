package com.dxm.anymock.common.dal.dao.httpinterface.impl;

import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.base.util.ConvertUtil;
import com.dxm.anymock.common.dal.dao.httpinterface.BranchScriptDao;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchScriptPOExample;
import com.dxm.anymock.common.dal.entity.HttpInterfaceBranchScriptPOWithBLOBs;
import com.dxm.anymock.common.dal.mapper.auto.HttpInterfaceBranchScriptPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BranchScriptDaoImpl implements BranchScriptDao {

    @Autowired
    private HttpInterfaceBranchScriptPOMapper httpInterfaceBranchScriptPOMapper;

    @Override
    public void insert(Long httpInterfaceId, List<BranchScript> httpHeaders) {
        httpHeaders.forEach(branchScript -> {
            HttpInterfaceBranchScriptPOWithBLOBs httpInterfaceBranchScriptPOWithBLOBs
                    = ConvertUtil.convert(branchScript, HttpInterfaceBranchScriptPOWithBLOBs.class);
            httpInterfaceBranchScriptPOWithBLOBs.setHttpInterfaceId(httpInterfaceId);

            int localResultSize;
            try {
                localResultSize = httpInterfaceBranchScriptPOMapper.insert(httpInterfaceBranchScriptPOWithBLOBs);
            } catch (DuplicateKeyException e) {
                throw new BaseException(ErrorCode.HTTP_INTERFACE_BRANCH_SCRIPT_DUPLICATE_KEY);
            }

            if (localResultSize != 1) {
                throw new IncorrectResultSizeDataAccessException(1, localResultSize);
            }
        });
    }

    @Override
    public void deleteByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceBranchScriptPOExample example = new HttpInterfaceBranchScriptPOExample();
        example.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        httpInterfaceBranchScriptPOMapper.deleteByExample(example);
    }

    @Override
    public List<BranchScript> selectByHttpInterfaceId(Long httpInterfaceId) {
        HttpInterfaceBranchScriptPOExample branchScriptPOExample = new HttpInterfaceBranchScriptPOExample();
        branchScriptPOExample.createCriteria().andHttpInterfaceIdEqualTo(httpInterfaceId);
        return ConvertUtil.convert(httpInterfaceBranchScriptPOMapper.selectByExampleWithBLOBs(
                branchScriptPOExample), BranchScript.class);
    }

    @Override
    public BranchScript selectByHttpInterfaceIdAndName(Long httpInterfaceId, String name) {
        HttpInterfaceBranchScriptPOExample branchScriptPOExample = new HttpInterfaceBranchScriptPOExample();
        branchScriptPOExample.createCriteria()
                .andHttpInterfaceIdEqualTo(httpInterfaceId)
                .andNameEqualTo(name);
        List<HttpInterfaceBranchScriptPOWithBLOBs> branchScriptPOWithBLOBsList
                = httpInterfaceBranchScriptPOMapper.selectByExampleWithBLOBs(branchScriptPOExample);
        if (branchScriptPOWithBLOBsList.size() != 1) {
            throw new BaseException(ErrorCode.HTTP_INTERFACE_BRANCH_SCRIPT_NOT_FOUND);
        }
        return ConvertUtil.convert(branchScriptPOWithBLOBsList.get(0), BranchScript.class);
    }
}
