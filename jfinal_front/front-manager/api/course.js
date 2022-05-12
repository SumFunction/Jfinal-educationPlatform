import request from '@/utils/request'
export default {
    getCourseList(current, limit, queryCourse) {
        return request({
            url: '/eduservice/front/course/findCourseByPages?current=' + current + '&limit=' + limit,
            method: 'post',
            data: queryCourse
        })
    },
    findCourseByTeacherId(id) {
        return request({
            url: '/eduservice/front/teacher/queryCourseById?id=' + id,
            method: 'get'
        })
    },
    getSubjectList() {//查询课程所有类别
        return request({
            url: '/eduservice/front/course/listSubject',
            method: 'get'
        })
    },
    getCourseDetail(id) {
        return request({
            url: '/eduservice/front/course/findCourseDetailById?courseId=' + id,
            method: 'get'
        })
    },
    searchByCourseName(current, limit, title) {
        return request({
            url: '/eduservice/front/course/searchByCourseName?current=' + current + '&limit=' + limit + '&title=' + title,
            method: 'get'
        })
    }
}