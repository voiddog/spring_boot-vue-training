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
  TEACHER_IN_PROVINCE: 0,
  TEACHER_IN_PROVINCE_STR: '省内',
  TEACHER_OUT_PROVINCE: 1,
  TEACHER_OUT_PROVINCE_STR: '省外',
  TEACHER_COUNTRY: 2,
  TEACHER_COUNTRY_STR: '国家',
  // 教师等级
  TEACHER_LEVEL_PROFESSOR: 1,
  TEACHER_LEVEL_PROFESSOR_STR: '教授',
  TEACHER_LEVEL_ASSOCIATE_PROFESSOR: 2,
  TEACHER_LEVEL_ASSOCIATE_PROFESSOR_STR: '副教授',
  TEACHER_LEVEL_LECTURER: 3,
  TEACHER_LEVEL_LECTURER_STR: '讲师',

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
  convertTeacherTypeToString: function (type) {
    switch (type) {
      case this.TEACHER_IN_PROVINCE:
        return this.TEACHER_IN_PROVINCE_STR
      case this.TEACHER_OUT_PROVINCE:
        return this.TEACHER_OUT_PROVINCE_STR
      case this.TEACHER_COUNTRY:
        return this.TEACHER_COUNTRY_STR
    }
    return '未知'
  },
  convertTeacherTypeToValue: function (str) {
    switch (str) {
      case this.TEACHER_IN_PROVINCE_STR:
        return this.TEACHER_IN_PROVINCE
      case this.TEACHER_OUT_PROVINCE_STR:
        return this.TEACHER_OUT_PROVINCE
      case this.TEACHER_COUNTRY_STR:
        return this.TEACHER_COUNTRY
    }
    return this.TEACHER_IN_PROVINCE
  },
  convertTeacherLevelToString: function (type) {
    switch (type) {
      case this.TEACHER_LEVEL_ASSOCIATE_PROFESSOR:
        return this.TEACHER_LEVEL_ASSOCIATE_PROFESSOR_STR
      case this.TEACHER_LEVEL_PROFESSOR:
        return this.TEACHER_LEVEL_PROFESSOR_STR
      case this.TEACHER_LEVEL_LECTURER:
        return this.TEACHER_LEVEL_LECTURER_STR
    }
    return '未知'
  },
  convertTeacherLevelToValue: function (str) {
    switch (str) {
      case this.TEACHER_LEVEL_LECTURER_STR:
        return this.TEACHER_LEVEL_LECTURER
      case this.TEACHER_LEVEL_PROFESSOR_STR:
        return this.TEACHER_LEVEL_PROFESSOR
      case this.TEACHER_LEVEL_ASSOCIATE_PROFESSOR_STR:
        return this.TEACHER_LEVEL_ASSOCIATE_PROFESSOR
    }
    return this.TEACHER_LEVEL_ASSOCIATE_PROFESSOR
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
