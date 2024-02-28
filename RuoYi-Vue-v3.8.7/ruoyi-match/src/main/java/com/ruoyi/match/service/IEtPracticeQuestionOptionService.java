package com.ruoyi.match.service;

import java.util.List;
import com.ruoyi.match.domain.EtPracticeQuestionOption;

/**
 * 试题选项Service接口
 * 
 * @author yhx
 * @date 2024-01-22
 */
public interface IEtPracticeQuestionOptionService 
{
    /**
     * 查询试题选项
     * 
     * @param id 试题选项主键
     * @return 试题选项
     */
    public EtPracticeQuestionOption selectEtPracticeQuestionOptionById(String id);

    /**
     * 查询试题选项列表
     * 
     * @param etPracticeQuestionOption 试题选项
     * @return 试题选项集合
     */
    public List<EtPracticeQuestionOption> selectEtoptions(EtPracticeQuestionOption etPracticeQuestionOption);

    public List<EtPracticeQuestionOption> selectEtoptions(String questionId);

    /**
     * 新增试题选项
     * 
     * @param etPracticeQuestionOption 试题选项
     * @return 结果
     */
    public int insertEtPracticeQuestionOption(EtPracticeQuestionOption etPracticeQuestionOption);

    /**
     * 修改试题选项
     * 
     * @param etPracticeQuestionOption 试题选项
     * @return 结果
     */
    public int updateEtPracticeQuestionOption(EtPracticeQuestionOption etPracticeQuestionOption);

    /**
     * 批量删除试题选项
     * 
     * @param ids 需要删除的试题选项主键集合
     * @return 结果
     */
    public int deleteEtPracticeQuestionOptionByIds(String[] ids);

    /**
     * 删除试题选项信息
     * 
     * @param id 试题选项主键
     * @return 结果
     */
    public int deleteEtPracticeQuestionOptionById(String id);
}
