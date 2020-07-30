import axios from 'axios'
import { Message } from 'element-ui'
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
    if (res.code === 500 || res.code === 400) {
      showMessage(true, "error", res.msg, 1000);
    } else {
      return res
    }
  }
  ,
  error => {
    Message({
      message: error.response.data.msg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)
export default service
