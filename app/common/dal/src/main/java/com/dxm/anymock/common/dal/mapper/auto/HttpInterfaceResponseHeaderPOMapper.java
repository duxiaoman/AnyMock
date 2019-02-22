package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceResponseHeaderPO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceResponseHeaderPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HttpInterfaceResponseHeaderPOMapper {
    long countByExample(HttpInterfaceResponseHeaderPOExample example);

    int deleteByExample(HttpInterfaceResponseHeaderPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceResponseHeaderPO record);

    int insertSelective(HttpInterfaceResponseHeaderPO record);

    List<HttpInterfaceResponseHeaderPO> selectByExample(HttpInterfaceResponseHeaderPOExample example);

    HttpInterfaceResponseHeaderPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceResponseHeaderPO record, @Param("example") HttpInterfaceResponseHeaderPOExample example);

    int updateByExample(@Param("record") HttpInterfaceResponseHeaderPO record, @Param("example") HttpInterfaceResponseHeaderPOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceResponseHeaderPO record);

    int updateByPrimaryKey(HttpInterfaceResponseHeaderPO record);
}