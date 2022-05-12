import request from '@/utils/request'
export default {
    addCollect(courseId) {
        return request({
            url: '/eduservice/front/collect/addCollect?courseId=' + courseId,
            method: 'get',
        })
    },
    listIfCollect(courseId) {
        return request({
            url: '/eduservice/front/collect/listIfCollect?courseId=' + courseId,
            method: 'get',
        })
    },
    cancelCollect(courseId) {
        return request({
            url: '/eduservice/front/collect/cancelCollect?courseId=' + courseId,
            method: 'get',
        })
    },
    getCollectList(current, limit) {
        return request({
            url: '/eduservice/front/collect/findCollectByPages?current=' + current + '&limit=' + limit,
            method: 'get'
        })
    },
}