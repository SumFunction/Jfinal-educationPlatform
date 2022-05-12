import request from '@/utils/request'
//主要负责讲师模块的api数据请求
export default {
    getSubjectList() {
        return request({
            url: `/eduservice/after/subject/listSubject`,
            method: 'get'
        })
    }
}