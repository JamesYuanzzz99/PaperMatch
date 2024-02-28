package com.ruoyi.match.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.annotation.Resource;

/**
 * 试题选项对象 et_practice_question_option
 * 
 * @author yhx
 * @date 2024-01-22
 */
public class EtPracticeQuestionOption extends BaseEntity
{
@Resource
    private EtPracticeQuestionInfo question;
    private static final long serialVersionUID = 1L;


    public EtPracticeQuestionOption() {
        // 无参数构造函数
    }
    /** 主键 */
    private String id;

    /** 所属试题 */
    private String questionId;

    /** 选项 */
    private String optionNum;

    /** 内容 */
    private String content;

    /** 顺序 */
    private Long sort;

    /** 备用字段1 */
    private String field1;

    /** 备用字段2 */
    private String field2;

    /** 备用字段3 */
    private String field3;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 备注信息 */
    private String remarks;

    /** 删除标记 */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }
    public EtPracticeQuestionOption(EtPracticeQuestionInfo question){
        this.question = question;
    }
    public String getId() 
    {
        return id;
    }
    public void setQuestionId(String questionId) 
    {
        this.questionId = questionId;
    }

    public String getQuestionId() 
    {
        return questionId;
    }
    public void setOptionNum(String optionNum) 
    {
        this.optionNum = optionNum;
    }

    public String getOptionNum() 
    {
        return optionNum;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setField1(String field1) 
    {
        this.field1 = field1;
    }

    public String getField1() 
    {
        return field1;
    }
    public void setField2(String field2) 
    {
        this.field2 = field2;
    }

    public String getField2() 
    {
        return field2;
    }
    public void setField3(String field3) 
    {
        this.field3 = field3;
    }

    public String getField3() 
    {
        return field3;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateDate(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionId", getQuestionId())
            .append("optionNum", getOptionNum())
            .append("content", getContent())
            .append("sort", getSort())
            .append("field1", getField1())
            .append("field2", getField2())
            .append("field3", getField3())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
