<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="试卷名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入试卷名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试卷分数" prop="paperScore">
        <el-input
          v-model="queryParams.paperScore"
          placeholder="请输入试卷分数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['match:match_question_paper:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['match:match_question_paper:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['match:match_question_paper:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:match_question_paper:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>



    <el-table v-loading="loading" :data="match_question_paperList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="试卷名称" align="center" prop="name" />
      <el-table-column label="试卷分数" align="center" prop="paperScore" />
      <el-table-column label="试卷总题数" align="center" prop="paperSums" />
      <el-table-column label="题库名称" align="center" prop="questionBankName" />
      <el-table-column label="组卷方式" align="center" prop="groupWay">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.groupWay === '1'" type="success">人工</el-tag>
          <el-tag v-else>随机</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注信息" align="center" prop="remarks" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:match_question_paper:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:match_question_paper:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>




    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改编辑试卷对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>


<div style="display: flex;justify-content: space-between">
  <div >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">

      <el-form-item label="试卷名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入试卷名称" style="width: 200px;"/>
      </el-form-item>

      <el-form-item label="题库" prop="questionBankId">
        <el-select v-model="form.questionBankId" placeholder="请选择题库" style="width: 200px;">
          <el-option
            v-for="questionBank in questionBanks"
            :key="questionBank.id"
            :label="questionBank.name"
            :value="questionBank.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="组卷方式" prop="groupWay">
        <el-radio-group v-model="form.groupWay">
          <el-radio v-for="option in options_List2" :key="option.optionNum" :label="option.optionNum" >
            {{ option.optionNum }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="试卷分数" prop="paperScore">
        <el-input :value="form.paperScore" disabled style="width: 200px;" />
      </el-form-item>
      <el-form-item label="总题数" prop="paperSums">
        <el-input :value="form.paperSums" disabled style="width: 200px;" />
      </el-form-item>
      <el-form-item label="单选分值" prop="selectGrade">
        <el-input v-model="form.selectGrade" placeholder="请输入单选分值"style="width: 200px;" />
      </el-form-item>
      <el-form-item label="判断分值" prop="judgeGrade">
        <el-input v-model="form.judgeGrade" placeholder="请输入判断分值" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="多选分值" prop="mulSelectGrade">
        <el-input v-model="form.mulSelectGrade" placeholder="请输入多选分值"style="width: 200px;" />
      </el-form-item>
      <el-form-item label="填空分值" prop="completionGrade">
        <el-input v-model="form.completionGrade" placeholder="请输入填空分值"style="width: 200px;" />
      </el-form-item>
      <el-form-item label="解答分值" prop="answerGrade">
        <el-input v-model="form.answerGrade" placeholder="请输入解答分值" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="论述分值" prop="discussGrade">
        <el-input v-model="form.discussGrade" placeholder="请输入论述分值" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="不定项选择题分值" prop="uncertaintiesGrade">
        <el-input v-model="form.uncertaintiesGrade" placeholder="请输入不定项选择题分值" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="备注信息" prop="remarks">
        <el-input v-model="form.remarks" placeholder="请输入备注信息" style="width: 200px;"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>

  <div style="border: 1px solid #d4d4d4;padding: 10px">

    <el-button
      type="primary"
      plain
      icon="el-icon-plus"
      size="big"
      @click="showFormDialog"
      v-hasPermi="['match:match_questions_infoList:list']"
    >添加试题</el-button>

    <el-table v-loading="loading" :data="match_questions_paper_detailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="题库" align="center" prop="questionBankId" />
      <el-table-column label="题干" align="center" prop="content"width="500"/>
      <el-table-column label="题型" align="center" prop="typeId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.question_type" :value="scope.row.typeId"/>
        </template>
      </el-table-column>
      <el-table-column label="难度" align="center" prop="complexity">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.complexity" :value="scope.row.complexity"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete2(scope.row)"
            v-hasPermi="['match:match_question_paper:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</div>
    </el-dialog>


    <el-dialog :title="title" :visible.sync="openTwo" width="1500px" append-to-body>
      <div>

        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="题型" prop="typeId">
            <el-select v-model="queryParams.typeId" placeholder="请选择题型" clearable>
              <el-option
                v-for="dict in dict.type.question_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="难度" prop="complexity">
            <el-select v-model="queryParams.complexity" placeholder="请选择难易程度" clearable>
              <el-option
                v-for="dict in dict.type.complexity"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
              <el-option
                v-for="dict in dict.type.sys_normal_disable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="时间" prop="createTime">
            <el-date-picker clearable
                            v-model="queryParams.createTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择创建时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery2">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            <el-button type="primary" size="medium" @click="submitForm2">确 定</el-button>
            <el-button size="medium" @click="cancel2">取 消</el-button>
          </el-form-item>
        </el-form>


        <el-table v-loading="loading" :data="match_questions_infoList" @selection-change="handleSelectionChange2">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="所属题库" align="center" prop="questionBankId" />
          <el-table-column label="题干内容" align="center" prop="content"width="500"/>
          <el-table-column label="题型" align="center" prop="typeId">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.question_type" :value="scope.row.typeId"/>
            </template>
          </el-table-column>
          <el-table-column label="难度" align="center" prop="complexity">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.complexity" :value="scope.row.complexity"/>
            </template>
          </el-table-column>
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
        </el-table>

      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMatch_question_paper, getMatch_question_paper, delMatch_question_paper, addMatch_question_paper, updateMatch_question_paper } from "@/api/match/match_question_paper";
