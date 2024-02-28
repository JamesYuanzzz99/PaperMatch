<template>
  <div class="app-container">
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
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
                        v-model="queryParams.createTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['match:match_questions_info:add']"
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
          v-hasPermi="['match:match_questions_info:edit']"
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
          v-hasPermi="['match:match_questions_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:match_questions_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="match_questions_infoList" @selection-change="handleSelectionChange">
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
      <el-table-column label="更新时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:match_questions_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:match_questions_info:remove']"
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

    <!-- 添加或修改模拟试题对话框 --> <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="70px">
      <el-form-item label="题干" prop="content">
        <el-input v-model="form.content" type="textarea" placeholder="请输入内容" />
      </el-form-item>


      <el-form-item label="题型" prop="typeId">
        <el-select v-model="form.typeId" placeholder="请选择题型">
          <el-option
            v-for="dict in dict.type.question_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>


        <el-form-item label="题库" prop="questionBankId">
        <el-select v-model="form.questionBankId" placeholder="请选择题库">
          <el-option
            v-for="questionBank in questionBanks"
            :key="questionBank.id"
            :label="questionBank.name"
            :value="questionBank.id"
          ></el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="难度" prop="complexity">
          <el-radio-group v-model="form.complexity">
            <el-radio
              v-for="dict in dict.type.complexity"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>


        <el-form-item label="答案">
          <template v-if="form.typeId === '1'">
            <!-- 单选题 -->
            <el-radio-group v-model="selectedOptions">
              <el-radio v-for="option in options_List" :key="option.optionNum" :label="option.optionNum" @change="updateAnswer(option)">
                {{ option.optionNum }}
                <el-input v-model="option.content" placeholder="请输入选项内容" />
                <!-- 删除按钮只在新选项上显示 -->
                <el-button v-if="option.sort > 4" type="danger" icon="el-icon-delete" @click="removeOption(option)">删除选项</el-button>
              </el-radio>
            </el-radio-group>
            <el-button type="primary" icon="el-icon-plus" @click="addOption">新增选项</el-button>
          </template>

          <template v-else-if="form.typeId === '2' || form.typeId === '5'">
            <!-- 多选题 -->

            <el-checkbox-group v-model="selectedOptions">
              <el-checkbox v-for="option in options_List" :key="option.optionNum" :label="option.optionNum" @change="updateAnswer2">
                {{ option.optionNum }}
                <el-input v-model="option.content" placeholder="请输入选项内容" />

                <el-button v-if="option.sort > 4" type="danger" icon="el-icon-delete" @click="removeOption(option)">删除选项</el-button>

              </el-checkbox>
            </el-checkbox-group>
            <el-button type="primary" icon="el-icon-plus" @click="addOption">新增选项</el-button>
          </template>


            <template v-else-if="form.typeId === '3'">
              <!-- 判断题 -->
              <el-radio-group v-model="selectedOptions">
                <el-radio v-for="option in options_List2" :key="option.optionNum" :label="option.optionNum" @change="updateAnswer(option)">
                  {{ option.optionNum }}
                </el-radio>
              </el-radio-group>
            </template>




          <template v-else-if="form.typeId === '4'">
            <!-- 填空题 -->
            <el-radio-group v-model="form.completionAnswer">
              <el-input v-model="form.completionAnswer" type="textarea" placeholder="请输入答案" />
            </el-radio-group>
          </template>

          <template v-else-if="form.typeId === '6'">
            <!-- 简答题 -->
            <el-upload
              class= ""
              drag
              action="http://localhost/dev-api/match/match_questions_info/answer"
              :headers="{ Authorization: 'Bearer ' + this.extractTokenFromCookie() }"
              :on-success="handleUploadSuccess"
              :on-remove = "deleteFile"
              :show-file-list = true
              :file-list = "showAnswerFile"
            multiple>

              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">至少上传一个pdf文件</div>
            </el-upload>
          </template>


          <template v-else-if="form.typeId === '7'">
            <!-- 论述题 -->
            <el-radio-group v-model="form.completionAnswer">
              <el-input v-model="form.completionAnswer" type="textarea" placeholder="请输入答案" />
            </el-radio-group>

            <el-upload
              class= ""
              drag
              action="http://localhost/dev-api/match/match_questions_info/answer"
              :headers="{ Authorization: 'Bearer ' + this.extractTokenFromCookie() }"
              :on-success="handleUploadSuccess"
              :on-remove = "deleteFile"
              :show-file-list = true
              :file-list = "showAnswerFile"
              multiple>

              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">至少上传一个pdf文件</div>
            </el-upload>
          </template>




        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import {
  listMatch_questions_info,
  getMatch_questions_info,
  delMatch_questions_info,
  addMatch_questions_info,
  updateMatch_questions_info,
  delMatch_questions_info_answerfile
} from "@/api/match/match_questions_info";
import {listMatch_questions_bank2}from "@/api/match/match_questions_bank";
import {delMatch_question_option, listMatch_question_option,} from "@/api/match/match_question_option";

