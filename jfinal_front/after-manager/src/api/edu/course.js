import request from '@/utils/request'
//主要负责讲师模块的api数据请求
export default {
    addCourseInformation(data) {
        return request({
            url: `/eduservice/after/eduCourse/addCourse`,
            method: 'post',
            data: data
        })
    },
    updateCourseInformation(data) {
        return request({
            url: `/eduservice/after/eduCourse/updateCourse`,
            method: 'post',
            data: data
        })
    },
    findCourseById(id) {
        return request({
            url: `/eduservice/after/eduCourse/findCourseById?id=` + id,
            method: 'get'
        })
    },
    findCourseByPages(current, limit, courseQuery) {
        return request({
            url: '/eduservice/after/eduCourse/findCourseByPages?current=' + current + '&limit=' + limit,
            method: 'post',
            data: courseQuery
        })
    },
    removeCourseById(id) {
        return request({
            url: '/eduservice/after/eduCourse/deleteCourseById?id=' + id,
            method: 'get'
        })
    },
    getPublishCourse(id) {
        return request({
            url: `/eduservice/after/eduCourse/getPublishCourseInfo?id=` + id,
            method: 'get'
        })
    },
    publishCourse(id) {
        return request({
            url: `/eduservice/after/eduCourse/publishCourse?id=` + id,
            method: 'get'
        })
    }
}