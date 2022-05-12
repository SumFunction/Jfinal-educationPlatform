import request from '@/utils/request'
//主要负责讲师模块的api数据请求
/*
章节模块的api请求方法
*/
export default {
    findByPages(current, limit, aut) {
        return request({
            url: `/eduservice/after/authrity/findByPages?current=` + current + `&limit=` + limit + `&aut=` + aut,
            method: 'get'
        })
    },
    getAllMember() {
        return request({
            url: `/eduservice/after/authrity/getAllMember`,
            method: 'get'
        })
    },
    deleteMemberById(id) {
        return request({
            url: `/eduservice/after/authrity/deleteMemberById?id=` + id,
            method: 'get'
        })
    },
    updateMember(user) {
        return request({
            url: `/eduservice/after/authrity/updateMember`,
            method: 'post',
            data: user
        })
    },
    getMemberInfoById(id) {
        return request({
            url: `/eduservice/after/authrity/getMemberInfoById?id=` + id,
            method: 'get',
        })
    },
    addMember(user) {
        return request({
            url: `/eduservice/after/authrity/addMember`,
            method: 'post',
            data: user
        })
    }
}