const app = getApp()

Component({

  properties: {
    text: {
      type: String,
      value: 'Wechat'
    },
    url: {
      type: String,
      value: ' '
    },
    pageIndex:{
      type: Number,
      value: ' '
    },
    back: {
      type: Boolean,
      value: false
    },
    home: {
      type: Boolean,
      value: false
    },
    read: {
      type: Boolean,
      value: false
    },
    tag: {
      type: Boolean,
      value: false
    }
  },

  data: {
    statusBarHeight: app.statusBarHeight + 'px',
    navigationBarHeight: (app.statusBarHeight + 44) + 'px'
  },

  methods: {
    backHome: function () {
      wx.reLaunch({
        url: '../../pages/index/index',
      })
    },
    back: function () {
      wx.navigateBack({
        delta: 1
      })
    },
    readMode: function (options) {
      wx.reLaunch({
        url: '../../pages/reader/lilReader',
      })
    },
    insertTag: function () {
      wx.navigateBack({
        delta: 1
      })
    }
  }
})
