package com.ruoyi.match.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.match.mapper.EtQuestionTypeMapper;
import com.ruoyi.match.domain.EtQuestionType;
import com.ruoyi.match.service.IEtQuestionTypeService;

/**
 * 试题类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-18
 */
@Service
public class EtQuestionTypeServiceImpl implements IEtQuestionTypeService 
{
    @Autowired
    private EtQuestionTypeMapper etQuestionTypeMapper;

    /**
     * 查询试题类型
     * 
     * @param id 试题类型主键
     * @return 试题类型
     */
    @Override
    public EtQuestionType selectEtQuestionTypeById(Long id)
    {
        return etQuestionTypeMapper.selectEtQuestionTypeById(id);
    }

    /**
     * 查询试题类型列表
     * 
     * @param etQuestionType 试题类型
     * @return 试题类型
     */
    @Override
    public List<EtQuestionType> selectEtQuestionTypeList(EtQuestionType etQuestionType)
    {
        return etQuestionTypeMapper.selectEtQuestionTypeList(etQuestionType);
    }

    /**
     * 新增试题类型
     * 
     * @param etQuestionType 试题类型
     * @return 结果
     */
    @Override
    public int insertEtQuestionType(EtQuestionType etQuestionType)
    {
        return etQuestionTypeMapper.insertEtQuestionType(etQuestionType);
    }

    /**
     * 修改试题类型
     * 
     * @param etQuestionType 试题类型
     * @return 结果
     */
    @Override
    public int updateEtQuestionType(EtQuestionType etQuestionType)
    {
        return etQuestionTypeMapper.updateEtQuestionType(etQuestionType);
    }

    /**
     * 批量删除试题类型
     * 
     * @param ids 需要删除的试题类型主键
     * @return 结果
     */
    @Override
    public int deleteEtQuestionTypeByIds(Long[] ids)
    {
        return etQuestionTypeMapper.deleteEtQuestionTypeByIds(ids);
    }

    /**
     * 删除试题类型信息
     * 
     * @param id 试题类型主键
     * @return 结果
     */
    @Override
    public int deleteEtQuestionTypeById(Long id)
    {
        return etQuestionTypeMapper.deleteEtQuestionTypeById(id);
    }
}
