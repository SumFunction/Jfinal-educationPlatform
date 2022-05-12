import request from '@/utils/request'
export default {
    registUser(user) {
        return request({
            url: '/eduucenter/front/regist',
            method: 'post',
            data: user
        })
    }
}