'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:8001"',
  OSS_PATH: '"https://guli-subjecttemplete.oss-cn-beijing.aliyuncs.com/%E5%88%86%E7%B1%BB%E6%B5%8B%E8%AF%95.xlsx"'
})
