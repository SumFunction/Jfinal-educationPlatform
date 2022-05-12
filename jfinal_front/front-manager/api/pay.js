import request from '@/utils/request'
export default {
    createPayForm(orderNo) {
        return request({
            url: '/eduservice/front/pay/createPay?orderNo=' + orderNo,
            method: 'get'
        })
    }
}