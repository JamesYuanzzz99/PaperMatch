package com.ruoyi.match.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.match.domain.EtPracticeQuestionBank;

/**
 * 模拟题库Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public interface EtPracticeQuestionBankMapper 
{
    /**
     * 查询模拟题库
     * 
     * @param id 模拟题库主键
     * @return 模拟题库
     */
    public EtPracticeQuestionBank selectEtPracticeQuestionBankById(String id);

    /**
     * 查询模拟题库列表
     * 
     * @param etPracticeQuestionBank 模拟题库
     * @return 模拟题库集合
     */
    public List<EtPracticeQuestionBank> selectEtPracticeQuestionBankList(EtPracticeQuestionBank etPracticeQuestionBank);
    public List<EtPracticeQuestionBank> selectEtPracticeQuestionBankList2(EtPracticeQuestionBank etPracticeQuestionBank);

    /**
     * 新增模拟题库
     * 
     * @param etPracticeQuestionBank 模拟题库
     * @return 结果
     */
    public int insertEtPracticeQuestionBank(EtPracticeQuestionBank etPracticeQuestionBank);

    /**
     * 修改模拟题库
     * 
     * @param etPracticeQuestionBank 模拟题库
     * @return 结果
     */
    public int updateEtPracticeQuestionBank(EtPracticeQuestionBank etPracticeQuestionBank);

    /**
     * 删除模拟题库
     * 
     * @param id 模拟题库主键
     * @return 结果
     */
    public int deleteEtPracticeQuestionBankById(String id);

    /**
     * 批量删除模拟题库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEtPracticeQuestionBankByIds(String[] ids);

    List<Map<String, String>> getQuestionBankIdNameMap(List<String> questionBankIdList);

    List<Map<String, String>> getIdNameList(List<String> idList);

}
