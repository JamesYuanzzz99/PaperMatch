package com.ruoyi.match.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.match.domain.EtPracticePaperDetail;
import com.ruoyi.match.domain.EtPracticeQuestionInfo;
import com.ruoyi.match.domain.EtPracticeTestPaper;

/**
 * 模拟试卷详情Service接口
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public interface IEtPracticePaperDetailService 
{
    /**
     * 查询模拟试卷详情
     * 
     * @param id 模拟试卷详情主键
     * @return 模拟试卷详情
     */
    public EtPracticePaperDetail selectEtPracticePaperDetailById(String id);

    public List<EtPracticePaperDetail> selectEtPracticePaperDetailByPaperId(String paperid);


    /**
     * 查询模拟试卷详情列表
     * 
     * @param etPracticePaperDetail 模拟试卷详情
     * @return 模拟试卷详情集合
     */
    public List<EtPracticePaperDetail> selectEtPracticePaperDetailList(EtPracticePaperDetail etPracticePaperDetail);

    /**
     * 新增模拟试卷详情
     * 
//     * @param etPracticePaperDetail 模拟试卷详情
     * @return 结果
     */
    public int insertEtPracticePaperDetail(Map<String ,List<String> >map);

    /**
     * 修改模拟试卷详情
     * 
     * @param  模拟试卷详情
     * @return 结果
     */
    public int updateEtPracticePaperDetail(Map<String ,List<EtPracticePaperDetail> >map);

    /**
     * 批量删除模拟试卷详情
     * 
     * @param ids 需要删除的模拟试卷详情主键集合
     * @return 结果
     */
    public int deleteEtPracticePaperDetailByIds(String[] ids);

    /**
     * 删除模拟试卷详情信息
     * 
     * @param id 模拟试卷详情主键
     * @return 结果
     */
    public int deleteEtPracticePaperDetailById(String id);


}
