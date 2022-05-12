import request from '@/utils/request'
export default {
    info() {
        return request({
            url: '/eduservice/info?token=' + '123',
            method: 'get',
        })
    }
} 