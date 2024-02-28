package com.ruoyi.match.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模拟试题对象 et_practice_question_info
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public class EtPracticeQuestionInfo extends BaseEntity
{


    private List<EtPracticeQuestionOption> options = Lists.newArrayList();
    public List<EtPracticeQuestionOption> getoptions() {
        return options;
    }

    public void setoptions(List<EtPracticeQuestionOption> options) {
        this.options = options;
    }
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 编号 */
    @Excel(name = "编号")
    private Long code;

    /** 所属题库 */
    @Excel(name = "所属题库")
    private String questionBankId;

    /** 题干内容 */
    @Excel(name = "题干内容")
    private String content;

    /** 题型 */
    @Excel(name = "题型")
    private String typeId;

    /** 难易程度 */
    @Excel(name = "难易程度")
    private String complexity;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String answer;

    /** 判断题答案 */
    @Excel(name = "判断题答案")
    private String judgeAnswer;

    /** 填空题答案 */
    @Excel(name = "填空题答案")
    private String completionAnswer;

    /** 详细解释 */
    @Excel(name = "详细解释")
    private String answerExplain;

    /** 试题图片路径 */
    @Excel(name = "试题图片路径")
    private String picPath;

    /** 试题图片id */
    @Excel(name = "试题图片id")
    private String picId;

    /** 状态，0启用1停用 */
    @Excel(name = "状态，0启用1停用")
    private String status;

    /** 备用字段1 */
    @Excel(name = "备用字段1")
    @TableField(updateStrategy =  FieldStrategy.IGNORED)
    private String field1;

    /** 备用字段2 */
    @Excel(name = "备用字段2")
    private String field2;

    /** 备用字段3 */
    @Excel(name = "备用字段3")
    private String field3;

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
    public void setCode(Long code) 
    {
        this.code = code;
    }

    public Long getCode() 
    {
        return code;
    }
    public void setQuestionBankId(String questionBankId) 
    {
        this.questionBankId = questionBankId;
    }

    public String getQuestionBankId() 
    {
        return questionBankId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTypeId(String typeId) 
    {
        this.typeId = typeId;
    }

    public String getTypeId() 
    {
        return typeId;
    }
    public void setComplexity(String complexity) 
    {
        this.complexity = complexity;
    }

    public String getComplexity() 
    {
        return complexity;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnswer()
    {
        return answer;
    }
    public void setJudgeAnswer(String judgeAnswer) 
    {
        this.judgeAnswer = judgeAnswer;
    }

    public String getJudgeAnswer() 
    {
        return judgeAnswer;
    }
    public void setCompletionAnswer(String completionAnswer) 
    {
        this.completionAnswer = completionAnswer;
    }

    public String getCompletionAnswer() 
    {
        return completionAnswer;
    }
    public void setAnswerExplain(String answerExplain) 
    {
        this.answerExplain = answerExplain;
    }

    public String getAnswerExplain() 
    {
        return answerExplain;
    }
    public void setPicPath(String picPath) 
    {
        this.picPath = picPath;
    }

    public String getPicPath() 
    {
        return picPath;
    }
    public void setPicId(String picId) 
    {
        this.picId = picId;
    }

    public String getPicId() 
    {
        return picId;
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
            .append("code", getCode())
            .append("questionBankId", getQuestionBankId())
            .append("content", getContent())
            .append("typeId", getTypeId())
            .append("complexity", getComplexity())
            .append("answer", getAnswer())
            .append("judgeAnswer", getJudgeAnswer())
            .append("completionAnswer", getCompletionAnswer())
            .append("answerExplain", getAnswerExplain())
            .append("picPath", getPicPath())
            .append("picId", getPicId())
            .append("status", getStatus())
            .append("field1", getField1())
            .append("field2", getField2())
            .append("field3", getField3())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }

    public void setQuestionBankName(String originalName) {
    }
}
