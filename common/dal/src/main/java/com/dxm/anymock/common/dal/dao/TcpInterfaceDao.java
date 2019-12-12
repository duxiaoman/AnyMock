package com.dxm.anymock.common.dal.dao;

import com.dxm.anymock.common.dal.entity.TcpInterfaceDO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceKeyBO;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by jff on 2019/8/26.
 */
public interface TcpInterfaceDao {

    /**
     * 新增tcp mock接口
     *
     * @param tcpInterfaceBO
     * @return
     */
    Long create(TcpInterfaceBO tcpInterfaceBO);

    /**
     * 根据接口id查询
     *
     * @param interfaceId
     * @return
     */
    TcpInterfaceBO queryById(Long interfaceId);

    /**
     * 根据唯一索引查询
     *
     * @param tcpInterfaceDO
     * @return
     */
    TcpInterfaceDO queryByKey(TcpInterfaceDO tcpInterfaceDO);


    /**
     * BO -> DO
     *
     * @param tcpInterfaceBO
     * @return
     */
    TcpInterfaceDO convertToDO(TcpInterfaceBO tcpInterfaceBO);


    /**
     * query all tcp interface
     *
     * @return
     */
    List<TcpInterfaceBO> getAllTcpInterface();

    /**
     * @param tcpInterfaceBO
     * @param isStart
     */
    void updateStatus(TcpInterfaceBO tcpInterfaceBO, Boolean isStart);

    /**
     * update
     *
     * @param tcpInterfaceBO
     */
    void update(TcpInterfaceBO tcpInterfaceBO);


    /**
     * query by keyBo
     *
     * @param tcpInterfaceKeyBO
     * @return
     */
    TcpInterfaceBO queryByKey(TcpInterfaceKeyBO tcpInterfaceKeyBO);

    /**
     * delete
     *
     * @param id
     */
    void delete(Long id);

    List<TcpInterfaceBO> queryBySpaceIdWithRowBoundsOrderByClause(Long spaceId, RowBounds rowBounds,
                                                                  String orderByClause);

    Long countBySpaceId(Long spaceId);

    Long countAll();

    List<TcpInterfaceBO> queryAllWithRowBoundsOrderByClause(RowBounds rowBounds, String orderByClause);
}
