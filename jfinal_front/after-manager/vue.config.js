module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8001',
                pathRewrite: { '^/api': '' },
                ws: true,
                changeOrigin: true
            }
        }
    },
    lintOnSave: false,
    publicPath: './',
    productionSourceMap: false,

}