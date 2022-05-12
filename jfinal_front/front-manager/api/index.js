import request from '@/utils/request'
export default {
    getAllCourseAndTeacher() {
        return request({
            url: '/eduservice/front/findIndexCourseAndTeacher',
            method: 'get'
        })
    }
}