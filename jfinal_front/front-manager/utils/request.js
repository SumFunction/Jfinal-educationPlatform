import axios from 'axios'
import Cookies from 'js-cookie'
import { Message, MessageBox } from 'element-ui'
// 创建axios实例
const service = axios.create({
    baseURL: process.env.BASE_URL, // api 的 base_url
    //baseURL: 'http://3743l214x0.qicp.vip', // api 的 base_url
    //baseURL: 'http://127.0.0.1:8001',
    timeout: 5000 // 请求超时时间
})

// http request 拦截器 发送请求时先会判断cookie里有没有token，有的话就放到请求头
service.interceptors.request.use(
    config => {
        //debugger
        if (Cookies.get('user_token')) {
            config.headers['token'] = Cookies.get('user_token');
        }
        return config
    },
    err => {
        return Promise.reject(err);
    }
)


// response 拦截器
service.interceptors.response.use(
    response => {
        /**
         * code为非20000是抛错 可结合自己业务进行修改
         */
        const res = response.data
        if (res.code === 20010) {
            Message({
                message: res.message,
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject('error')
        }
        else {
            return response.data
        }
    },
    error => {
        console.log('err' + error) // for debug
        Message({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        })
        return Promise.reject(error)
    }
)
export default service