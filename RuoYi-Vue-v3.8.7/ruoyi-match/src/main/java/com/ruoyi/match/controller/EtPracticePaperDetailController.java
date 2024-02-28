package com.ruoyi.match.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.match.domain.EtPracticeQuestionInfo;
import com.ruoyi.match.mapper.EtPracticeQuestionInfoMapper;
import com.ruoyi.match.service.IEtPracticeQuestionInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.match.domain.EtPracticePaperDetail;
import com.ruoyi.match.service.IEtPracticePaperDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模拟试卷详情Controller
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/match/match_questions_paper_detail")
public class EtPracticePaperDetailController extends BaseController
{
    @Autowired
    private IEtPracticePaperDetailService etPracticePaperDetailService;
    @Autowired
    private IEtPracticeQuestionInfoService etPracticeQuestionInfoService;

    @Resource
    private EtPracticeQuestionInfoMapper etPracticeQuestionInfoMapper;

    /**
     * 查询模拟试卷详情列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(EtPracticePaperDetail etPracticePaperDetail)
    {
        startPage();
//        String id = etPracticePaperDetail.getQuestionId();
//
//
//        List<EtPracticeQuestionInfo> list =
//            etPracticeQuestionInfoService.selectEtPracticeQuestionInfoList(etPracticeQuestionInfoMapper.selectEtPracticeQuestionInfoById(id));
//
//        // 获取 EtPracticeQuestionInfo 的 question_bank_id 列表
//        List<String> questionBankIdList = list.stream()
//                .map(EtPracticeQuestionInfo::getQuestionBankId)
//                .collect(Collectors.toList());
//
//        // 通过 etPracticeQuestionInfoService 获取 HashMap 映射（question_bank_id 到 name 的映射）
//        Map<String, String> questionBankIdNameMap =
//                etPracticeQuestionInfoService.getQuestionBankIdNameMap(questionBankIdList);
//
//        // 将映射值覆盖到 EtPracticeQuestionInfo 对象中
//        list.forEach(info -> {
//            String questionBankName = questionBankIdNameMap.get(info.getQuestionBankId());
//            info.setQuestionBankId(questionBankName);
//        });

        List<EtPracticePaperDetail> list = etPracticePaperDetailService.selectEtPracticePaperDetailList(etPracticePaperDetail);

        return getDataTable(list);
    }

    /**
     * 导出模拟试卷详情列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:export')")
    @Log(title = "模拟试卷详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtPracticePaperDetail etPracticePaperDetail)
    {
        List<EtPracticePaperDetail> list = etPracticePaperDetailService.selectEtPracticePaperDetailList(etPracticePaperDetail);
        ExcelUtil<EtPracticePaperDetail> util = new ExcelUtil<EtPracticePaperDetail>(EtPracticePaperDetail.class);
        util.exportExcel(response, list, "模拟试卷详情数据");
    }

    /**
     * 获取模拟试卷详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:query')")
    @GetMapping(value = "/id/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(etPracticePaperDetailService.selectEtPracticePaperDetailById(id));
    }

    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:query')")
    @GetMapping(value = "/{paperid}")
    public AjaxResult getInfoByPaperId(@PathVariable("paperid") String paperid)
    {

        return success(etPracticePaperDetailService.selectEtPracticePaperDetailByPaperId(paperid));
    }

    /**
     * 新增模拟试卷详情
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:add')")
    @Log(title = "模拟试卷详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Map<String, Object> requestBody) {
        List<String> stringList = (List<String>) requestBody.get("list");
        String stringValue = (String) requestBody.get("paperid");

        // 创建符合参数类型的 Map
        Map<String, List<String>> requestMap = new HashMap<>();
        requestMap.put(stringValue,stringList);

        return toAjax(etPracticePaperDetailService.insertEtPracticePaperDetail(requestMap));
    }


    /**
     * 修改模拟试卷详情
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:edit')")
    @Log(title = "模拟试卷详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Map<String, Object> requestBody) {
        List<EtPracticePaperDetail> stringList = (List<EtPracticePaperDetail>) requestBody.get("list");
        String stringValue = (String) requestBody.get("paperid");

        // 创建符合参数类型的 Map
        Map<String, List<EtPracticePaperDetail>> requestMap = new HashMap<>();
        requestMap.put(stringValue,stringList);

        return toAjax(etPracticePaperDetailService.updateEtPracticePaperDetail(requestMap));
    }

    /**
     * 删除模拟试卷详情
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:remove')")
    @Log(title = "模拟试卷详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(etPracticePaperDetailService.deleteEtPracticePaperDetailByIds(ids));
    }


    /**
     * 删除模拟试卷详情
     */
//    @PreAuthorize("@ss.hasPermi('match:match_questions_paper_detail:remove')")
//    @Log(title = "模拟试卷详情", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{id}")
//    public AjaxResult remove(@PathVariable String id)
//    {
//        return toAjax(etPracticePaperDetailService.deleteEtPracticePaperDetailById(id));
//    }
}
