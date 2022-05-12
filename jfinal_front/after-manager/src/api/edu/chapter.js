import request from '@/utils/request'
//主要负责讲师模块的api数据请求
/*
章节模块的api请求方法
*/
export default {
    findChapter(id) {
        return request({
            url: `/eduservice/after/eduChapter/findAllChapterAndVideoById?id=` + id,
            method: 'get'
        })
    },
    getChapterInfo(id) {
        return request({
            url: `/eduservice/after/eduChapter/getChapterInfoById?id=` + id,
            method: 'get'
        })
    },
    addChapter(data) {
        return request({
            url: `/eduservice/after/eduChapter/addChapter`,
            method: 'post',
            data: data
        })
    },
    deleteChapter(id) {
        return request({
            url: `/eduservice/after/eduChapter/deleteChapterById?id=` + id,
            method: 'get'
        })
    },
    updateChapter(data) {
        return request({
            url: `/eduservice/after/eduChapter/updateChapter`,
            method: 'post',
            data: data
        })
    }
}