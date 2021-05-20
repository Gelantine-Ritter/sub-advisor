const SimpleProgressWebpackPlugin = require('simple-progress-webpack-plugin')

module.exports = {
  configureWebpack: {
    devServer: {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, PUT, OPTIONS, DELETE',
        'Access-Control-Allow-Headers':
          'Access-Control-Allow-Methods, Access-Control-Allow-Origin, Origin, Accept, Content-Type',
        'Content-Type': 'application/json',
        Accept: 'application/json',
      },
      proxy: 'http://localhost:8080/',
      port: 3030,
      progress: false,
    },
    plugins: [
      new SimpleProgressWebpackPlugin({
        format: 'minimal',
      }),
    ],
  },

  transpileDependencies: ['vuetify'],
}
