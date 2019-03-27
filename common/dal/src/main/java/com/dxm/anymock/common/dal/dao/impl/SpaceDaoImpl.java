package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.IncorrectResultSizeException;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.model.SpaceBO;
import com.dxm.anymock.common.dal.entity.SpaceDO;
import com.dxm.anymock.common.dal.entity.SpaceDOExample;
import com.dxm.anymock.common.dal.mapper.auto.SpaceDOMapper;
import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SpaceDaoImpl implements SpaceDao {

    @Autowired
    private SpaceDOMapper spaceDOMapper;

    @Override
    public SpaceBO queryById(Long id) {
        SpaceDO spaceDO = spaceDOMapper.selectByPrimaryKey(id);
        if (spaceDO == null) {
            return null;
        }
        return convertToBO(spaceDO);
    }

    @Override
    public List<SpaceBO> queryByParentId(Long parentId) {
        return queryByParentIdOrderByClause(parentId, null);
    }

    @Override
    public List<SpaceBO> queryByParentIdOrderByClause(Long parentId, String orderByClause) {
        SpaceDOExample example = new SpaceDOExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause(orderByClause);
        return convertToBOList(spaceDOMapper.selectByExample(example));
    }

    @Override
    public void create(SpaceBO spaceBO) {
        SpaceDO spaceDO = convertToDO(spaceBO);
        Date now = new Date();
        spaceDO.setId(null);
        spaceDO.setGmtCreate(now);
        spaceDO.setGmtModified(now);

        int resultSize;
        try {
            resultSize = spaceDOMapper.insert(spaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_SPACE);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }

    @Override
    public void update(SpaceBO spaceBO) {
        SpaceDO spaceDO = convertToDO(spaceBO);
        spaceDO.setGmtCreate(null);
        spaceDO.setGmtModified(new Date());

        int resultSize;
        try {
            resultSize = spaceDOMapper.updateByPrimaryKeySelective(spaceDO);
        } catch (DuplicateKeyException e) {
            throw new BizException(ResultCode.DUPLICATE_KEY_SPACE);
        }

        if (resultSize == 0) {
            throw new BizException(ResultCode.NOT_FOUND_SPACE);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }

    @Override
    public void delete(Long id) {
        int resultSize = spaceDOMapper.deleteByPrimaryKey(id);
        if (resultSize == 0) {
            throw new BizException(ResultCode.NOT_FOUND_SPACE);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeException(resultSize);
        }
    }

    private SpaceBO convertToBO(SpaceDO spaceDO) {
        SpaceBO spaceBO = new SpaceBO();
        BeanUtils.copyProperties(spaceDO, spaceBO);
        spaceBO.setAccessAuthority(EnumUtils.getEnum(AccessAuthority.class, spaceDO.getAccessAuthority()));
        return spaceBO;
    }

    private List<SpaceBO> convertToBOList(List<SpaceDO> spaceDOList) {
        List<SpaceBO> spaceBOList = new LinkedList<>();
        spaceDOList.forEach(spaceDO -> spaceBOList.add(convertToBO(spaceDO)));
        return spaceBOList;
    }

    private SpaceDO convertToDO(SpaceBO spaceBO) {
        if (spaceBO == null) {
            return null;
        }
        SpaceDO spaceDO = new SpaceDO();
        BeanUtils.copyProperties(spaceBO, spaceDO);
        if (spaceBO.getAccessAuthority() != null) {
            spaceDO.setAccessAuthority(spaceBO.getAccessAuthority().name());
        }
        return spaceDO;
    }
}
