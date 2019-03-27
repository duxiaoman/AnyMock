package com.dxm.anymock.common.dal.mapper.auto;

import com.dxm.anymock.common.dal.entity.HttpInterfaceHeaderDO;
import com.dxm.anymock.common.dal.entity.HttpInterfaceHeaderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HttpInterfaceHeaderDOMapper {
    long countByExample(HttpInterfaceHeaderDOExample example);

    int deleteByExample(HttpInterfaceHeaderDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HttpInterfaceHeaderDO record);

    int insertSelective(HttpInterfaceHeaderDO record);

    List<HttpInterfaceHeaderDO> selectByExampleWithRowbounds(HttpInterfaceHeaderDOExample example, RowBounds rowBounds);

    List<HttpInterfaceHeaderDO> selectByExample(HttpInterfaceHeaderDOExample example);

    HttpInterfaceHeaderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HttpInterfaceHeaderDO record, @Param("example") HttpInterfaceHeaderDOExample example);

    int updateByExample(@Param("record") HttpInterfaceHeaderDO record, @Param("example") HttpInterfaceHeaderDOExample example);

    int updateByPrimaryKeySelective(HttpInterfaceHeaderDO record);

    int updateByPrimaryKey(HttpInterfaceHeaderDO record);
}