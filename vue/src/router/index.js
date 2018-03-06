import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import * as types from '../store/mutation-types'
import Login from '@/components/Login'
import Home from '@/components/Home'
import AddTeacher from '@/components/teacher/AddTeacher'
import FilterTeacher from '@/components/teacher/FilterTeacher'
import AddStudent from '@/components/student/AddStudent'
import FilterStudent from '@/components/student/FilterStudent'

Vue.use(Router)

const hello = {
  path: '/',
  name: 'Home',
  component: Home,
  title: 'Home',
  icon: 'fas fa-home'
}

const login = {
  path: '/login',
  name: 'Login',
  component: Login,
  title: '登录',
  icon: 'fas fa-sign-in-alt'
}

const addTeacher = {
  path: '/add-teacher',
  name: 'AddTeacher',
  component: AddTeacher,
  title: '添加教师',
  icon: 'fab fa-nintendo-switch'
}

const filterTeacher = {
  path: '/filter-teacher',
  name: 'FilterTeacher',
  component: FilterTeacher,
  title: '查找教师',
  icon: 'fas fa-chart-pie'
}

const addStudent = {
  path: '/add-student',
  name: 'AddStudent',
  component: AddStudent,
  title: '添加学生',
  icon: 'fas fa-graduation-cap'
}

const filterStudent = {
  path: '/filter-student',
  name: 'FilterStudent',
  component: FilterStudent,
  title: '查找学生',
  icon: 'fas fa-search'
}

const needLoginRouter = [
  {...addTeacher},
  {...filterTeacher},
  {...addStudent},
  {...filterStudent}
]

const router = new Router({
  routes: [
    {...hello},
    {...login},
    ...needLoginRouter
  ]
})

let isPathNeedLogin = path => {
  let res = false
  needLoginRouter.forEach(value => {
    if (path.startsWith(value.path)) {
      res = true
      return true
    }
  })
  return res
}

router.beforeEach((to, from, next) => {
  if (to.path === '/login' && store.state.isLogin) {
    next('/')
    return
  }
  if (isPathNeedLogin(to.path) && !store.state.isLogin) {
    // need login
    next('/login')
  } else {
    next()
  }
})

router.afterEach((to, from) => {
  window.scrollTo(0, 0)
})

// 初始化菜单
const menuList = []
menuList.push(hello)
menuList.push(addTeacher)
menuList.push(filterTeacher)
menuList.push(addStudent)
menuList.push(filterStudent)
store.commit(types.SET_MENU_LIST, menuList)

export default router
