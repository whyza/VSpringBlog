// import request from '@/utils/request'

// export function login(username, password) {
//   return request({
//     url: 'https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin/user/login',
//     method: 'post',
//     data: {
//       username,
//       password
//     }
//   })
// }

// export function getInfo(token) {
//   return request({
//     url: 'https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin/user/info',
//     method: 'get',
//     params: { token }
//   })
// }

// export function logout() {
//   return request({
//     url: 'https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin/user/logout',
//     method: 'post'
//   })
// }
import request from '@/utils/request'

export function login(userName, passWord) {
  return request({
    url: '/sys/login',
    method: 'post',
    data: {
      userName,
      passWord
    }
  })
}

export function getInfo(userName) {
  return request({
    url: '/sys/getUsreInfo',
    method: 'get',
    params: {userName}
  })
}
export function registerUser(userName,password) {
  return request({
    url: '/userRegister',
    method: 'post',
    data: {userName,password}
  })
}

export function logout() {
  return request({
    url: 'https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin/user/logout',
    method: 'post'
  })
}
