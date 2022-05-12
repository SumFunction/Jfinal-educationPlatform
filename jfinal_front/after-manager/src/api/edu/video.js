import request from '@/utils/request'
//主要负责讲师模块的api数据请求
/*
小节模块的api请求方法
*/
export default {
    addVideo(data) {
        return request({
            url: `/eduservice/eduVideo/addVideo`,
            method: 'post',
            data: data
        })
    },
    deleteVideoById(id) {//注意 这个api是删除数据库的所有视频信息api
        return request({
            url: `/eduservice/eduVideo/deleleVideoById?id=` + id,
            method: 'get'
        })
    },
    //这个api只是删除云端视频
    deleteVodById(id) {
        return request({
            url: `/eduvod/deleteVodById?id=` + id,
            method: 'get'
        })
    },
    updateVideo(data) {
        return request({
            url: `/eduservice/eduVideo/updateVideoById`,
            method: 'post',
            data: data
        })
    },
    findVideoById(id) {
        return request({
            url: `/eduservice/eduVideo/findVideoById?id=` + id,
            method: 'get'
        })
    },
    send() {
        return request({
            url: `/eduvod/testError`,
            method: 'get'
        })
    }
}