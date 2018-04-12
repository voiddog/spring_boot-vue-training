<template>
  <div>
    <h1>老师</h1>
    <div class="card">
      <el-form id="teacher">
        <label for="upload_teacher">导入老师</label>
        <input type="file" id="upload_teacher" name="file" @change="onTeacherChange($event)" accept=".xls,.xlsx">
        <el-button size="mini" type="primary" @click="uploadTeacher">导入</el-button>
      </el-form>
      <br />
      <el-button type="success" @click="exportTeacher">导出老师表</el-button>
    </div>
    <h1>学员</h1>
    <div class="card">
      <el-form id="student">
        <label for="upload_teacher">导入学员</label>
        <input type="file" id="upload_student" name="file" @change="onStudentChange($event)" accept=".xls,.xlsx">
        <el-button size="mini" type="primary" @click="uploadStudent">导入</el-button>
      </el-form>
      <br />
      <el-button type="success" @click="exportStudent">导出学员表</el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
  @import "../styles/base.less";

  .card {
    width: @form-width;
  }
</style>

<script>
  import netio from '@/netio'

  export default {
    name: 'ImportOrExport',
    data() {
      return {
        teacherFile: undefined,
        studentFile: undefined
      }
    },
    methods: {
      onTeacherChange(event) {
        this.teacherFile = event.target.files[0]
      },
      uploadTeacher() {
        if (this.teacherFile) {
          let formData = new FormData()
          formData.append('file', this.teacherFile)
          netio.post('/xls/upload/teacher', formData, {
            method: 'post',
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }).then(response => {
            this.$message({
              message: '导入成功',
              type: 'success'
            })
          })
        } else {
          alert('请选择文件')
        }
      },
      exportTeacher() {
        netio.post('/xls/backup/teacher')
          .then(response => {
            netio.get('/download', {params: {name: response}})
          })
      },
      onStudentChange(event) {
        this.studentFile = event.target.files[0]
      },
      uploadStudent() {
        if (this.studentFile) {
          let formData = new FormData()
          formData.append('file', this.studentFile)
          netio.post('/xls/upload/student', formData, {
            method: 'post',
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }).then(response => {
            this.$message({
              message: '导入成功',
              type: 'success'
            })
          })
        } else {
          alert('请选择文件')
        }
      },
      exportStudent() {
        netio.post('/xls/backup/student')
          .then(response => {
            netio.get('/download', {params: {name: response}})
          })
      }
    }
  }
</script>
