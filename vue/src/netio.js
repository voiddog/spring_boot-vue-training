import axios from 'axios'
import CNT from '@/constant'

const netio = axios.create({
  baseURL: 'http://localhost:8080/'
})

// download url
const downloadUrl = url => {
  console.log('download: ' + url)
  let iframe = document.createElement('iframe')
  iframe.style.display = 'none'
  iframe.src = url
  iframe.onload = function () {
    document.body.removeChild(iframe)
  }
  document.body.appendChild(iframe)
}

netio.interceptors.response.use(value => {
  // 处理excel文件
  if (value.headers) {
    let contentType = value.headers['content-type']
    if (contentType === 'application/octet-stream' || contentType === 'application/x-msdownload'
      || contentType === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
      downloadUrl(value.request.responseURL)
      return
    }
  }
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
