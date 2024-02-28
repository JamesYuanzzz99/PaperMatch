package com.ruoyi.match.controller;

import java.util.Date;
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
import com.ruoyi.match.domain.EtQuestionType;
import com.ruoyi.match.service.IEtQuestionTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 试题类型Controller
 * 
 * @author ruoyi
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/match/match_question_type")
public class EtQuestionTypeController extends BaseController
{
    @Autowired
    private IEtQuestionTypeService etQuestionTypeService;

    /**
     * 查询试题类型列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_type:list')")
    @GetMapping("/list")
    public TableDataInfo list(EtQuestionType etQuestionType)
    {
        startPage();
        List<EtQuestionType> list = etQuestionTypeService.selectEtQuestionTypeList(etQuestionType);
        return getDataTable(list);
    }

    /**
     * 导出试题类型列表
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_type:export')")
    @Log(title = "试题类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtQuestionType etQuestionType)
    {
        List<EtQuestionType> list = etQuestionTypeService.selectEtQuestionTypeList(etQuestionType);
        ExcelUtil<EtQuestionType> util = new ExcelUtil<EtQuestionType>(EtQuestionType.class);
        util.exportExcel(response, list, "试题类型数据");
    }

    /**
     * 获取试题类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(etQuestionTypeService.selectEtQuestionTypeById(id));
    }

    /**
     * 新增试题类型
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_type:add')")
    @Log(title = "试题类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EtQuestionType etQuestionType)
    {
        etQuestionType.setUpdateTime(new Date());
        return toAjax(etQuestionTypeService.insertEtQuestionType(etQuestionType));
    }

    /**
     * 修改试题类型
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_type:edit')")
    @Log(title = "试题类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EtQuestionType etQuestionType)
    {
        return toAjax(etQuestionTypeService.updateEtQuestionType(etQuestionType));
    }

    /**
     * 删除试题类型
     */
    @PreAuthorize("@ss.hasPermi('match:match_question_type:remove')")
    @Log(title = "试题类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(etQuestionTypeService.deleteEtQuestionTypeByIds(ids));
    }
}
