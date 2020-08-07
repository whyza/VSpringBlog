
import request from '@/utils/request'

export function addComment(url, data) {
  return request({
    url: url,
    method: 'post',
    data: data
  })
}
export function queryComment(url, params) {
  return request({
    url: url,
    method: 'get',
    params
  })
}
export function queryAllCommentsListPage(url, params) {
  return request({
    url: url,
    method: 'get',
    params
  })
}
