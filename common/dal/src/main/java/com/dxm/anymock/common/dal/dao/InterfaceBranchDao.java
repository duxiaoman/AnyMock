package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.entity.InterfaceBranchDO;
import com.dxm.anymock.common.dal.entity.TcpInterfaceDO;
import com.dxm.anymock.common.dal.model.InterfaceBranchBO;

import java.util.List;

/**
 * Created by jff on 2019/8/26.
 */
public interface InterfaceBranchDao {

    /**
     * 创建接口响应分支
     *
     * @param interfaceBranchBOList
     * @param interfaceId
     */
    void batchCreate(List<InterfaceBranchBO> interfaceBranchBOList, Long interfaceId);

    /**
     * 查询接口的所有响应分支
     *
     * @param interfaceId
     * @return
     */
    List<InterfaceBranchBO> batchQuery(Long interfaceId);


    /**
     * 根据branchname 查询响应分支
     *
     * @param branchName
     * @param branchBOList
     * @return
     */
    InterfaceBranchBO findBranch(String branchName, List<InterfaceBranchBO> branchBOList);


    /**
     *
     *
     * @param httpInterfaceId
     */
    void batchDelete(Long httpInterfaceId);
}

