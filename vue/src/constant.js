const CNT = {
  // server url
  SERVER_URL: 'http://localhost:8080',
  // server code
  STATUS_SUCCESS: 200,
  STATUS_SYS_ERROR: 100,
  STATUS_MSG_ERROR: 101,
  MALE: 1,
  MALE_STR: '男',
  FEMALE: 0,
  FEMALE_STR: '女',
  // 教师类型
  TEACHER_IN_PROVINCE: '省内',
  TEACHER_OUT_PROVINCE: '省外',
  TEACHER_COUNTRY: '国家',

  convertSexToString: function (type) {
    switch (type) {
      case CNT.MALE:
        return CNT.MALE_STR
      case CNT.FEMALE:
        return CNT.FEMALE_STR
    }
    return '未知'
  },
  convertSexToValue: function (str) {
    switch (str) {
      case CNT.MALE_STR:
        return CNT.MALE
      case CNT.FEMALE_STR:
        return CNT.FEMALE
    }
    return CNT.FEMALE
  },
  convertSexTagColor: function (gender) {
    switch (gender) {
      case CNT.MALE:
        return ''
      case CNT.FEMALE:
        return 'danger'
      default:
        return 'info'
    }
  },
  convertTypeTagColor: function (type) {
    switch (type) {
      case CNT.TEACHER_IN_PROVINCE:
        return ''
      case CNT.TEACHER_OUT_PROVINCE:
        return 'warning'
      case CNT.TEACHER_COUNTRY:
        return 'success'
      default:
        return 'info'
    }
  }
}

export default CNT
