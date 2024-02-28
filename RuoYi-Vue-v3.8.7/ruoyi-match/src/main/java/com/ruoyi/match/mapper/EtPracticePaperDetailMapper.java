package com.ruoyi.match.mapper;

import java.util.List;
import com.ruoyi.match.domain.EtPracticePaperDetail;

/**
 * 模拟试卷详情Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public interface EtPracticePaperDetailMapper 
{
    /**
     * 查询模拟试卷详情
     * 
     * @param id 模拟试卷详情主键
     * @return 模拟试卷详情
     */
    public EtPracticePaperDetail selectEtPracticePaperDetailById(String id);
    public EtPracticePaperDetail selectEtPracticePaperDetailByQuestionId(String id);

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
     * @param etPracticePaperDetailList 模拟试卷详情
     * @return 结果
     */
    public int insertEtPracticePaperDetail(EtPracticePaperDetail etPracticePaperDetail);

    /**
     * 修改模拟试卷详情
     * 
     * @param etPracticePaperDetail 模拟试卷详情
     * @return 结果
     */
    public int updateEtPracticePaperDetail(EtPracticePaperDetail etPracticePaperDetail);

    /**
     * 删除模拟试卷详情
     * 
     * @param id 模拟试卷详情主键
     * @return 结果
     */
    public int deleteEtPracticePaperDetailById(String id);

    /**
     * 批量删除模拟试卷详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEtPracticePaperDetailByIds(String[] ids);
    public int deleteEtPracticePaperDetailByPaperId(String paperid);
}
