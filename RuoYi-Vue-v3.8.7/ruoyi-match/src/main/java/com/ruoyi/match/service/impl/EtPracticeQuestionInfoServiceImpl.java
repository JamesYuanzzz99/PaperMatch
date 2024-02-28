package com.ruoyi.match.service.impl;

import java.io.IOException;
import java.util.*;
import java.nio.file.*;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.match.domain.EtPracticeQuestionOption;
import com.ruoyi.match.mapper.EtPracticeQuestionBankMapper;
import com.ruoyi.match.mapper.EtPracticeQuestionOptionMapper;
import com.ruoyi.match.service.IEtPracticeQuestionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.match.mapper.EtPracticeQuestionInfoMapper;
import com.ruoyi.match.domain.EtPracticeQuestionInfo;
import com.ruoyi.match.service.IEtPracticeQuestionInfoService;
import com.ruoyi.common.utils.file.FileUtils ;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.ruoyi.common.utils.file.FileUploadUtils.*;

/**
 * 模拟试题Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@Service
public class EtPracticeQuestionInfoServiceImpl implements IEtPracticeQuestionInfoService {
    @Resource
    private EtPracticeQuestionInfoMapper etPracticeQuestionInfoMapper;
    @Resource
    private EtPracticeQuestionBankMapper etPracticeQuestionBankMapper;
    @Resource
    private EtPracticeQuestionOptionMapper etPracticeQuestionOptionMapper;


    /**
     * 查询模拟试题
     *
     * @param id 模拟试题主键
     * @return 模拟试题
     */
    @Override
    public EtPracticeQuestionInfo selectEtPracticeQuestionInfoById(String id) {


        EtPracticeQuestionInfo etPracticeQuestionInfo = etPracticeQuestionInfoMapper.selectEtPracticeQuestionInfoById(id);

        if (etPracticeQuestionInfo != null)
        etPracticeQuestionInfo.setoptions(etPracticeQuestionOptionMapper.selectQuestionOptionListByQuestionInfoId(id));

        return etPracticeQuestionInfo;
    }

    /**
     * 查询模拟试题列表
     *
     * @param etPracticeQuestionInfo 模拟试题
     * @return 模拟试题
     */
    @Override
    public List<EtPracticeQuestionInfo> selectEtPracticeQuestionInfoList(EtPracticeQuestionInfo etPracticeQuestionInfo) {



        return etPracticeQuestionInfoMapper.selectEtPracticeQuestionInfoList(etPracticeQuestionInfo);
    }

    /**
     * 新增模拟试题
     *
     * @param etPracticeQuestionInfo 模拟试题
     * @return 结果
     */

    /**/
    @Override
    public int insertEtPracticeQuestionInfo(EtPracticeQuestionInfo etPracticeQuestionInfo) {
        String replace = UUID.randomUUID().toString().replace("-", "");
        etPracticeQuestionInfo.setId(replace);
        etPracticeQuestionInfo.setCreateTime(new Date());

        etPracticeQuestionInfoMapper.insertEtPracticeQuestionInfo(etPracticeQuestionInfo);
        String id = etPracticeQuestionInfo.getId();

        List<EtPracticeQuestionOption> etPracticeQuestionOptionList = etPracticeQuestionInfo.getoptions();


        for (EtPracticeQuestionOption questionOption : etPracticeQuestionOptionList) {
            if (questionOption.getId() == null) {
                // 选项id为空，表示新的选项，执行添加操作
                String replace2 = UUID.randomUUID().toString().replace("-", "");

                questionOption.setId(replace2);
                questionOption.setCreateTime(new Date());
                questionOption.setQuestionId(id);
                questionOption.setOptionNum(questionOption.getOptionNum());
                etPracticeQuestionOptionMapper.insertEtPracticeQuestionOption(questionOption);
            } else {

                // 选项id不为空，表示已存在的选项，执行更新操作
                etPracticeQuestionOptionMapper.updateEtPracticeQuestionOption(questionOption);

            }
        }


        return 1;


    }



    /**
     * 修改模拟试题
     *
     * @param etPracticeQuestionInfo 模拟试题
     * @return 结果
     */
    @Override
    public int updateEtPracticeQuestionInfo(EtPracticeQuestionInfo etPracticeQuestionInfo) {
        etPracticeQuestionInfo.setUpdateTime(new Date());

        String id = etPracticeQuestionInfo.getId();
        List<EtPracticeQuestionOption> etPracticeQuestionOptionList = etPracticeQuestionInfo.getoptions();


        for (EtPracticeQuestionOption questionOption : etPracticeQuestionOptionList) {
            if (questionOption.getId()==null){

                String replace2 = UUID.randomUUID().toString().replace("-", "");

                questionOption.setId(replace2);
                questionOption.setCreateTime(new Date());
                questionOption.setQuestionId(id);
                questionOption.setOptionNum(questionOption.getOptionNum());


                etPracticeQuestionOptionMapper.insertEtPracticeQuestionOption(questionOption);
                continue;
            }
            etPracticeQuestionOptionMapper.updateEtPracticeQuestionOption(questionOption);

        }

        if (etPracticeQuestionInfo.getField1()==null){
            etPracticeQuestionInfoMapper.deleteEtPracticeQuestionInfoAnswerFile();
        }



        return etPracticeQuestionInfoMapper.updateEtPracticeQuestionInfo(etPracticeQuestionInfo);
    }

    /**
     * 批量删除模拟试题
     *
     * @param ids 需要删除的模拟试题主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeQuestionInfoByIds(String[] ids) {
        for (String id : ids) {
            for (EtPracticeQuestionOption etPracticeQuestionOption : etPracticeQuestionOptionMapper.selectQuestionOptionListByQuestionInfoId(id)) {
                etPracticeQuestionOptionMapper.deleteEtPracticeQuestionOptionById(etPracticeQuestionOption.getId());
            }
            EtPracticeQuestionInfo etPracticeQuestionInfo1 = etPracticeQuestionInfoMapper.selectEtPracticeQuestionInfoById(id);

            String field1 = etPracticeQuestionInfo1.getField1();
            if (field1 != null && !field1.isEmpty()) {
                String[] filePaths = field1.split(",");
                for (String filePath : filePaths) {
                    deleteEtPracticeQuestionInfoAnswerFile(filePath.trim());
                }
            }
        }

            return etPracticeQuestionInfoMapper.deleteEtPracticeQuestionInfoByIds(ids);
    }

    @Override
    public int deleteEtPracticeQuestionInfoAnswerFile(String filePath) {
        try {

            filePath = filePath.replace("/profile", getDefaultBaseDir());
            filePath = filePath.replace("/", "\\");

            // 调用 deleteFile 方法删除文件
            if (FileUtils.deleteFile(filePath)) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Unable to delete the file.");
            }
        } catch (Exception e) {
            System.err.println("Error while deleting the file: " + e.getMessage());
        }
        return 1;
    }


    /**
     * 删除模拟试题信息
     *
     * @param id 模拟试题主键
     * @return 结果
     */
    @Override
    public int deleteEtPracticeQuestionInfoById(String id) {
        for (EtPracticeQuestionOption etPracticeQuestionOption : etPracticeQuestionOptionMapper.selectQuestionOptionListByQuestionInfoId(id)) {
            etPracticeQuestionOptionMapper.deleteEtPracticeQuestionOptionById(etPracticeQuestionOption.getId());
        }
        return etPracticeQuestionInfoMapper.deleteEtPracticeQuestionInfoById(id);
    }

    @Override
    public Map<String, String> getQuestionBankIdNameMap(List<String> questionBankIdList) {
        // 根据 question_bank_id 查询对应的 name
        List<Map<String, String>> idNameList = etPracticeQuestionBankMapper.getQuestionBankIdNameMap(questionBankIdList);

        // 构建映射关系
        Map<String, String> idNameMap = new HashMap<>();
        for (Map<String, String> idName : idNameList) {
            idNameMap.put(idName.get("id"), idName.get("name"));
        }

        return idNameMap;
    }
}