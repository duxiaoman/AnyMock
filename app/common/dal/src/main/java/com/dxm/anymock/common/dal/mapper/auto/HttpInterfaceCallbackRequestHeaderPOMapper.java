package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceCallbackRequestHeaderPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceCallbackRequestHeaderPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HttpInterfaceCallbackRequestHeaderPOMapper {
    long countByExample(HttpInterfaceCallbackRequestHeaderPOExample example);

    int deleteByExample(HttpInterfaceCallbackRequestHeaderPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceCallbackRequestHeaderPO record);

    int insertSelective(HttpInterfaceCallbackRequestHeaderPO record);

    List<HttpInterfaceCallbackRequestHeaderPO> selectByExample(HttpInterfaceCallbackRequestHeaderPOExample example);

    HttpInterfaceCallbackRequestHeaderPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceCallbackRequestHeaderPO record, @Param("example") HttpInterfaceCallbackRequestHeaderPOExample example);

    int updateByExample(@Param("record") HttpInterfaceCallbackRequestHeaderPO record, @Param("example") HttpInterfaceCallbackRequestHeaderPOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceCallbackRequestHeaderPO record);

    int updateByPrimaryKey(HttpInterfaceCallbackRequestHeaderPO record);
}