package com.ruoyi.match.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.match.domain.EtPracticeQuestionInfo;
import com.ruoyi.match.mapper.EtPracticeQuestionInfoMapper;
import com.ruoyi.match.service.IEtPracticeQuestionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.match.mapper.EtPracticePaperDetailMapper;
import com.ruoyi.match.domain.EtPracticePaperDetail;
import com.ruoyi.match.service.IEtPracticePaperDetailService;

import javax.annotation.Resource;

/**
 * 模拟试卷详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
@Service
public class EtPracticePaperDetailServiceImpl implements IEtPracticePaperDetailService 
{
    @Resource
    private EtPracticePaperDetailMapper etPracticePaperDetailMapper;

    @Resource
    private EtPracticeQuestionInfoMapper etPracticeQuestionInfoMapper;
    @Resource
    private IEtPracticeQuestionInfoService etPracticeQuestionInfoService;

    /**
     * 查询模拟试卷详情
     * 
     * @param id 模拟试卷详情主键
     * @return 模拟试卷详情
     */
    @Override
    public EtPracticePaperDetail selectEtPracticePaperDetailById(String id)
    {

        return etPracticePaperDetailMapper.selectEtPracticePaperDetailById(id);
    }

    @Override
    public List<EtPracticePaperDetail> selectEtPracticePaperDetailByPaperId(String paperid) {
        List<EtPracticePaperDetail> list = etPracticePaperDetailMapper.selectEtPracticePaperDetailByPaperId(paperid);
        return list;
//        List<EtPracticeQuestionInfo> list2 = new ArrayList<>(); // 初始化为一个空的ArrayList
//
//        for (EtPracticePaperDetail etPracticePaperDetail : list) {
//            if (etPracticeQuestionInfoMapper.selectEtPracticeQuestionInfoById(etPracticePaperDetail.getQuestionId())!=null) {
//
//                list2.add(etPracticeQuestionInfoMapper.selectEtPracticeQuestionInfoById(etPracticePaperDetail.getQuestionId()));
//            }
//        }
//
//        // 获取 EtPracticeQuestionInfo 的 question_bank_id 列表
//        List<String> questionBankIdList = list2.stream()
//                .map(EtPracticeQuestionInfo::getQuestionBankId)
//                .collect(Collectors.toList());
//
//        // 通过 etPracticeQuestionInfoService 获取 HashMap 映射（question_bank_id 到 name 的映射）
//        Map<String, String> questionBankIdNameMap =
//                etPracticeQuestionInfoService.getQuestionBankIdNameMap(questionBankIdList);
//
//        // 将映射值覆盖到 EtPracticeQuestionInfo 对象中
//        list2.forEach(info -> {
//            String questionBankName = questionBankIdNameMap.get(info.getQuestionBankId());
//            info.setQuestionBankId(questionBankName);
//        });
//
//        return list2;
    }


    /**
     * 查询模拟试卷详情列表
     * 
     * @param etPracticePaperDetail 模拟试卷详情
     * @return 模拟试卷详情
     */
    @Override
    public List<EtPracticePaperDetail> selectEtPracticePaperDetailList(EtPracticePaperDetail etPracticePaperDetail)
    {
        return etPracticePaperDetailMapper.selectEtPracticePaperDetailList(etPracticePaperDetail);
    }

    /**
     * 新增模拟试卷详情
     * 
     * @return 结果
     */
    @Override
    public int insertEtPracticePaperDetail(Map<String,List<String>  >map)
    {
        for (Map.Entry<String,List<String>> entry : map.entrySet()) {
            String paperId = entry.getKey();

            List<String> etPracticeQuestionInfoList = entry.getValue();

            for (String etPracticeQuestionInfo : etPracticeQuestionInfoList) {
                // 具体的逻辑...
                String questionid = etPracticeQuestionInfo;
                EtPracticePaperDetail etPracticePaperDetail = new EtPracticePaperDetail();
                etPracticePaperDetail.setQuestionId(questionid);
                etPracticePaperDetail.setPaperId(paperId);

                String replace2 = UUID.randomUUID().toString().replace("-", "");
                etPracticePaperDetail.setId(replace2);
                etPracticePaperDetail.setCreateTime(new Date());
                etPracticePaperDetailMapper.insertEtPracticePaperDetail(etPracticePaperDetail);
            }
        }

        return 1;
    }

    /**
     * 修改模拟试卷详情
     * 
     * @param
     * @return 结果
     */
    @Override
    public int updateEtPracticePaperDetail(Map<String ,List<EtPracticePaperDetail> >map)
    {
        for (Map.Entry<String,List<EtPracticePaperDetail>> entry : map.entrySet()) {
            String paperId = entry.getKey();

            List<EtPracticePaperDetail> etPracticeQuestionInfoList = entry.getValue();
            etPracticePaperDetailMapper.deleteEtPracticePaperDetailByPaperId(paperId);

            for (EtPracticePaperDetail etPracticeQuestionInfo : etPracticeQuestionInfoList) {
                String questionid = etPracticeQuestionInfo.getQuestionId();
                if (etPracticePaperDetailMapper.selectEtPracticePaperDetailByQuestionId(questionid)==null) {
                    EtPracticePaperDetail etPracticePaperDetail = new EtPracticePaperDetail();
                    etPracticePaperDetail.setQuestionId(questionid);
                    etPracticePaperDetail.setPaperId(paperId);

                    String replace2 = UUID.randomUUID().toString().replace("-", "");
                    etPracticePaperDetail.setId(replace2);
                    etPracticePaperDetail.setCreateTime(new Date());
                    etPracticePaperDetailMapper.insertEtPracticePaperDetail(etPracticePaperDetail);
                }
            }
        }

        return 1;
    }

    /**
     * 批量删除模拟试卷详情
     * 
     * @param ids 需要删除的模拟试卷详情主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticePaperDetailByIds(String[] ids)
    {
        return etPracticePaperDetailMapper.deleteEtPracticePaperDetailByIds(ids);
    }

    /**
     * 删除模拟试卷详情信息
     * 
     * @param id 模拟试卷详情主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticePaperDetailById(String id)
    {
        return etPracticePaperDetailMapper.deleteEtPracticePaperDetailById(id);
    }


}
