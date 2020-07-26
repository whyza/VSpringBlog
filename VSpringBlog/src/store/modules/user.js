import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { showMessage } from "@/utils/showMessage"
const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    userId:""
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      localStorage.setItem('name', name);
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_USERID: (state, userId) => {
      state.userId = userId
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const userName = userInfo.userName.trim()
      return new Promise((resolve, reject) => {
        login(userName, userInfo.passWord).then(response => {
          if (response.success) {
            const data = response.result
            setToken(data.token)
            commit('SET_TOKEN', data.token)
            commit('SET_NAME', userName)
            commit('SET_USERID', data.userInfo.id)
          } else {
            showMessage(true, "error", response.message, 3000);
          }
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      const userName = localStorage.getItem('name');
      return new Promise((resolve, reject) => {
        getInfo(userName).then(response => {
          // if(response.data === 500){
          //    removeToken()
          // }
          // const data = response.data
          // if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
          commit('SET_ROLES', response.result)
          // } else {
          // reject('getInfo: roles must be a non-null array !')
          // }
          commit('SET_NAME', userName)
          // commit('SET_AVATAR', data.avatarUrl)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
