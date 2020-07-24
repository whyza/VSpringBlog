
import request from '@/utils/request'

export function addArticle(url, data) {
  return request({
    url: url,
    method: 'post',
    data: data
  })
}

export function getArticleContentByUserId(url, articleId) {
  return request({
    url: url,
    method: 'GET',
    params: articleId
  })
}

export function getArticleListPage(url, params) {
  return request({
    url: url,
    method: 'get',
    params: params
  })
}