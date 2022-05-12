import request from '@/utils/request'
export default {
    loginUser(user) {
        return request({
            url: '/eduucenter/front/login',
            method: 'post',
            data: user
        })
    },
    getUserInfo() {
        return request({
            url: '/eduucenter/front/getUserInfo',
            method: 'get'
        })
    }
}