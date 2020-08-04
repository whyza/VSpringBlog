
import request from '@/utils/request'

export function getAllCategory(url) {
  return request({
    url: url,
    method: 'get'
  })
}

export function getCategoryNameById(url, params) {
  return request({
    url: url,
    method: 'get',
    params: params
  })
}

