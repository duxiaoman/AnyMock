package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.utils.ConvertUtils;
import com.dxm.anymock.common.base.entity.Space;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.SpaceDao;
import com.dxm.anymock.common.dal.entity.SpacePO;
import com.dxm.anymock.common.dal.entity.SpacePOExample;
import com.dxm.anymock.common.dal.mapper.auto.SpacePOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SpaceDaoImpl implements SpaceDao {

    @Autowired
    private SpacePOMapper spacePOMapper;

    @Override
    public List<Space> selectByParentId(Long parentId) {
        SpacePOExample example = new SpacePOExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return ConvertUtils.convert(spacePOMapper.selectByExample(example), Space.class);
    }

    @Override
    public void insert(Space space) {
        int resultSize;
        try {
            resultSize = spacePOMapper.insert(ConvertUtils.convert(space, SpacePO.class));
        } catch (DuplicateKeyException e) {
            throw new BaseException(ErrorCode.SPACE_DUPLICATE_KEY);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public void update(Space space) {
        int resultSize;
        try {
            resultSize = spacePOMapper.updateByPrimaryKeySelective(ConvertUtils.convert(space, SpacePO.class));
        } catch (DuplicateKeyException e) {
            throw new BaseException(ErrorCode.SPACE_DUPLICATE_KEY);
        }

        if (resultSize == 0) {
            throw new BaseException(ErrorCode.SPACE_NOT_FOUND);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public void delete(Long id) {
        int resultSize = spacePOMapper.deleteByPrimaryKey(id);
        if (resultSize == 0) {
            throw new BaseException(ErrorCode.SPACE_NOT_FOUND);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public Integer level(Long id) {
        Integer level = 0;
        while (true) {
            if (id.equals(0L)) {
                break;
            }
            SpacePO spacePO = spacePOMapper.selectByPrimaryKey(id);
            if (spacePO != null) {
                level++;
            } else {
                throw new BaseException(ErrorCode.SPACE_NOT_FOUND);
            }
            id = spacePO.getParentId();
        }
        return level;
    }
}
