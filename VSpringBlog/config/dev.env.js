'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // BASE_API: '"https://iazuresky.com:8443/"',
  BASE_API: '"http://localhost:8081/"',
  MOVIE_API: '"https://api.douban.com/v2/movie/top250"',
  USER_API: '"http://localhost:3000/"'
})
