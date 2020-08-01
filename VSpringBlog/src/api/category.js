
import request from '@/utils/request'

export function getAllCategory(url) {
  return request({
    url: url,
    method: 'get'
  })
}
