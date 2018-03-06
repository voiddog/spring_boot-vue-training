<template>
  <div id="filter-student" v-loading="loading">
    <!--弹出框-->
    <el-dialog title="修改内容" :visible.sync="dialog.edit.visible">
      <el-form :model="dialog.edit.student" label-width="100px" v-loading="loading">
        <el-form-item label="学生名称" prop="name">
          <el-input v-model="dialog.edit.student.name" placeholder="请输入学生名称"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="dialog.edit.student.gender" placeholder="无" style="width: 100px">
            <el-option :label="CNT.MALE_STR" :value="CNT.MALE"/>
            <el-option :label="CNT.FEMALE_STR" :value="CNT.FEMALE"/>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄">
          <el-select v-model.numder="dialog.edit.student.age" placeholder="请选择学生年龄">
            <el-option v-for="n in 100" :value="n + 17" :key="n"/>
          </el-select>
        </el-form-item>
        <el-form-item label="单位" prop="organization">
          <el-input v-model="dialog.edit.student.organization"/>
        </el-form-item>
        <el-form-item label="职业" prop="job">
          <el-input v-model="dialog.edit.student.job"/>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model.number="dialog.edit.student.phone"/>
        </el-form-item>
        <el-form-item label="上课记录">
          <el-input v-model="dialog.edit.student.record" type="textarea"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateStudent()">确定</el-button>
          <el-button @click="dialog.edit.visible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="系统提示" :visible.sync="dialog.remove.visible">
      <span>确定删除该记录吗</span>
      <span class="dialog-footer" slot="footer">
        <el-button @click="dialog.remove.visible = false">取 消</el-button>
        <el-button type="primary" @click="deleteStudent()">确 定</el-button>
      </span>
    </el-dialog>
    <!--正文-->
    <h2>查找学生</h2>
    <p>根据查找条件筛选出学生，超过一页会分页 <span class="container-hint">（筛选条件都是选填，不填则列出全部学生）</span></p>
    <!--查询头部-->
    <el-form :inline="true" :model="filterData" size="small" id="filter-form" class="card">
      <el-form-item label="编号" v-if=" 'id' in filterData ">
        <el-input v-model="filterData.id" placeholder="学生编号"/>
      </el-form-item>
      <el-form-item label="姓名" v-if=" 'name' in filterData ">
        <el-input v-model="filterData.name" placeholder="学生姓名"/>
      </el-form-item>
      <el-form-item label="性别" v-if=" 'gender' in filterData ">
        <el-select v-model="filterData.gender" placeholder="全部" style="width: 100px">
          <el-option label="全部" :value="undefined"/>
          <el-option :label="CNT.MALE_STR" :value="CNT.MALE"/>
          <el-option :label="CNT.FEMALE_STR" :value="CNT.FEMALE"/>
        </el-select>
      </el-form-item>
      <el-form-item label="单位" v-if=" 'organization' in filterData ">
        <el-input v-model="filterData.organization" placeholder="单位"/>
      </el-form-item>
      <el-form-item label="工作" v-if=" 'job' in filterData ">
        <el-input v-model="filterData.job" placeholder="工作"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handlerFilter">查询</el-button>
      </el-form-item>
    </el-form>
    <!--学生列表-->
    <p>
      <span>查询结果 <i class="far fa-arrow-alt-circle-down"></i></span>
    </p>
    <div class="card" id="form-ret">
      <el-table
        :stripe="true"
        :data="students">
        <!--扩展内容-->
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="table-expand">
              <el-form-item label="工作">
                <span>{{ props.row.job }}</span>
              </el-form-item>
              <el-form-item label="单位">
                <span>{{ props.row.organization }}</span>
              </el-form-item>
              <el-form-item label="上课记录">
                <span>{{ props.row.record }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="id"
          width="50"
          label="编号">
        </el-table-column>
        <el-table-column
          prop="name"
          width="150"
          label="姓名">
        </el-table-column>
        <el-table-column
          width="80"
          label="性别">
          <template slot-scope="scope">
            <el-tag :type="CNT.convertSexTagColor(scope.row.gender)">
              {{ CNT.convertSexToString(scope.row.gender) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="age"
          width="50"
          label="年龄">
        </el-table-column>
        <el-table-column
          prop="phone"
          width="200"
          label="电话号码">
        </el-table-column>
        <el-table-column
          prop="organization"
          width="200"
          label="单位">
        </el-table-column>
        <el-table-column
          fixed="right"
          width="100"
          label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="handlerModifyStudent(scope.row)">编辑</el-button>
            <el-button type="text" class="btn-delete" @click="handlerDeleteStudent(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-if="pageCount > 1"
        layout="prev, pager, next"
        @current-change="handlerPageChange"
        :current-page="filterData.pageIndex+1"
        :page-count="pageCount">
      </el-pagination>
    </div>
  </div>
</template>

<style lang="less">
  @import "../../styles/base.less";

  .container-hint {
    color: @txt-secondary;
    font-style: italic;
  }

  .el-form {
    width: @form-width;
  }

  .el-dialog {
    .el-form {
      width: 100%;
    }
  }

  .btn-delete {
    color: @danger;
  }

  #form-ret {
    display: flex;
    flex-direction: column;
    .el-pagination {
      margin-top: 10px;
      align-self: center;
    }
  }

  .table-expand {
    font-size: 0;
    label {
      width: 90px;
      color: #99a9bf;
    }
    .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;
    }
  }
</style>

<script>
  import CNT from '@/constant'
  import {StudentBean} from '@/Beans'
  import netio from '@/netio'

  class Request extends StudentBean {
    constructor (param) {
      super(param)
      param = param || {}
      this.pageIndex = param.pageIndex || 0
      this.pageSize = param.pageSize || 10
    }
  }

  export default {
    name: 'FilterStudent',
    data () {
      return {
        loading: false,
        pageCount: 0,
        filterData: new Request(),
        CNT,
        tableLoading: false,
        students: [],
        dialog: {
          edit: {
            visible: false,
            student: {}
          },
          remove: {
            visible: false,
            student: undefined
          }
        },
      }
    },
    methods: {
      submitFilter (request) {
        this.loading = true
        netio.post('/student/filter', request).then(data => {
          this.loading = false
          this.pageCount = data.totalPages
          this.students = []
          data.content.forEach(v => {
            this.students.push(new StudentBean(v))
          })
        }).catch(error => {
          this.loading = false
          console.log(error)
        })
      },
      handlerFilter () {
        this.filterData.pageIndex = 0
        this.submitFilter(this.filterData)
      },
      handlerPageChange (index) {
        this.filterData.pageIndex = index - 1
        this.submitFilter(this.filterData)
      },
      handlerModifyStudent (student) {
        this.dialog.edit.visible = true
        this.dialog.edit.student = new StudentBean(student)
      },
      handlerDeleteStudent (student) {
        this.dialog.remove.visible = true
        this.dialog.remove.student = student
      },
      updateStudent () {
        let edit = this.dialog.edit
        this.loading = true
        netio.post('/student/update', edit.student)
          .then(value => {
            this.loading = false
            edit.visible = false
            this.students.forEach(student => {
              if (student.id === value.id) {
                student.update(value)
              }
            })
          })
          .catch(reason => {
            this.loading = false
          })
      },
      deleteStudent() {
        let remove = this.dialog.remove
        if (!remove.student) {
          return
        }
        this.loading = true
        netio.get('/student/remove', {params: {studentId: remove.student.id}})
          .then(resp => {
            this.loading = false
            remove.visible = false
            this.$message({
              message: '删除学生成功',
              type: 'success'
            })
            remove.student = undefined
            let index = this.students.indexOf(remove.student)
            if (index >= 0) {
              this.students.splice(index, 1)
            } else {
              this.submitFilter(this.filterData)
            }
          })
          .catch(e => {
            this.loading = false
            remove.visible = false
            this.$message({
              message: '删除学生失败',
              type: 'error'
            })
          })
      }
    }
  }
</script>
