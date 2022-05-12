import request from '@/utils/request'
export default {
    isBuy(courseId) {
        return request({
            url: '/eduservice/front/order/isBuy?courseId=' + courseId,
            method: 'get'
        })
    },
    createOrder(courseId) {
        return request({
            url: '/eduservice/front/order/createOrder?courseId=' + courseId,
            method: 'get'
        })
    },
    findOrderInfoByOrderNo(orderNo) {
        return request({
            url: '/eduservice/front/order/findOrderInfoByOrderNo?orderNo=' + orderNo,
            method: 'get'
        })
    }
}