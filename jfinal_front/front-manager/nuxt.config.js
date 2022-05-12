
module.exports = {
  env: {
    BASE_URL: process.env.BASE_URL,
    NODE_ENV: process.env.NODE_ENV
  },
  plugins: [
    {

      src: '~/plugins/element-ui',
      ssr: false
    },
    {
      src: '~/plugins/nuxt-swiper-plugin.js',
      ssr: false
    }
  ],
  css: [
    'swiper/dist/css/swiper.css',
    'element-ui/lib/theme-chalk/index.css'
  ],
  /*
  ** Headers of the page
  */
  head: {
    title: '农课宝',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '{{escape description }}' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    extend(config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}