import {getMatch_questions_bank, listMatch_questions_bank2} from "@/api/match/match_questions_bank";
import {
  addMatch_questions_info,
  getMatch_questions_info,
  listMatch_questions_info
} from "@/api/match/match_questions_info";
import {
  addMatch_questions_paper_detail, delMatch_questions_paper_detail,
  getMatch_questions_paper_detail, getMatch_questions_paper_detail_by_paperid,
  listMatch_questions_paper_detail, updateMatch_questions_paper_detail
} from "@/api/match/match_questions_paper_detail";

export default {
  name: "Match_question_paper",
  dicts: ['question_type', 'complexity', 'sys_normal_disable'],
  data() {
    return {
      openTwo:false,
      options_List2:[
        { optionNum:'人工', content:'', sort: 1},
        { optionNum:'随机', content:'', sort: 2},
      ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      questionids:[],
      questionBanks: [] ,
      match_questions_infoList: [],
      match_paper_detail_List: [],
      match_paper_detail_questions_List: [],
      match_questions_paper_detailList:[],
      dictTypeOptions: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 编辑试卷表格数据
      match_question_paperList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        name: null,
        paperScore: null,
        // questionBankName: null,
      },
      // 表单参数
      form: {},
      questionform:{},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    // this.getquestionList();
    this.getQuestionBanks();
    // this.getDetailList();
  },
  methods: {


    computedPaperScore() {
      // 计算试卷分数，相加其他分值项
      return 0;
    },
    computedPaperquestionNum() {
      // 计算试卷试题总数
      return this.match_questions_paper_detailList.length;
    },

    showFormDialog(row) {
      this.getquestionList();
      this.openTwo = true;
    },
    getquestionList() {
      this.loading = true;
      listMatch_questions_info(this.queryParams).then(response => {
        this.match_questions_infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getDetailList() {
      this.loading = true;
      if (this.form.id != null) {
        getMatch_questions_paper_detail_by_paperid(this.form.id)
          .then(response => {
            // 假设 response.data 是一个数组
            if (response)
            {
              const paperDetailList = response.data;
              // 从 paperDetailList 中提取 id
              const idArray = paperDetailList.map(item => item.id);
              // 将 id 映射为 getMatch_questions_info 的 promises
              const promises = idArray.map(id => getMatch_questions_info(id));
              // 使用 Promise.all 等待所有 promises 完成
              return Promise.all(promises);
            }
          })
          .then(responses => {
            // 假设 responses 是一个响应数组
            this.match_questions_paper_detailList = responses.map(response => response.data);
            // 这一行在您的代码中被注释了，解注释以确保它的执行（如果有需要）
            // this.title = "修改编辑试卷";
          })
          .catch(error => {
            // 处理任何错误
            console.error("获取数据时出错：", error);
          })
          .finally(() => {
            // 确保 loading 指示器在请求成功或遇到错误时关闭
            this.loading = false;
          });
      } else {
        // 处理 this.form.id 为 null 的情况
      }
    },


    getQuestionBanks() {
      listMatch_questions_bank2().then(response => {
        this.questionBanks = response;
      });
    },
     getQuestionBank(id) {
      getMatch_questions_bank(id).then(response => {
        console.log(response)
        return response.date.name;
      });
    },
    /** 查询编辑试卷列表 */
    getList() {
      this.loading = true;
      listMatch_question_paper(this.queryParams).then(response => {
        this.match_question_paperList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancel2() {
      this.openTwo = false;
      // this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        courseId: null,
        paperScore: null,
        paperSums: null,
        questionBankId: null,
        questionBankName: null,
        groupWay: null,
        examRulesId: null,
        selectSum: null,
        selectGrade: null,
        selectScores: null,
        judgeSum: null,
        judgeGrade: null,
        judgeScores: null,
        mulSelectSum: null,
        mulSelectGrade: null,
        mulSelectScores: null,
        completionSum: null,
        completionGrade: null,
        completionScores: null,
        answerSum: null,
        answerGrade: null,
        answerScores: null,
        discussSum: null,
        discussGrade: null,
        discussScores: null,
        uncertaintiesSum: null,
        uncertaintiesGrade: null,
        uncertaintiesScores: null,
        status: null,
        field1: null,
        field2: null,
        field3: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remarks: null,
        delFlag: null
      };

      this.questionform = {
        id: null,
        code: null,
        questionBankId: null,
        content: null,
        typeId: null,
        complexity: null,
        answer: null,
        judgeAnswer: null,
        completionAnswer: null,
        answerExplain: null,
        picPath: null,
        picId: null,
        status: null,
        field1: null,
        field2: null,
        field3: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remarks: null,
        delFlag: null,
        options: [],
        selectedOptions: [],
      };

      this.resetForm("form");
      this.match_questions_paper_detailList=[];
      this.questionids=[];
      this.match_paper_detail_questions_List=[];

    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    handleQuery2() {
      this.queryParams.pageNum = 1;
      this.getquestionList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery2();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    handleSelectionChange2(selection) {
      this.questionids = selection;
      this.single = selection.length!==1
      this.multiple = !selection.length
      this.match_questions_paper_detailList = this.questionids.map(item => item.id);
      for (const typeId of this.questionids.map(item => item.typeId)) {
        if (typeId == '1') this.form.selectSum +=1;
        else if (typeId == '2') this.form.mulSelectSum +=1;
        else if (typeId == '3') this.form.judgeSum +=1;
        else if (typeId == '4') this.form.uncertaintiesSum +=1;
        else if (typeId == '5') this.form.answerSum +=1;
        else if (typeId == '6') this.form.discussSum +=1;
        else if (typeId == '7') this.form.completionSum +=1;

      }


    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加编辑试卷";
    },

    all(){
      this.match_questions_paper_detailList = this.match_questions_paper_detailList.filter(item => item.id !== ids);
      this.form.paperSums = this.match_questions_paper_detailList.length;
      this.form.paperScore = this.form.judgeSum * this.form.judgeGrade
        +this.form.completionSum * this.form.completionGrade
        +this.form.discussSum * this.form.discussGrade
        +this.form.selectSum * this.form.selectGrade
        +this.form.uncertaintiesSum * this.form.uncertaintiesGrade
        +this.form.mulSelectSum * this.form.mulSelectGrade
        +this.form.answerSum * this.form.answerGrade
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      console.log(id)
      getMatch_questions_paper_detail_by_paperid(id)
        .then(response => {
          console.log(response)
          this.match_questions_paper_detailList = response.data;
          this.loading = false;
          const match_questions_infoList = [];
          this.questionids = this.match_questions_paper_detailList.map(item => item.questionId);

          // console.log('match_questions_paper_detailList',this.match_questions_paper_detailList)
          // console.log('questionids',this.questionids)

          for (const Id of this.questionids) {
              console.log('Id',Id)
              getMatch_questions_info(Id).then(response =>{
                const match_questions_info =response.data;
                console.log(match_questions_info);
                match_questions_infoList.push(match_questions_info);
              });
          }
          this.match_questions_paper_detailList = match_questions_infoList;
          console.log(match_questions_infoList);

        })
        .catch(error => {
          console.error('获取匹配问题信息时出错：', error);
        });
      getMatch_question_paper(id).then(response => {
        this.form = response.data;
        if (this.form.groupWay==='1')this.form.groupWay = '人工';
        else this.form.groupWay = '随机';

        this.open = true;
        this.title = "修改编辑试卷";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            if (this.form.groupWay==='人工')this.form.groupWay = '1';
            else this.form.groupWay = '2';

            updateMatch_question_paper(this.form).then(response => {
              const paperid = response.data;

              console.log(this.match_questions_paper_detailList);
            updateMatch_questions_paper_detail({paperid:paperid,list:this.match_questions_paper_detailList});

            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
            });

          } else {
            addMatch_question_paper(this.form).then(response => {
              const paperid = response.data;
              this.match_questions_paper_detailList = this.match_questions_paper_detailList.map(item => item.id);
              // console.log('???',this.match_questions_paper_detailList);
              // console.log('???',paperid);

              addMatch_questions_paper_detail({paperid:paperid,list:this.match_questions_paper_detailList}).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            });
          }
        }
      });
    },
    submitForm2() {
      this.$modal.msgSuccess("新增试题成功");
      this.match_questions_paper_detailList =this.questionids;
      this.form.paperSums = this.match_questions_paper_detailList.length;

      // 将 this.form.paperScore 转换为整数
      this.form.paperScore = this.form.judgeSum * this.form.judgeGrade
      +this.form.completionSum * this.form.completionGrade
      +this.form.discussSum * this.form.discussGrade
      +this.form.selectSum * this.form.selectGrade
      +this.form.uncertaintiesSum * this.form.uncertaintiesGrade
      +this.form.mulSelectSum * this.form.mulSelectGrade
      +this.form.answerSum * this.form.answerGrade

      this.openTwo = false;


    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除数据项？').then(function() {
        return delMatch_question_paper(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleDelete2(row) {
      const ids = row.id || this.ids;
      console.log('row',row);
      console.log('match_questions_paper_detailList',this.match_questions_paper_detailList);
      const flag = this.form.id;
      if (flag) {
        this.$modal.confirm('是否确认删除模拟试卷详情编号为"' + ids + '"的数据项？').then(function () {
          return delMatch_questions_paper_detail(ids);
        }).then(() => {
          this.getDetailList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });

        this.match_questions_paper_detailList = this.match_questions_paper_detailList.filter(item => item.id !== ids);
        this.form.paperSums = this.match_questions_paper_detailList.length;
        this.form.paperScore = this.form.judgeSum * this.form.judgeGrade
          +this.form.completionSum * this.form.completionGrade
          +this.form.discussSum * this.form.discussGrade
          +this.form.selectSum * this.form.selectGrade
          +this.form.uncertaintiesSum * this.form.uncertaintiesGrade
          +this.form.mulSelectSum * this.form.mulSelectGrade
          +this.form.answerSum * this.form.answerGrade

      }
      else {
        // 删除数组中与传入id相同的元素

        this.match_questions_paper_detailList = this.match_questions_paper_detailList.filter(item => item.id !== ids);
        this.form.paperSums = this.match_questions_paper_detailList.length;
        this.form.paperScore = this.form.judgeSum * this.form.judgeGrade
          +this.form.completionSum * this.form.completionGrade
          +this.form.discussSum * this.form.discussGrade
          +this.form.selectSum * this.form.selectGrade
          +this.form.uncertaintiesSum * this.form.uncertaintiesGrade
          +this.form.mulSelectSum * this.form.mulSelectGrade
          +this.form.answerSum * this.form.answerGrade

      }
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('match/match_question_paper/export', {
        ...this.queryParams
      }, `match_question_paper_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
