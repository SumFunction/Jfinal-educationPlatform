import request from '@/utils/request'
export default {
    getAllbanner() {
        return request({
            url: '/eduservice/front/findAllBanner',
            method: 'get'
        })
    }
}