const SimpleProgressWebpackPlugin = require('simple-progress-webpack-plugin')

module.exports = {
  configureWebpack:{
    devServer: {
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
