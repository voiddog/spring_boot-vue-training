<template>
  <div>
    <h1>教师</h1>
    <div class="card">
      <el-form id="teacher">
        <label for="upload_teacher">导入教师</label>
        <input type="file" id="upload_teacher" name="file" @change="onTeacherChange($event)" accept=".xls,.xlsx">
        <el-button size="mini" type="primary" @click="uploadTeacher">导入</el-button>
      </el-form>
      <br />
      <el-button type="success" @click="exportTeacher">导出教师表</el-button>
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
        teacherFile: undefined
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
      }
    }
  }
</script>
