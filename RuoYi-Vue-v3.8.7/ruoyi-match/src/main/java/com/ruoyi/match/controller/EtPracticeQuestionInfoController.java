package com.ruoyi.match.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.match.service.IEtPracticeQuestionBankService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.match.domain.EtPracticeQuestionInfo;
import com.ruoyi.match.service.IEtPracticeQuestionInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 模拟试题Controller
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@RestController
@RequestMapping("/match/match_questions_info")
public class EtPracticeQuestionInfoController extends BaseController
{
    @Resource
    private IEtPracticeQuestionInfoService etPracticeQuestionInfoService;

    /**
     * 删除模拟试题答案附件
     */
    @Log(title = "模拟试题附件", businessType = BusinessType.DELETE)
    @GetMapping("/deleteFile")
    public AjaxResult removeAnswerFile(@RequestParam String filePath) {
        return toAjax(etPracticeQuestionInfoService.deleteEtPracticeQuestionInfoAnswerFile(filePath));
    }


    /**
     * 导出模拟试题列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_info:export')")
    @Log(title = "模拟试题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtPracticeQuestionInfo etPracticeQuestionInfo)
    {
        List<EtPracticeQuestionInfo> list = etPracticeQuestionInfoService.selectEtPracticeQuestionInfoList(etPracticeQuestionInfo);
        ExcelUtil<EtPracticeQuestionInfo> util = new ExcelUtil<EtPracticeQuestionInfo>(EtPracticeQuestionInfo.class);
        util.exportExcel(response, list, "模拟试题数据");
    }

    /**
     * 获取模拟试题详细信息
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {

        return success(etPracticeQuestionInfoService.selectEtPracticeQuestionInfoById(id));
    }

    /**
     * 新增模拟试题
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_info:add')")
    @Log(title = "模拟试题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EtPracticeQuestionInfo etPracticeQuestionInfo)
    {


        return toAjax(etPracticeQuestionInfoService.insertEtPracticeQuestionInfo(etPracticeQuestionInfo));
    }

    /**
     * 修改模拟试题
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_info:edit')")
    @Log(title = "模拟试题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EtPracticeQuestionInfo etPracticeQuestionInfo)
    {
        return toAjax(etPracticeQuestionInfoService.updateEtPracticeQuestionInfo(etPracticeQuestionInfo));
    }

    /**
     * 删除模拟试题
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_info:remove')")
    @Log(title = "模拟试题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(etPracticeQuestionInfoService.deleteEtPracticeQuestionInfoByIds(ids));
    }




    /**
     * 上传模拟试题文件
     */
//    @PreAuthorize("@ss.hasPermi('match:match_questions_info:answer')")
    @Log(title = "模拟试题附件上传")
    @PostMapping("/answer")
    public AjaxResult answer(@RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            String answer = FileUploadUtils.upload(RuoYiConfig.getAnswerPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("field1", answer);
            return ajax;
        }
        return error("上传附件异常");
    }
    /**
     * 查询模拟试题列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_info:list')")
    @GetMapping("/list")
    public TableDataInfo list(EtPracticeQuestionInfo etPracticeQuestionInfo) {
        startPage();
        List<EtPracticeQuestionInfo> list =
                etPracticeQuestionInfoService.selectEtPracticeQuestionInfoList(etPracticeQuestionInfo);

        // 获取 EtPracticeQuestionInfo 的 question_bank_id 列表
        List<String> questionBankIdList = list.stream()
                .map(EtPracticeQuestionInfo::getQuestionBankId)
                .collect(Collectors.toList());

        // 通过 etPracticeQuestionInfoService 获取 HashMap 映射（question_bank_id 到 name 的映射）
        Map<String, String> questionBankIdNameMap =
                etPracticeQuestionInfoService.getQuestionBankIdNameMap(questionBankIdList);

        // 将映射值覆盖到 EtPracticeQuestionInfo 对象中
        list.forEach(info -> {
            String questionBankName = questionBankIdNameMap.get(info.getQuestionBankId());
            info.setQuestionBankId(questionBankName);
        });

        return getDataTable(list);
    }

}
