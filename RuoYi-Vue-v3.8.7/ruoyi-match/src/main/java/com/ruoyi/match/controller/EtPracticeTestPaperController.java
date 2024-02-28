package com.ruoyi.match.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.match.domain.EtPracticePaperDetail;
import com.ruoyi.match.service.IEtPracticePaperDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.match.domain.EtPracticeTestPaper;
import com.ruoyi.match.service.IEtPracticeTestPaperService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 编辑试卷Controller
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/match/match_question_paper")
public class EtPracticeTestPaperController extends BaseController
{
    @Autowired
    private IEtPracticeTestPaperService etPracticeTestPaperService;

    /**
     * 查询编辑试卷列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_paper:list')")
    @GetMapping("/list")
    public TableDataInfo list(EtPracticeTestPaper etPracticeTestPaper)
    {
        startPage();

        List<EtPracticeTestPaper> list = etPracticeTestPaperService.selectEtPracticeTestPaperList(etPracticeTestPaper);

        return getDataTable(list);
    }

    /**
     * 导出编辑试卷列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_paper:export')")
    @Log(title = "编辑试卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtPracticeTestPaper etPracticeTestPaper)
    {
        List<EtPracticeTestPaper> list = etPracticeTestPaperService.selectEtPracticeTestPaperList(etPracticeTestPaper);
        ExcelUtil<EtPracticeTestPaper> util = new ExcelUtil<EtPracticeTestPaper>(EtPracticeTestPaper.class);
        util.exportExcel(response, list, "编辑试卷数据");
    }

    /**
     * 获取编辑试卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_paper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {

        return success(etPracticeTestPaperService.selectEtPracticeTestPaperById(id));
    }

    /**
     * 新增编辑试卷
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_paper:add')")
    @Log(title = "编辑试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EtPracticeTestPaper etPracticeTestPaper) {
        try {
            // 插入操作，注意数据库生成的id会被设置到传入的对象中
            int rows = etPracticeTestPaperService.insertEtPracticeTestPaper(etPracticeTestPaper);
            if (rows > 0) {
                // 返回成功消息和表单的id
                return AjaxResult.success("创建试卷成功", etPracticeTestPaper.getId());
            } else {
                return AjaxResult.error("创建试卷失败");
            }
        } catch (Exception e) {
            // 异常处理，返回错误信息
            return AjaxResult.error("创建试卷失败，发生异常");
        }
    }

    /**
     * 修改编辑试卷
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_paper:edit')")
    @Log(title = "编辑试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EtPracticeTestPaper etPracticeTestPaper)
    {
        return toAjax(etPracticeTestPaperService.updateEtPracticeTestPaper(etPracticeTestPaper));
    }

    /**
     * 删除编辑试卷
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_paper:remove')")
    @Log(title = "编辑试卷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(etPracticeTestPaperService.deleteEtPracticeTestPaperByIds(ids));
    }


}
