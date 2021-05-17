const SimpleProgressWebpackPlugin = require('simple-progress-webpack-plugin')

module.exports = {
  configureWebpack: {
    plugins: [
      new SimpleProgressWebpackPlugin({
        format: 'minimal'
      })
    ]
  },
  devServer: {
    progress: false,
  },
  transpileDependencies: ['vuetify'],
}
