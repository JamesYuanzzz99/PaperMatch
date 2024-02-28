package com.ruoyi.match.service.impl;

import java.util.*;

import com.ruoyi.match.mapper.EtPracticeQuestionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.match.mapper.EtPracticeQuestionBankMapper;
import com.ruoyi.match.domain.EtPracticeQuestionBank;
import com.ruoyi.match.service.IEtPracticeQuestionBankService;

/**
 * 模拟题库Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@Service
public class EtPracticeQuestionBankServiceImpl implements IEtPracticeQuestionBankService 
{
    @Autowired
    private EtPracticeQuestionBankMapper etPracticeQuestionBankMapper;

    /**
     * 查询模拟题库
     * 
     * @param id 模拟题库主键
     * @return 模拟题库
     */
    @Override
    public EtPracticeQuestionBank selectEtPracticeQuestionBankById(String id)
    {
        return etPracticeQuestionBankMapper.selectEtPracticeQuestionBankById(id);
    }

    /**
     * 查询模拟题库列表
     * 
     * @param etPracticeQuestionBank 模拟题库
     * @return 模拟题库
     */
    @Override
    public List<EtPracticeQuestionBank> selectEtPracticeQuestionBankList(EtPracticeQuestionBank etPracticeQuestionBank)
    {
        return etPracticeQuestionBankMapper.selectEtPracticeQuestionBankList(etPracticeQuestionBank);
    }

    @Override
    public List<EtPracticeQuestionBank> selectEtPracticeQuestionBankList2(EtPracticeQuestionBank etPracticeQuestionBank) {
        return etPracticeQuestionBankMapper.selectEtPracticeQuestionBankList2(etPracticeQuestionBank);
    }

    /**
     * 新增模拟题库
     * 
     * @param etPracticeQuestionBank 模拟题库
     * @return 结果
     */
    @Override
    public int insertEtPracticeQuestionBank(EtPracticeQuestionBank etPracticeQuestionBank)
    {
        String replace = UUID.randomUUID().toString().replace("-", "");
        etPracticeQuestionBank.setId(replace);
        etPracticeQuestionBank.setCreateTime(new Date());
        return etPracticeQuestionBankMapper.insertEtPracticeQuestionBank(etPracticeQuestionBank);
    }

    /**
     * 修改模拟题库
     * 
     * @param etPracticeQuestionBank 模拟题库
     * @return 结果
     */
    @Override
    public int updateEtPracticeQuestionBank(EtPracticeQuestionBank etPracticeQuestionBank)
    {
        return etPracticeQuestionBankMapper.updateEtPracticeQuestionBank(etPracticeQuestionBank);
    }

    /**
     * 批量删除模拟题库
     * 
     * @param ids 需要删除的模拟题库主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeQuestionBankByIds(String[] ids)
    {
        return etPracticeQuestionBankMapper.deleteEtPracticeQuestionBankByIds(ids);
    }

    /**
     * 删除模拟题库信息
     * 
     * @param id 模拟题库主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeQuestionBankById(String id)
    {
        return etPracticeQuestionBankMapper.deleteEtPracticeQuestionBankById(id);
    }

    @Override
    public Map<String, String> getQuestionBankIdNameMap(List<String> questionBankIdList) {
        // 根据 question_bank_id 查询对应的 name
        List<Map<String, String>> idNameList = etPracticeQuestionBankMapper.getQuestionBankIdNameMap(questionBankIdList);

        // 构建映射关系
        Map<String, String> idNameMap = new HashMap<>();
        for (Map<String, String> idName : idNameList) {
            idNameMap.put(idName.get("question_bank_id"), idName.get("name"));
        }
        return idNameMap;
    }

    @Override
    public Map<String, String> getIdValueMap(List<String> idList) {
        // 根据 id 查询对应的 name 列表
        List<Map<String, String>> idNameList = etPracticeQuestionBankMapper.getIdNameList(idList);

        // 构建映射关系
        Map<String, String> idNameMap = new HashMap<>();
        for (Map<String, String> idName : idNameList) {
            idNameMap.put(idName.get("id"), idName.get("name"));
        }

        return idNameMap;
    }

}
