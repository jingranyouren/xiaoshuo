// pages/finally/finally.js
import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    request({url:url_prefix+'/book/finish'})
    .then(result=>{
      if(result.data.code == 200){
        let list = result.data.data;
        this.setData({
          list
        })
      }
    })
  },
  handerItemSelected(e){
    // console.log(e);
     let {bookid} = e.detail;
     wx.navigateTo({url:'/pages/bookinfo/bookinfo?bookId='+bookid});
  }
})