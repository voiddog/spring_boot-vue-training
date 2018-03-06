const setCookie = (cName, value, expiredays) => {
  let exdate = new Date()
  exdate.setDate(exdate.getDate() + expiredays)
  if (value) {
    value = encodeURI(value)
  } else {
    value = ''
  }
  document.cookie = cName + '=' + value + ((expiredays == null) ? '' : ';expires=' + exdate.toGMTString())
}

function getCookie (name) {
  let reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
  let arr = document.cookie.match(reg)
  if (arr) {
    let token = arr[2]
    return decodeURI(token)
  } else {
    return null
  }
}

function getToken () {
  return getCookie('token')
}

function saveToken (token) {
  setCookie('token', token)
}

export default {
  setCookie,
  getCookie,
  getToken,
  saveToken
}
