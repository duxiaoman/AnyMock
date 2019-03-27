package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HttpInterfaceDOMapper {
    long countByExample(HttpInterfaceDOExample example);

    int deleteByExample(HttpInterfaceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceDO record);

    int insertSelective(HttpInterfaceDO record);

    List<HttpInterfaceDO> selectByExampleWithRowbounds(HttpInterfaceDOExample example, RowBounds rowBounds);

    List<HttpInterfaceDO> selectByExample(HttpInterfaceDOExample example);

    HttpInterfaceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceDO record, @Param("example") HttpInterfaceDOExample example);

    int updateByExample(@Param("record") HttpInterfaceDO record, @Param("example") HttpInterfaceDOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceDO record);

    int updateByPrimaryKey(HttpInterfaceDO record);
}