package com.ruoyi.match.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ruoyi.match.domain.EtPracticeQuestionOption;
import com.ruoyi.match.mapper.EtPracticeQuestionBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.match.mapper.EtPracticeTestPaperMapper;
import com.ruoyi.match.domain.EtPracticeTestPaper;
import com.ruoyi.match.service.IEtPracticeTestPaperService;

import javax.annotation.Resource;

/**
 * 编辑试卷Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
@Service
public class EtPracticeTestPaperServiceImpl implements IEtPracticeTestPaperService 
{
    @Resource
    private EtPracticeTestPaperMapper etPracticeTestPaperMapper;
    @Resource
    private EtPracticeQuestionBankMapper etPracticeQuestionBankMapper;

    /**
     * 查询编辑试卷
     * 
     * @param id 编辑试卷主键
     * @return 编辑试卷
     */
    @Override
    public EtPracticeTestPaper selectEtPracticeTestPaperById(String id)
    {
        return etPracticeTestPaperMapper.selectEtPracticeTestPaperById(id);
    }

    /**
     * 查询编辑试卷列表
     * 
     * @param etPracticeTestPaper 编辑试卷
     * @return 编辑试卷
     */
    @Override
    public List<EtPracticeTestPaper> selectEtPracticeTestPaperList(EtPracticeTestPaper etPracticeTestPaper)
    {
        return etPracticeTestPaperMapper.selectEtPracticeTestPaperList(etPracticeTestPaper);
    }

    /**
     * 新增编辑试卷
     * 
     * @param etPracticeTestPaper 编辑试卷
     * @return 结果
     */
    @Override
    public int insertEtPracticeTestPaper(EtPracticeTestPaper etPracticeTestPaper)
    {

        String replace = UUID.randomUUID().toString().replace("-", "");
        etPracticeTestPaper.setId(replace);
        etPracticeTestPaper.setCreateTime(new Date());

        String way = etPracticeTestPaper.getGroupWay();
        if (way.equals("人工"))way = "1";
        else way = "2";
        etPracticeTestPaper.setGroupWay(way);
        String bankid = etPracticeTestPaper.getQuestionBankId();
        etPracticeTestPaper.setQuestionBankName(etPracticeQuestionBankMapper.selectEtPracticeQuestionBankById(bankid).getName());

        return etPracticeTestPaperMapper.insertEtPracticeTestPaper(etPracticeTestPaper);
    }

    /**
     * 修改编辑试卷
     * 
     * @param etPracticeTestPaper 编辑试卷
     * @return 结果
     */
    @Override
    public int updateEtPracticeTestPaper(EtPracticeTestPaper etPracticeTestPaper)
    {
        return etPracticeTestPaperMapper.updateEtPracticeTestPaper(etPracticeTestPaper);
    }

    /**
     * 批量删除编辑试卷
     * 
     * @param ids 需要删除的编辑试卷主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeTestPaperByIds(String[] ids)
    {
        return etPracticeTestPaperMapper.deleteEtPracticeTestPaperByIds(ids);
    }

    /**
     * 删除编辑试卷信息
     * 
     * @param id 编辑试卷主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeTestPaperById(String id)
    {
        return etPracticeTestPaperMapper.deleteEtPracticeTestPaperById(id);
    }
}
