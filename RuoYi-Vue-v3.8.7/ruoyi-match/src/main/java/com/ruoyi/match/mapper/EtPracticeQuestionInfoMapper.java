package com.ruoyi.match.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.match.domain.EtPracticeQuestionInfo;
import org.springframework.data.repository.query.Param;

/**
 * 模拟试题Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public interface EtPracticeQuestionInfoMapper 
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
     * 删除模拟试题
     * 
     * @param id 模拟试题主键
     * @return 结果
     */
    public int deleteEtPracticeQuestionInfoById(String id);

    /**
     * 批量删除模拟试题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEtPracticeQuestionInfoByIds(String[] ids);


    // 根据条件查询 id 和字段值的映射
    List<Map<String, Object>> getIdValueMap(@Param("condition") EtPracticeQuestionInfo condition);

    int deleteEtPracticeQuestionInfoAnswerFile();
}
