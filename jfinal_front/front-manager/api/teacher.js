import request from '@/utils/request'
export default {
    getTeacherList(current, limit) {
        return request({
            url: '/eduservice/front/teacher/findTeacherByPages?current=' + current + '&limit=' + limit,
            method: 'post'
        })
    },
    queryById(id) {
        return request({
            method: 'get',
            url: `/eduservice/front/teacher/findById/?id=` + id

        })
    }
}