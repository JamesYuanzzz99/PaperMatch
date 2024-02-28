package com.ruoyi.match.service;

import java.util.List;
import com.ruoyi.match.domain.EtQuestionType;

/**
 * 试题类型Service接口
 * 
 * @author ruoyi
 * @date 2024-01-18
 */
public interface IEtQuestionTypeService 
{
    /**
     * 查询试题类型
     * 
     * @param id 试题类型主键
     * @return 试题类型
     */
    public EtQuestionType selectEtQuestionTypeById(Long id);

    /**
     * 查询试题类型列表
     * 
     * @param etQuestionType 试题类型
     * @return 试题类型集合
     */
    public List<EtQuestionType> selectEtQuestionTypeList(EtQuestionType etQuestionType);

    /**
     * 新增试题类型
     * 
     * @param etQuestionType 试题类型
     * @return 结果
     */
    public int insertEtQuestionType(EtQuestionType etQuestionType);

    /**
     * 修改试题类型
     * 
     * @param etQuestionType 试题类型
     * @return 结果
     */
    public int updateEtQuestionType(EtQuestionType etQuestionType);

    /**
     * 批量删除试题类型
     * 
     * @param ids 需要删除的试题类型主键集合
     * @return 结果
     */
    public int deleteEtQuestionTypeByIds(Long[] ids);

    /**
     * 删除试题类型信息
     * 
     * @param id 试题类型主键
     * @return 结果
     */
    public int deleteEtQuestionTypeById(Long id);
}
