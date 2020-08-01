import axios from 'axios'
import { Message } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'
import { showMessage } from "@/utils/showMessage"


// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 url
  timeout: 1000000 // 请求超时时间
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
    Promise.resolve(error)
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
  },
  error => {
    if (error && error.response) {
      switch (error.response.status) {
        case 400: error.message = '请求错误(400)'; break;
        case 401: error.message = '未授权，请重新登录(401)'; break;
        case 403: error.message = '拒绝访问(403)'; break;
        case 404: error.message = '请求出错(404)'; break;
        case 408: error.message = '请求超时(408)'; break;
        case 500: error.message = '服务器错误(500)'; break;
        case 501: error.message = '服务未实现(501)'; break;
        case 502: error.message = '网络错误(502)'; break;
        case 503: error.message = '服务不可用(503)'; break;
        case 504: error.message = '网络超时(504)'; break;
        case 505: error.message = 'HTTP版本不受支持(505)'; break;
        default: error.message = `连接出错(${error.response.status})!`;
      }
    } else {
      error.message = '连接服务器失败!'
    }
    showMessage(true, "error", error.response.data.msg, 1000);
    return Promise.reject(error);
  }
)
export default service
