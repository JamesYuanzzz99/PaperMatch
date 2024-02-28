package com.ruoyi.match.service;

import java.util.List;
import com.ruoyi.match.domain.EtPracticeTestPaper;

/**
 * 编辑试卷Service接口
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public interface IEtPracticeTestPaperService 
{
    /**
     * 查询编辑试卷
     * 
     * @param id 编辑试卷主键
     * @return 编辑试卷
     */
    public EtPracticeTestPaper selectEtPracticeTestPaperById(String id);

    /**
     * 查询编辑试卷列表
     * 
     * @param etPracticeTestPaper 编辑试卷
     * @return 编辑试卷集合
     */
    public List<EtPracticeTestPaper> selectEtPracticeTestPaperList(EtPracticeTestPaper etPracticeTestPaper);

    /**
     * 新增编辑试卷
     * 
     * @param etPracticeTestPaper 编辑试卷
     * @return 结果
     */
    public int insertEtPracticeTestPaper(EtPracticeTestPaper etPracticeTestPaper);

    /**
     * 修改编辑试卷
     * 
     * @param etPracticeTestPaper 编辑试卷
     * @return 结果
     */
    public int updateEtPracticeTestPaper(EtPracticeTestPaper etPracticeTestPaper);

    /**
     * 批量删除编辑试卷
     * 
     * @param ids 需要删除的编辑试卷主键集合
     * @return 结果
     */
    public int deleteEtPracticeTestPaperByIds(String[] ids);

    /**
     * 删除编辑试卷信息
     * 
     * @param id 编辑试卷主键
     * @return 结果
     */
    public int deleteEtPracticeTestPaperById(String id);
}
