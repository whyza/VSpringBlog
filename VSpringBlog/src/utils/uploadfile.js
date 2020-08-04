import request from '@/utils/request'

export function uploadImg(url, data) {
    return request({
        url: url,
        method: 'post',
        data: data,
        headers: { 'Content-Type': 'multipart/form-data' },
    })
}