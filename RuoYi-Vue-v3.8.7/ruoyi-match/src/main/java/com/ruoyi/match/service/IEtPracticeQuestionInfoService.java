package com.ruoyi.match.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.match.domain.EtPracticeQuestionInfo;

/**
 * 模拟试题Service接口
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public interface IEtPracticeQuestionInfoService 
{
    /**
     * 查询模拟试题
     * 
     * @param id 模拟试题主键
     * @return 模拟试题
     */
    public EtPracticeQuestionInfo selectEtPracticeQuestionInfoById(String id);

    /**
     * 查询模拟试题列表
     * 
     * @param etPracticeQuestionInfo 模拟试题
     * @return 模拟试题集合
     */
    public List<EtPracticeQuestionInfo> selectEtPracticeQuestionInfoList(EtPracticeQuestionInfo etPracticeQuestionInfo);

    /**
     * 新增模拟试题
     * 
     * @param etPracticeQuestionInfo 模拟试题
     * @return 结果
     */
    public int insertEtPracticeQuestionInfo(EtPracticeQuestionInfo etPracticeQuestionInfo);

    /**
     * 修改模拟试题
     * 
     * @param etPracticeQuestionInfo 模拟试题
     * @return 结果
     */
    public int updateEtPracticeQuestionInfo(EtPracticeQuestionInfo etPracticeQuestionInfo);

    /**
     * 批量删除模拟试题
     * 
     * @param ids 需要删除的模拟试题主键集合
     * @return 结果
     */
    public int deleteEtPracticeQuestionInfoByIds(String[] ids);


    public int deleteEtPracticeQuestionInfoAnswerFile(String filePath);

    /**
     * 删除模拟试题信息
     * 
     * @param id 模拟试题主键
     * @return 结果
     */
    public int deleteEtPracticeQuestionInfoById(String id);

    Map<String, String> getQuestionBankIdNameMap(List<String> questionBankIdList);



}
