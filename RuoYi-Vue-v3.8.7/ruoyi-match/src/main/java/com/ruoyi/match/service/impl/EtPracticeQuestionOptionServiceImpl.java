package com.ruoyi.match.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.match.mapper.EtPracticeQuestionOptionMapper;
import com.ruoyi.match.domain.EtPracticeQuestionOption;
import com.ruoyi.match.service.IEtPracticeQuestionOptionService;

import javax.annotation.Resource;

/**
 * 试题选项Service业务层处理
 * 
 * @author yhx
 * @date 2024-01-22
 */
@Service
public class EtPracticeQuestionOptionServiceImpl implements IEtPracticeQuestionOptionService 
{
    @Resource
    private EtPracticeQuestionOptionMapper etPracticeQuestionOptionMapper;

    /**
     * 查询试题选项
     * 
     * @param id 试题选项主键
     * @return 试题选项
     */
    @Override
    public EtPracticeQuestionOption selectEtPracticeQuestionOptionById(String id)
    {
        return etPracticeQuestionOptionMapper.selectEtPracticeQuestionOptionById(id);
    }

    /**
     * 查询试题选项列表
     * 
     * @param etPracticeQuestionOption 试题选项
     * @return 试题选项
     */
    @Override
    public List<EtPracticeQuestionOption> selectEtoptions(EtPracticeQuestionOption etPracticeQuestionOption)
    {
        return etPracticeQuestionOptionMapper.selectEtoptions(etPracticeQuestionOption);
    }

    @Override
    public List<EtPracticeQuestionOption> selectEtoptions(String questionId) {
        return etPracticeQuestionOptionMapper.selectEtoptions2(questionId);
    }

    /**
     * 新增试题选项
     * 
     * @param etPracticeQuestionOption 试题选项
     * @return 结果
     */
    @Override
    public int insertEtPracticeQuestionOption(EtPracticeQuestionOption etPracticeQuestionOption)
    {
        String replace = UUID.randomUUID().toString().replace("-", "");
        etPracticeQuestionOption.setId(replace);
        etPracticeQuestionOption.setCreateTime(new Date());
        return etPracticeQuestionOptionMapper.insertEtPracticeQuestionOption(etPracticeQuestionOption);
    }

    /**
     * 修改试题选项
     * 
     * @param etPracticeQuestionOption 试题选项
     * @return 结果
     */
    @Override
    public int updateEtPracticeQuestionOption(EtPracticeQuestionOption etPracticeQuestionOption)
    {
        return etPracticeQuestionOptionMapper.updateEtPracticeQuestionOption(etPracticeQuestionOption);
    }

    /**
     * 批量删除试题选项
     * 
     * @param ids 需要删除的试题选项主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeQuestionOptionByIds(String[] ids)
    {
        return etPracticeQuestionOptionMapper.deleteEtPracticeQuestionOptionByIds(ids);
    }

    /**
     * 删除试题选项信息
     * 
     * @param id 试题选项主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeQuestionOptionById(String id)
    {
        return etPracticeQuestionOptionMapper.deleteEtPracticeQuestionOptionById(id);
    }
}
