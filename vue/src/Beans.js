class UserBean {
  constructor (param) {
    param = param || {}
    this.id = param.id
    this.name = param.name
    this.gender = param.gender
    this.age = param.age
    this.phone = param.phone
    this.organization = param.organization
    this.job = param.job
  }

  update (param) {
    param = param || {}
    this.id = param.id
    this.name = param.name
    this.gender = param.gender
    this.age = param.age
    this.phone = param.phone
    this.organization = param.organization
    this.job = param.job
  }
}

class TeacherBean extends UserBean {

  constructor (param) {
    super(param)
    param = param || {}
    this.type = param.type
    this.levelType = param.levelType
    this.record = param.record
    this.cost = param.cost
    // 评价
    this.appraise = param.appraise
  }

  update (param) {
    super.update(param)
    param = param || {}
    this.type = param.type
    this.levelType = param.levelType
    this.record = param.record
    this.cost = param.cost
    // 评价
    this.appraise = param.appraise
  }
}

class StudentBean extends UserBean {
  constructor (param) {
    super(param)
    param = param || {}
    this.record = param.record
  }

  update (param) {
    super.update(param)
    param = param || {}
    this.record = param.record
  }
}

export {TeacherBean, StudentBean, UserBean}
