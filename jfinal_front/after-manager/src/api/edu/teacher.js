import request from '@/utils/request'
//主要负责讲师模块的api数据请求
export default {
    getAllTeacher() {
        return request({
            url: `/eduservice/after/teacher/findAll`,
            method: 'get'
        })
    },
    getTeacherList(current, limit, teacherQuery) {
        return request({
            url: '/eduservice/after/teacher/findTeacherByPages/?current=' + current + '&limit=' + limit,
            method: 'post',
            data: teacherQuery
        })
    },
    removeDataById(id) {
        return request({
            url: `/eduservice/after/teacher/deleteTeacherById?id=` + id,
            method: 'get'
        })
    },
    addTeacher(teacher) {
        return request({
            url: '/eduservice/after/teacher/addTeacher',
            method: 'post',
            data: teacher
        })
    },
    queryById(id) {
        return request({
            url: `/eduservice/after/teacher/findById/?id=` + id,
            method: 'get',
        })
    },
    updateTeacher(teacher) {
        return request({
            url: `/eduservice/after/teacher/updateTeacher`,
            method: 'post',
            data: teacher
        })
    }
}