<template>
  <div id="filter-teacher" v-loading="loading">
    <!--弹出框-->
    <el-dialog title="修改内容" :visible.sync="dialog.edit.visible">
      <el-form :model="dialog.edit.teacher" label-width="100px" v-loading="loading">
        <el-form-item label="老师名称" prop="name">
          <el-input v-model="dialog.edit.teacher.name" placeholder="请输入老师名称"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="dialog.edit.teacher.gender" placeholder="无" style="width: 100px">
            <el-option :label="CNT.MALE_STR" :value="CNT.MALE"/>
            <el-option :label="CNT.FEMALE_STR" :value="CNT.FEMALE"/>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄">
          <el-select v-model="dialog.edit.teacher.age" placeholder="请选择老师年龄">
            <el-option v-for="n in 100" :value="n + 17" :key="n"/>
          </el-select>
        </el-form-item>
        <el-form-item label="单位" prop="organization">
          <el-input v-model="dialog.edit.teacher.organization"/>
        </el-form-item>
        <el-form-item label="职务" prop="job">
          <el-input v-model="dialog.edit.teacher.job"/>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model.number="dialog.edit.teacher.phone"/>
        </el-form-item>
        <el-form-item label="老师类型">
          <el-select v-model="dialog.edit.teacher.type" placeholder="请选择老师类型">
            <el-option :label="CNT.TEACHER_IN_PROVINCE" :value="CNT.TEACHER_IN_PROVINCE"/>
            <el-option :label="CNT.TEACHER_OUT_PROVINCE" :value="CNT.TEACHER_OUT_PROVINCE"/>
            <el-option :label="CNT.TEACHER_COUNTRY" :value="CNT.TEACHER_COUNTRY"/>
          </el-select>
        </el-form-item>
        <el-form-item label="老师等级">
          <el-input v-model.number="dialog.edit.teacher.levelType"/>
        </el-form-item>
        <el-form-item label="费用">
          <el-input v-model.number="dialog.edit.teacher.cost"/>
        </el-form-item>
        <el-form-item label="上课记录">
          <el-input v-model="dialog.edit.teacher.record" type="textarea"/>
        </el-form-item>
        <el-form-item label="上课评价">
          <el-input v-model="dialog.edit.teacher.appraise" type="textarea"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateTeacher()">确定</el-button>
          <el-button @click="dialog.edit.visible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="系统提示" :visible.sync="dialog.remove.visible">
      <span>确定删除该记录吗</span>
      <span class="dialog-footer" slot="footer">
        <el-button @click="dialog.remove.visible = false">取 消</el-button>
        <el-button type="primary" @click="deleteTeacher()">确 定</el-button>
      </span>
    </el-dialog>
    <!--正文-->
    <h2>查找老师</h2>
    <p>根据查找条件筛选出老师，超过一页会分页 <span class="container-hint">（筛选条件都是选填，不填则列出全部老师）</span></p>
    <!--查询头部-->
    <el-form :inline="true" :model="filterData" size="small" id="filter-form" class="card">
      <el-form-item label="编号" v-if=" 'id' in filterData ">
        <el-input v-model="filterData.id" placeholder="老师编号"/>
      </el-form-item>
      <el-form-item label="姓名" v-if=" 'name' in filterData ">
        <el-input v-model="filterData.name" placeholder="老师姓名"/>
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
      <el-form-item label="职务" v-if=" 'job' in filterData ">
        <el-input v-model="filterData.job" placeholder="职务"/>
      </el-form-item>
      <el-form-item label="类型" v-if=" 'type' in filterData ">
        <el-select v-model="filterData.type" placeholder="类型" style="width: 100px">
          <el-option label="全部" :value="undefined"/>
          <el-option label="省内" :value="CNT.TEACHER_IN_PROVINCE"/>
          <el-option label="省外" :value="CNT.TEACHER_OUT_PROVINCE"/>
          <el-option label="全国" :value="CNT.TEACHER_COUNTRY"/>
        </el-select>
      </el-form-item>
      <el-form-item label="等级" v-if=" 'levelType' in filterData ">
        <el-input v-model="filterData.levelType" placeholder="等级"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handlerFilter">查询</el-button>
      </el-form-item>
    </el-form>
    <!--老师列表-->
    <p>
      <span>查询结果 <i class="far fa-arrow-alt-circle-down"></i></span>
    </p>
    <div class="card" id="form-ret">
      <el-table
        :stripe="true"
        :data="teachers">
        <!--扩展内容-->
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="table-expand">
              <el-form-item label="花费金额">
                <span>{{ props.row.cost }}</span>
              </el-form-item>
              <el-form-item label="职务">
                <span>{{ props.row.job }}</span>
              </el-form-item>
              <el-form-item label="单位">
                <span>{{ props.row.organization }}</span>
              </el-form-item>
              <el-form-item label="上课记录">
                <span>{{ props.row.record }}</span>
              </el-form-item>
              <el-form-item label="评价">
                <span>{{ props.row.appraise }}</span>
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
          width="100"
          label="类型">
          <template slot-scope="scope">
            <el-tag :type="CNT.convertTypeTagColor(scope.row.type)">
              {{ scope.row.type }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          width="100"
          label="等级">
          <template slot-scope="scope">
            <span>{{ scope.row.levelType }}</span>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          width="100"
          label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="handlerModifyTeacher(scope.row)">编辑</el-button>
            <el-button type="text" class="btn-delete" @click="handlerDeleteTeacher(scope.row)">删除</el-button>
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
  import {TeacherBean} from '@/Beans'
  import netio from '@/netio'

  class Request extends TeacherBean {
    constructor (param) {
      super(param)
      param = param || {}
      this.pageIndex = param.pageIndex || 0
      this.pageSize = param.pageSize || 10
    }
  }

  export default {
    name: 'FilterTeacher',
    data () {
      return {
        loading: false,
        pageCount: 0,
        filterData: new Request(),
        CNT,
        tableLoading: false,
        teachers: [],
        dialog: {
          edit: {
            visible: false,
            teacher: {}
          },
          remove: {
            visible: false,
            teacher: undefined
          }
        }
      }
    },
    methods: {
      submitFilter (request) {
        this.loading = true
        netio.post('/teacher/filter', request).then(data => {
          this.loading = false
          this.pageCount = data.totalPages
          this.teachers = []
          data.content.forEach(v => {
            this.teachers.push(new TeacherBean(v))
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
      handlerModifyTeacher (teacher) {
        this.dialog.edit.visible = true
        this.dialog.edit.teacher = new TeacherBean(teacher)
      },
      handlerDeleteTeacher (teacher) {
        this.dialog.remove.visible = true
        this.dialog.remove.teacher = teacher
      },
      updateTeacher () {
        let edit = this.dialog.edit
        this.loading = true
        netio.post('/teacher/update', edit.teacher)
          .then(value => {
            this.loading = false
            edit.visible = false
            this.teachers.forEach(teacher => {
              if (teacher.id === value.id) {
                teacher.update(value)
              }
            })
          })
          .catch(reason => {
            this.loading = false
          })
      },
      deleteTeacher () {
        let remove = this.dialog.remove
        if (!remove.teacher) {
          return
        }
        this.loading = true
        netio.get('/teacher/remove', {params: {teacherId: remove.teacher.id}})
          .then(resp => {
            this.loading = false
            remove.visible = false
            this.$message({
              message: '删除老师成功',
              type: 'success'
            })
            remove.teacher = undefined
            let index = this.teachers.indexOf(remove.teacher)
            if (index >= 0) {
              this.teachers.splice(index, 1)
            } else {
              this.submitFilter(this.filterData)
            }
          })
          .catch(e => {
            this.loading = false
            remove.visible = false
            this.$message({
              message: '删除老师失败',
              type: 'error'
            })
          })
      }
    }
  }
</script>
