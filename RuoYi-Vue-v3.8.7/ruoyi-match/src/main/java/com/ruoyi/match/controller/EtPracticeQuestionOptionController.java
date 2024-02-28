package com.ruoyi.match.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.match.domain.EtPracticeQuestionOption;
import com.ruoyi.match.service.IEtPracticeQuestionOptionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 试题选项Controller
 * 
 * @author yhx
 * @date 2024-01-22
 */
@RestController
@RequestMapping("/match/match_question_option")
public class EtPracticeQuestionOptionController extends BaseController
{
    @Autowired
    private IEtPracticeQuestionOptionService etPracticeQuestionOptionService;


    /**
     * 查询试题选项列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_option:list')")
    @GetMapping("/list")
    public TableDataInfo list(EtPracticeQuestionOption etPracticeQuestionOption)
    {
        startPage();
        List<EtPracticeQuestionOption> list = etPracticeQuestionOptionService.selectEtoptions(etPracticeQuestionOption);
        return getDataTable(list);
    }

    /**
     * 导出试题选项列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_option:export')")
    @Log(title = "试题选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtPracticeQuestionOption etPracticeQuestionOption)
    {
        List<EtPracticeQuestionOption> list = etPracticeQuestionOptionService.selectEtoptions(etPracticeQuestionOption);
        ExcelUtil<EtPracticeQuestionOption> util = new ExcelUtil<EtPracticeQuestionOption>(EtPracticeQuestionOption.class);
        util.exportExcel(response, list, "试题选项数据");
    }

    /**
     * 获取试题选项详细信息
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_option:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(etPracticeQuestionOptionService.selectEtPracticeQuestionOptionById(id));
    }

    /**
     * 新增试题选项
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_option:add')")
    @Log(title = "试题选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EtPracticeQuestionOption etPracticeQuestionOption)
    {
        return toAjax(etPracticeQuestionOptionService.insertEtPracticeQuestionOption(etPracticeQuestionOption));
    }

    /**
     * 修改试题选项
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_option:edit')")
    @Log(title = "试题选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EtPracticeQuestionOption etPracticeQuestionOption)
    {
        return toAjax(etPracticeQuestionOptionService.updateEtPracticeQuestionOption(etPracticeQuestionOption));
    }

    /**
     * 删除试题选项
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_option:remove')")
    @Log(title = "试题选项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(etPracticeQuestionOptionService.deleteEtPracticeQuestionOptionByIds(ids));
    }
}
