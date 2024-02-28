package com.ruoyi.match.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模拟试卷详情对象 et_practice_paper_detail
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public class EtPracticePaperDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 试卷id */
    private String paperId;

    /** 题号 */
    private Long questionNo;

    /** 试题id */
    private String questionId;

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

    public String getId() 
    {
        return id;
    }
    public void setPaperId(String paperId) 
    {
        this.paperId = paperId;
    }

    public String getPaperId() 
    {
        return paperId;
    }
    public void setQuestionNo(Long questionNo) 
    {
        this.questionNo = questionNo;
    }

    public Long getQuestionNo() 
    {
        return questionNo;
    }
    public void setQuestionId(String questionId) 
    {
        this.questionId = questionId;
    }

    public String getQuestionId() 
    {
        return questionId;
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
    public void setUpdateTime(Date updateTime)
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
            .append("paperId", getPaperId())
            .append("questionNo", getQuestionNo())
            .append("questionId", getQuestionId())
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
