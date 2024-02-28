package com.ruoyi.match.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import com.ruoyi.match.domain.EtPracticeQuestionBank;
import com.ruoyi.match.service.IEtPracticeQuestionBankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模拟题库Controller
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@RestController
@RequestMapping("/match/match_questions_bank")
public class EtPracticeQuestionBankController extends BaseController
{
    @Autowired
    private IEtPracticeQuestionBankService etPracticeQuestionBankService;

    /**
     * 查询模拟题库列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:list')")
    @GetMapping("/list")
    public TableDataInfo list(EtPracticeQuestionBank etPracticeQuestionBank) {
        startPage();
        List<EtPracticeQuestionBank> list =
                etPracticeQuestionBankService.selectEtPracticeQuestionBankList(etPracticeQuestionBank);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:list2')")
    @GetMapping("/list2")
    public List<EtPracticeQuestionBank> list2(EtPracticeQuestionBank etPracticeQuestionBank) {
        return etPracticeQuestionBankService.selectEtPracticeQuestionBankList2(etPracticeQuestionBank);
    }
    /**
     * 导出模拟题库列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:export')")
    @Log(title = "模拟题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtPracticeQuestionBank etPracticeQuestionBank)
    {
        List<EtPracticeQuestionBank> list = etPracticeQuestionBankService.selectEtPracticeQuestionBankList(etPracticeQuestionBank);
        ExcelUtil<EtPracticeQuestionBank> util = new ExcelUtil<EtPracticeQuestionBank>(EtPracticeQuestionBank.class);
        util.exportExcel(response, list, "模拟题库数据");
    }

    /**
     * 获取模拟题库详细信息
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(etPracticeQuestionBankService.selectEtPracticeQuestionBankById(id));
    }

    /**
     * 新增模拟题库
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:add')")
    @Log(title = "模拟题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EtPracticeQuestionBank etPracticeQuestionBank)
    {
        return toAjax(etPracticeQuestionBankService.insertEtPracticeQuestionBank(etPracticeQuestionBank));
    }

    /**
     * 修改模拟题库
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:edit')")
    @Log(title = "模拟题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EtPracticeQuestionBank etPracticeQuestionBank)
    {
        return toAjax(etPracticeQuestionBankService.updateEtPracticeQuestionBank(etPracticeQuestionBank));
    }

    /**
     * 删除模拟题库
     */
    @PreAuthorize("@ss.hasPermi('match:match_questions_bank:remove')")
    @Log(title = "模拟题库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(etPracticeQuestionBankService.deleteEtPracticeQuestionBankByIds(ids));
    }
}
