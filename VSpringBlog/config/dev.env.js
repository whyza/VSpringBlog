'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"https://iazuresky.com:8443/"',
  // BASE_API: '"http://localhost:8081/"',
  DEV_API: '"https://iazuresky.com:8443/"'
})