export default {
  name: "Match_questions_info",
  dicts: ['question_type', 'complexity', 'sys_normal_disable'],
  data() {
    return {
      //存储选项列表数据
      options_List: [
        { optionNum: 'A', content: '', sort: 1 },
        { optionNum: 'B', content: '', sort: 2 },
        { optionNum: 'C', content: '', sort: 3 },
        { optionNum: 'D', content: '', sort: 4 },
      ],
      options_List2:[
        { optionNum:'正确', content:'', sort: 1},
        { optionNum:'错误', content:'', sort: 2},

      ],

      showAnswerFile:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      questionBanks: [] ,// 存储题库列表数据
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 模拟试题表格数据
      match_questions_infoList: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        questionBankId: null,
        typeId: null,
        complexity: null,
        status: null,
        createTime: null,
      },
      selectedOptions: [],
      // 表单参数
         form: {

           options: [],
           answer : null,
           field1:null

         },


      dictTypeOptions: [], // 存放字典表中的题型选项
      practiceQuestionOptionRowIdx: 0,
      // 表单校验
      rules: {
        content: [
          { required: true, message: "题干内容不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "题型不能为空", trigger: "change" }
        ],
        complexity: [
          { required: true, message: "难易程度不能为空", trigger: "change" }
        ],
        // answer: [
        //   { required: true, message: "正确答案不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
    this.getQuestionBanks();
  },
  methods: {
    extractTokenFromCookie() {
      const cookies = document.cookie.split(';');
      for (const cookie of cookies) {
        const [cookieName, cookieValue] = cookie.trim().split('=');
        if (cookieName === 'Admin-Token') {
          return cookieValue;
        }
      }
    },

    updateAnswer(option) {
      // 将选中的单选框的值赋给 form 的 answer 属性
      this.form.answer = option.optionNum;
      if (!this.form.options.includes(option)) {
        this.form.options.push(option);
      }
    },
    handleUploadSuccess(response, file, fileList) {
      console.log('handleUploadSuccess');

      if (response) {
          this.form.field1 = fileList.map(item => item.response.field1).join(',');
        this.$message.success('文件上传成功');
      } else {
        this.$message.error('文件上传失败');
      }
    },

    updateAnswer2(option,value) {

      this.selectedOptions.sort((a, b) => {
        const optionA = this.options_List.find(opt => opt.optionNum === a);
        const optionB = this.options_List.find(opt => opt.optionNum === b);
        return optionA.sort - optionB.sort;
      });
      // this.form.answer = this.form.selectedOptions.join(',');
    },
    addOption() {
      // Generate a new option
      const newOption = {
        optionNum: String.fromCharCode(65 + this.options_List.length), // A, B, C, ...
        content: '',
        sort: this.options_List.length + 1,
      };

      // Add the new option to options_List
      this.options_List.push(newOption);

       this.updateAnswer2(newOption);
    },
    // 移除选项
    removeOption(option) {
      if (option.id) {
        // 如果选项有 ID，说明是已保存到数据库的选项
        this.$modal.confirm('是否确认删除选项？').then(() => {
          // 调用删除选项的数据库操作，假设 delMatch_question_option 方法返回一个 Promise
          return delMatch_question_option(option.id);
        }).then(() => {
          // 删除成功后更新选项列表
          this.updateOptions();
          this.options_List = this.options_List.filter(item => item !== option);
          // 从选中的选项中移除被删除选项的字母
          if(this.selectedOptions) {
            this.selectedOptions = this.selectedOptions.filter(item => item !== option.optionNum);
            // 更新 answer 字段
            this.form.answer = this.selectedOptions.join(',');
          }
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
          // 处理删除失败的情况
        });
      } else {
        // 如果选项没有 ID，可能是新添加的选项，直接从选项列表中移除
        this.selectedOptions = this.selectedOptions.filter(item => item !== option.optionNum);

        this.options_List = this.options_List.filter(item => item !== option);
        this.$modal.msgSuccess("删除成功");
      }
    },

    updateOptions() {
      // 从选项列表中移除选项
      const index = this.options_List.findIndex(opt => opt === this.currentOption);
      if (index !== -1) {
        this.options_List.splice(index, 1);
      }
    },

    getQuestionBanks() {
      listMatch_questions_bank2().then(response => {
        this.questionBanks = response;
      });
    },
        /*删除答案附件*/
    deleteFile(file, fileList) {

        const field1 = file.url;
        delMatch_questions_info_answerfile({ filePath: field1 })
          .then(() => {
            // 成功后可以提示用户文件删除成功
            if (this.form.field1.length === field1.length && this.form.field1 === field1) {
              this.form.field1 = null;
              console.log("删完了");
            } else {
              const regex = new RegExp(field1);
              const match = this.form.field1.match(regex);
              if (match) {
                const index = match.index;
                const length = match[0].length;
                // 删除操作
                if (this.form.field1[index - 1] === ',') {
                  console.log("匹配的子字符串前面有逗号")
                  this.form.field1 = this.form.field1.slice(0, index - 1) + this.form.field1.slice(index);
                  this.form.field1 = this.form.field1.replace(regex, '');
                  console.log("没删完");

                } else if (this.form.field1[index + length] === ',') {
                  console.log("匹配的子字符串后面有逗号")
                  this.form.field1 = this.form.field1.slice(0, index + length) + this.form.field1.slice(index + length + 1);
                  this.form.field1 = this.form.field1.replace(regex, '');
                  console.log("没删完");

                } else {
                  console.log("匹配的子字符串前后都没有逗号，直接删除")
                  this.form.field1 = null;
                  console.log("删完了");
                }
              }
            }
            this.$message.success('文件删除成功');
          })
          .catch(error => {
            // 处理删除文件失败的情况
            this.$message.error(`文件删除失败`);
          });
    },


    /** 查询模拟试题列表 */
    getList() {
      this.loading = true;
      listMatch_questions_info(this.queryParams).then(response => {
        this.match_questions_infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {

      this.form = {
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
      this.options_List =    [
        { optionNum:'A', content:'', sort: 1},
        { optionNum:'B', content:'', sort: 2},
        { optionNum:'C', content:'', sort: 3},
        { optionNum:'D', content:'', sort: 4},
      ];

      this.options_List2=[
        { optionNum:'正确', content:'', sort: 1},
        { optionNum:'错误', content:'', sort: 2},

      ];
      this.resetForm("form");

      this.showAnswerFile = [];

    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();

    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加模拟试题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      if (row.typeId === '2') {
        try {
          this.selectedOptions = JSON.parse(row.answer);
        } catch (error) {
          console.error("Error parsing selectedOptions:", error);
        }
      }

      this.reset();
      const id = row.id || this.ids;
      getMatch_questions_info(id).then(response => {
        this.form = response.data;
        this.options_List = response.data.options;
        this.options_List.sort((a, b) => a.sort - b.sort);

        // 更新 showAnswerFile 为包含文件信息的数组
        if (response.data.field1!=null) {
          const paths = response.data.field1.split(',').map(item => item.trim());

          this.showAnswerFile = paths.map(path => ({
            name: path.split('/').pop(),
            url: path
          }));
        }
        this.open = true;
        this.title = "修改模拟试题";
      }
      );
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (this.selectedOptions && this.selectedOptions.length > 0) {
          this.form.answer = JSON.stringify(this.selectedOptions);
        }
        else {this.form.answer = null;}
        if (valid) {
          if (this.form.id != null) {
            updateMatch_questions_info(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.options = this.options_List
            addMatch_questions_info(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }

        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除试题？').then(function() {
        return delMatch_questions_info(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('match/match_questions_info/export', {
        ...this.queryParams
      }, `match_questions_info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
