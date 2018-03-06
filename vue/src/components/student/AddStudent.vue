<template>
  <div id="add-student">
    <h2>添加学生</h2>
    <p>添加上课记录，<span style="color: red">*</span>为必填，其余的选填</p>
    <el-form :model="reqAddData" :rules="rules" ref="reqAddData" label-width="100px" class="card">
      <el-form-item label="学生名称" prop="name">
        <el-input v-model="reqAddData.name" placeholder="请输入学生名称"/>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="reqAddData.gender">
          <el-radio :label="CNT.MALE_STR"/>
          <el-radio :label="CNT.FEMALE_STR"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄">
        <el-select v-model.numder="reqAddData.age" placeholder="请选择学生年龄">
          <el-option v-for="n in 100" :value="n + 17" :key="n"/>
        </el-select>
      </el-form-item>
      <el-form-item label="单位" prop="organization">
        <el-input v-model="reqAddData.organization"/>
      </el-form-item>
      <el-form-item label="职业" prop="job">
        <el-input v-model="reqAddData.job"/>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model.number="reqAddData.phone"/>
      </el-form-item>
      <el-form-item label="上课记录">
        <el-input v-model="reqAddData.record" type="textarea"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('reqAddData')">提交</el-button>
        <el-button @click="resetForm('reqAddData')">重置</el-button>
      </el-form-item>
    </el-form>
    <br/>
    <p>新增的学生列表</p>
    <el-table
      :stripe="true"
      :data="newStudents">
      <el-table-column
        prop="id"
        label="编号"
        width="50">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        label="性别"
        width="80">
        <template slot-scope="scope">
          <el-tag :type="CNT.convertSexTagColor(scope.row.gender)">
            {{ CNT.convertSexToString(scope.row.gender) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="50">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handlerRemove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style lang="less" scoped>
  @import "../../styles/base.less";

  .el-form {
    width: @form-width;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    .el-form-item {
      width: 300px;
    }
  }
  .el-table {
    margin-top: 20px;
    width: @form-width;
    .el-button {
      color: @danger;
    }
  }
</style>

<script>
  import CNT from '@/constant'
  import netio from '@/netio'
  import {StudentBean} from '@/Beans'

  export default {
    name: 'AddStudent',
    data () {
      return {
        reqAddData: new StudentBean({
          gender: CNT.MALE_STR,
          age: 25,
          type: CNT.TEACHER_IN_PROVINCE,
          levelType: CNT.TEACHER_LEVEL_LECTURER
        }),
        CNT: {
          ...CNT
        },
        rules: {
          name: [
            {required: true, message: '请输入学生名称', trigger: 'blur'},
            {min: 1, max: 13, message: '长度在 1 到 15 个字符', trigger: 'blur'}
          ],
          organization: [
            {min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur'}
          ],
          job: [
            {min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur'}
          ],
          gender: [
            {required: true, message: '请选择性别', trigger: 'blur'}
          ]
        },
        newStudents: []
      }
    },
    methods: {
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // add student
            let reqAddData = this.reqAddData
            let request = {...reqAddData}
            request.gender = reqAddData.gender === CNT.FEMALE_STR ? CNT.FEMALE : CNT.MALE
            const loading = this.$loading({
              lock: true,
              text: 'Sending'
            })
            netio.post('/student/add', request).then(data => {
              loading.close()
              this.$message({
                message: '添加学生成功',
                type: 'success'
              })
              this.newStudents.push(new StudentBean(data))
            }).catch(reason => {
              loading.close()
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      },
      handlerRemove (studentDao) {
        if (!studentDao) {
          return
        }
        const loading = this.$loading({
          lock: true,
          text: 'Sending'
        })
        netio.get('/student/remove', {params: {studentId: studentDao.id}})
          .then(resp => {
            loading.close()
            this.$message({
              message: '删除学生成功',
              type: 'success'
            })
            let index = this.newStudents.indexOf(studentDao)
            if (index >= 0) {
              this.newStudents.splice(index, 1)
            }
          })
          .catch(e => {
            loading.close()
            this.$message({
              message: '删除学生失败',
              type: 'error'
            })
          })
      }
    }
  }
</script>
