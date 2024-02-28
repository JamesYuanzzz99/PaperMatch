package com.ruoyi.match.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模拟题库对象 et_practice_question_bank
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public class EtPracticeQuestionBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 所属课程 */
    @Excel(name = "所属课程")
    private String courseId;

    /** 状态，0启用1停用 */
    @Excel(name = "状态")
    private String status;

    /** 备用字段1 */
    @Excel(name = "备用字段1")
    private String field1;

    /** 备用字段2 */
    @Excel(name = "备用字段2")
    private String field2;

    /** 备用字段3 */
    @Excel(name = "备用字段3")
    private String field3;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注信息 */
    @Excel(name = "备注信息")
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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCourseId(String courseId) 
    {
        this.courseId = courseId;
    }

    public String getCourseId() 
    {
        return courseId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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

//    public Date getCreateTime()
//    {
//        return super.getCreateTime();
//    }

//    public Date getupdateTime()
//    {
//        return super.getUpdateTime();
//    }

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
            .append("name", getName())
            .append("courseId", getCourseId())
            .append("status", getStatus())
            .append("field1", getField1())
            .append("field2", getField2())
            .append("field3", getField3())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getCreateTime())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }


}
