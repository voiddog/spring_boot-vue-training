<template>
  <div id="add-teacher">
    <h2>添加教师</h2>
    <p>添加上课记录，<span style="color: red">*</span>为必填，其余的选填</p>
    <el-form :model="reqAddData" :rules="rules" ref="reqAddData" label-width="100px" class="card">
      <el-form-item label="教师名称" prop="name">
        <el-input v-model="reqAddData.name" placeholder="请输入教师名称"/>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="reqAddData.gender">
          <el-radio :label="CNT.MALE_STR"/>
          <el-radio :label="CNT.FEMALE_STR"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄">
        <el-select v-model="reqAddData.age" placeholder="请选择教师年龄">
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
      <el-form-item label="教师类型">
        <el-select v-model="reqAddData.type" placeholder="请选择教师类型">
          <el-option :label="CNT.TEACHER_IN_PROVINCE" :value="CNT.TEACHER_IN_PROVINCE"/>
          <el-option :label="CNT.TEACHER_OUT_PROVINCE" :value="CNT.TEACHER_OUT_PROVINCE"/>
          <el-option :label="CNT.TEACHER_COUNTRY" :value="CNT.TEACHER_COUNTRY"/>
        </el-select>
      </el-form-item>
      <el-form-item label="教师等级">
        <el-input v-model="reqAddData.levelType"/>
      </el-form-item>
      <el-form-item label="费用">
        <el-input v-model.number="reqAddData.cost"/>
      </el-form-item>
      <el-form-item label="上课记录">
        <el-input v-model="reqAddData.record" type="textarea"/>
      </el-form-item>
      <el-form-item label="上课评价">
        <el-input v-model="reqAddData.appraise" type="textarea"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('reqAddData')">提交</el-button>
        <el-button @click="resetForm('reqAddData')">重置</el-button>
      </el-form-item>
    </el-form>
    <br/>
    <p>新增的教师列表</p>
    <el-table
      :stripe="true"
      :data="newTeachers">
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
  import {TeacherBean} from '@/Beans'

  export default {
    name: 'AddTeacher',
    data () {
      return {
        reqAddData: new TeacherBean({
          gender: CNT.MALE_STR,
          age: 25,
          type: CNT.TEACHER_IN_PROVINCE
        }),
        CNT: {
          ...CNT
        },
        rules: {
          name: [
            {required: true, message: '请输入教师名称', trigger: 'blur'},
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
        newTeachers: []
      }
    },
    methods: {
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // add teacher
            let reqAddData = this.reqAddData
            let request = {...reqAddData}
            request.gender = reqAddData.gender === CNT.FEMALE_STR ? CNT.FEMALE : CNT.MALE
            const loading = this.$loading({
              lock: true,
              text: 'Sending'
            })
            netio.post('/teacher/add', request).then(data => {
              loading.close()
              this.$message({
                message: '添加教师成功',
                type: 'success'
              })
              this.newTeachers.push(new TeacherBean(data))
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
      handlerRemove (teacherDao) {
        if (!teacherDao) {
          return
        }
        const loading = this.$loading({
          lock: true,
          text: 'Sending'
        })
        netio.get('/teacher/remove', {params: {teacherId: teacherDao.id}})
          .then(resp => {
            loading.close()
            this.$message({
              message: '删除教师成功',
              type: 'success'
            })
            let index = this.newTeachers.indexOf(teacherDao)
            if (index >= 0) {
              this.newTeachers.splice(index, 1)
            }
          })
          .catch(e => {
            loading.close()
            this.$message({
              message: '删除教师失败',
              type: 'error'
            })
          })
      }
    }
  }
</script>
