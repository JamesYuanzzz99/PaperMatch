package com.ruoyi.match.mapper;

import java.util.List;
import com.ruoyi.match.domain.EtPracticeQuestionOption;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * 试题选项Mapper接口
 * 
 * @author yhx
 * @date 2024-01-22
 */
public interface EtPracticeQuestionOptionMapper
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

    public List<EtPracticeQuestionOption> selectEtoptions2(String questionId);

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
     * 删除试题选项
     * 
     * @param id 试题选项主键
     * @return 结果
     */
    public int deleteEtPracticeQuestionOptionById(String id);

    /**
     * 批量删除试题选项
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEtPracticeQuestionOptionByIds(String[] ids);

    List<EtPracticeQuestionOption> selectQuestionOptionListByQuestionInfoId(String id);
}
