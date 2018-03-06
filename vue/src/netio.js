import axios from 'axios'
import CNT from '@/constant'

const netio = axios.create({
  baseURL: 'http://localhost:8080/'
})

netio.interceptors.response.use(value => {
  if (!value.data) {
    alert('系统傻逼了')
    return Promise.reject(value)
  }
  if (value.data.status !== CNT.STATUS_SUCCESS) {
    console.log('onError: ' + JSON.stringify(value.data))
    alert(value.data.message)
    return Promise.reject(value)
  }
  return value.data.data
}, error => {
  console.log(error)
  return Promise.reject(error)
})

export default netio
