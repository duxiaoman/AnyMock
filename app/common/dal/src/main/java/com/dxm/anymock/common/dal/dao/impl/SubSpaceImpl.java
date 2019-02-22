package com.dxm.anymock.common.dal.dao.impl;

import com.dxm.anymock.common.base.utils.ConvertUtils;
import com.dxm.anymock.common.base.entity.SubSpace;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.SubSpaceDao;
import com.dxm.anymock.common.dal.entity.SubSpacePO;
import com.dxm.anymock.common.dal.entity.SubSpacePOExample;
import com.dxm.anymock.common.dal.mapper.auto.SubSpacePOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SubSpaceImpl implements SubSpaceDao {

    @Autowired
    private SubSpacePOMapper subSpacePOMapper;

    @Override
    public List<SubSpace> selectBySpaceId(Long spaceId) {
        SubSpacePOExample example = new SubSpacePOExample();
        example.createCriteria().andSpaceIdEqualTo(spaceId);
        return ConvertUtils.convert(subSpacePOMapper.selectByExample(example), SubSpace.class);
    }

    @Override
    public void insert(SubSpace subSpace) {
        int resultSize;
        try {
            resultSize = subSpacePOMapper.insert(ConvertUtils.convert(subSpace, SubSpacePO.class));
        } catch (DuplicateKeyException e) {
            throw new BaseException(ErrorCode.SUB_SPACE_DUPLICATE_KEY);
        }

        if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public void update(SubSpace subSpace) {
        int resultSize;
        try {
            resultSize = subSpacePOMapper.updateByPrimaryKey(ConvertUtils.convert(subSpace, SubSpacePO.class));
        } catch (DuplicateKeyException e) {
            throw new BaseException(ErrorCode.SUB_SPACE_DUPLICATE_KEY);
        }

        if (resultSize == 0) {
            throw new BaseException(ErrorCode.SUB_SPACE_NOT_FOUND);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public void delete(Long id) {
        int resultSize = subSpacePOMapper.deleteByPrimaryKey(id);
        if (resultSize == 0) {
            throw new BaseException(ErrorCode.SUB_SPACE_NOT_FOUND);
        } else if (resultSize != 1) {
            throw new IncorrectResultSizeDataAccessException(1, resultSize);
        }
    }

    @Override
    public boolean exists(Long id) {
        return subSpacePOMapper.selectByPrimaryKey(id) != null;
    }
}
