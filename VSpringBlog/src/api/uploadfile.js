
import request from '@/utils/request'

export function uploadImg(url, params) {
    return request({
        url: url,
        method: 'post',
        data: params,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}
