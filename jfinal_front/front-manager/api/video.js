import request from '@/utils/request'
export default {
    getAuth(id) {
        return request({
            url: '/eduvod/getAuth?id=' + id,
            method: 'get'
        })
    }
}