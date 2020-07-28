import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'
import { showMessage } from "@/utils/showMessage"


// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 url
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      //请求携带自定义token
      config.headers['Access-Token'] = getToken()
    }

    return config
  },
  error => {
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code === 500) {
      showMessage(true, "error", res.msg, 1000);
    } else {
      return res
    }
    // if (!getToken()) {
    //   MessageBox.confirm(
    //     '你已被登出，可以取消继续留在该页面，或者重新登录',
    //     '确定登出',
    //     {
    //       confirmButtonText: '重新登录',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }
    //   ).then(() => {
    //     store.dispatch('FedLogOut').then(() => {
    //       location.reload() // 为了重新实例化vue-router对象 避免bug
    //     })
    //   })
    //   return Promise.reject('error')
    // } else {
    //   return res
    // }

  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)
export default service
