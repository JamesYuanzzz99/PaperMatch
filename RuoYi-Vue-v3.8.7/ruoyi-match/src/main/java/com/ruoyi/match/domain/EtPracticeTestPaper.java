package com.ruoyi.match.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 编辑试卷对象 et_practice_test_paper
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public class EtPracticeTestPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 试卷名称 */
    @Excel(name = "试卷名称")
    private String name;

    /** 所属课程 */
    private String courseId;

    /** 试卷分数 */
    @Excel(name = "试卷分数")
    private Long paperScore;

    /** 试卷总题数 */
    @Excel(name = "试卷总题数")
    private Long paperSums;

    /** 题库id */
    private String questionBankId;

    /** 题库名称 */
    @Excel(name = "题库名称")
    private String questionBankName;

    /** 组卷方式，人工/随机 */
    @Excel(name = "组卷方式，人工/随机")
    private String groupWay;

    /** 考试规则id */
    private String examRulesId;

    /** 单选总数 */
    private Long selectSum;

    /** 单选分值 */
    private Long selectGrade;

    /** 单选总分 */
    private Long selectScores;

    /** 判断总数 */
    private Long judgeSum;

    /** 判断分值 */
    private Long judgeGrade;

    /** 判断总分 */
    private Long judgeScores;

    /** 多选总数 */
    private Long mulSelectSum;

    /** 多选分值 */
    private Long mulSelectGrade;

    /** 多选总分 */
    private Long mulSelectScores;

    /** 填空总数 */
    private Long completionSum;

    /** 填空分值 */
    private Long completionGrade;

    /** 填空总分 */
    private Long completionScores;

    /** 解答总数 */
    private Long answerSum;

    /** 解答分值 */
    private Long answerGrade;

    /** 解答总分 */
    private Long answerScores;

    /** 论述总数 */
    private Long discussSum;

    /** 论述分值 */
    private Long discussGrade;

    /** 论述总分 */
    private Long discussScores;

    /** 不定项选择题总数 */
    private Long uncertaintiesSum;

    /** 不定项选择题分值 */
    private Long uncertaintiesGrade;

    /** 不定项选择题总分 */
    private Long uncertaintiesScores;

    /** 状态 */
    private String status;

    /** 备用字段1 */
    private String field1;

    /** 备用字段2 */
    private String field2;

    /** 备用字段3 */
    private String field3;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新时间 */
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
    public void setPaperScore(Long paperScore) 
    {
        this.paperScore = paperScore;
    }

    public Long getPaperScore() 
    {
        return paperScore;
    }
    public void setPaperSums(Long paperSums) 
    {
        this.paperSums = paperSums;
    }

    public Long getPaperSums() 
    {
        return paperSums;
    }
    public void setQuestionBankId(String questionBankId) 
    {
        this.questionBankId = questionBankId;
    }

    public String getQuestionBankId() 
    {
        return questionBankId;
    }
    public void setQuestionBankName(String questionBankName) 
    {
        this.questionBankName = questionBankName;
    }

    public String getQuestionBankName() 
    {
        return questionBankName;
    }
    public void setGroupWay(String groupWay) 
    {
        this.groupWay = groupWay;
    }

    public String getGroupWay() 
    {
        return groupWay;
    }
    public void setExamRulesId(String examRulesId) 
    {
        this.examRulesId = examRulesId;
    }

    public String getExamRulesId() 
    {
        return examRulesId;
    }
    public void setSelectSum(Long selectSum) 
    {
        this.selectSum = selectSum;
    }

    public Long getSelectSum() 
    {
        return selectSum;
    }
    public void setSelectGrade(Long selectGrade) 
    {
        this.selectGrade = selectGrade;
    }

    public Long getSelectGrade() 
    {
        return selectGrade;
    }
    public void setSelectScores(Long selectScores) 
    {
        this.selectScores = selectScores;
    }

    public Long getSelectScores() 
    {
        return selectScores;
    }
    public void setJudgeSum(Long judgeSum) 
    {
        this.judgeSum = judgeSum;
    }

    public Long getJudgeSum() 
    {
        return judgeSum;
    }
    public void setJudgeGrade(Long judgeGrade) 
    {
        this.judgeGrade = judgeGrade;
    }

    public Long getJudgeGrade() 
    {
        return judgeGrade;
    }
    public void setJudgeScores(Long judgeScores) 
    {
        this.judgeScores = judgeScores;
    }

    public Long getJudgeScores() 
    {
        return judgeScores;
    }
    public void setMulSelectSum(Long mulSelectSum) 
    {
        this.mulSelectSum = mulSelectSum;
    }

    public Long getMulSelectSum() 
    {
        return mulSelectSum;
    }
    public void setMulSelectGrade(Long mulSelectGrade) 
    {
        this.mulSelectGrade = mulSelectGrade;
    }

    public Long getMulSelectGrade() 
    {
        return mulSelectGrade;
    }
    public void setMulSelectScores(Long mulSelectScores) 
    {
        this.mulSelectScores = mulSelectScores;
    }

    public Long getMulSelectScores() 
    {
        return mulSelectScores;
    }
    public void setCompletionSum(Long completionSum) 
    {
        this.completionSum = completionSum;
    }

    public Long getCompletionSum() 
    {
        return completionSum;
    }
    public void setCompletionGrade(Long completionGrade) 
    {
        this.completionGrade = completionGrade;
    }

    public Long getCompletionGrade() 
    {
        return completionGrade;
    }
    public void setCompletionScores(Long completionScores) 
    {
        this.completionScores = completionScores;
    }

    public Long getCompletionScores() 
    {
        return completionScores;
    }
    public void setAnswerSum(Long answerSum) 
    {
        this.answerSum = answerSum;
    }

    public Long getAnswerSum() 
    {
        return answerSum;
    }
    public void setAnswerGrade(Long answerGrade) 
    {
        this.answerGrade = answerGrade;
    }

    public Long getAnswerGrade() 
    {
        return answerGrade;
    }
    public void setAnswerScores(Long answerScores) 
    {
        this.answerScores = answerScores;
    }

    public Long getAnswerScores() 
    {
        return answerScores;
    }
    public void setDiscussSum(Long discussSum) 
    {
        this.discussSum = discussSum;
    }

    public Long getDiscussSum() 
    {
        return discussSum;
    }
    public void setDiscussGrade(Long discussGrade) 
    {
        this.discussGrade = discussGrade;
    }

    public Long getDiscussGrade() 
    {
        return discussGrade;
    }
    public void setDiscussScores(Long discussScores) 
    {
        this.discussScores = discussScores;
    }

    public Long getDiscussScores() 
    {
        return discussScores;
    }
    public void setUncertaintiesSum(Long uncertaintiesSum) 
    {
        this.uncertaintiesSum = uncertaintiesSum;
    }

    public Long getUncertaintiesSum() 
    {
        return uncertaintiesSum;
    }
    public void setUncertaintiesGrade(Long uncertaintiesGrade) 
    {
        this.uncertaintiesGrade = uncertaintiesGrade;
    }

    public Long getUncertaintiesGrade() 
    {
        return uncertaintiesGrade;
    }
    public void setUncertaintiesScores(Long uncertaintiesScores) 
    {
        this.uncertaintiesScores = uncertaintiesScores;
    }

    public Long getUncertaintiesScores() 
    {
        return uncertaintiesScores;
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
            .append("name", getName())
            .append("courseId", getCourseId())
            .append("paperScore", getPaperScore())
            .append("paperSums", getPaperSums())
            .append("questionBankId", getQuestionBankId())
            .append("questionBankName", getQuestionBankName())
            .append("groupWay", getGroupWay())
            .append("examRulesId", getExamRulesId())
            .append("selectSum", getSelectSum())
            .append("selectGrade", getSelectGrade())
            .append("selectScores", getSelectScores())
            .append("judgeSum", getJudgeSum())
            .append("judgeGrade", getJudgeGrade())
            .append("judgeScores", getJudgeScores())
            .append("mulSelectSum", getMulSelectSum())
            .append("mulSelectGrade", getMulSelectGrade())
            .append("mulSelectScores", getMulSelectScores())
            .append("completionSum", getCompletionSum())
            .append("completionGrade", getCompletionGrade())
            .append("completionScores", getCompletionScores())
            .append("answerSum", getAnswerSum())
            .append("answerGrade", getAnswerGrade())
            .append("answerScores", getAnswerScores())
            .append("discussSum", getDiscussSum())
            .append("discussGrade", getDiscussGrade())
            .append("discussScores", getDiscussScores())
            .append("uncertaintiesSum", getUncertaintiesSum())
            .append("uncertaintiesGrade", getUncertaintiesGrade())
            .append("uncertaintiesScores", getUncertaintiesScores())
            .append("status", getStatus())
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
