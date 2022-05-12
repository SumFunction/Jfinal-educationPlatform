import request from '@/utils/request'
//主要负责讲师模块的api数据请求
export default {
    getCourseSell() {
        return request({
            url: `/eduservice/after/eduStatic/getCourseSell`,
            method: 'get'
        })
    }
}