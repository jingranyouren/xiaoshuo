// pages/bookinfo/bookinfo.js

import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
      bookInfo:{},
      bookId:0,
      userId:-1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //console.log(options);
    let {bookId} = options;
    this.setData({
      bookId
    })
    wx.getStorage({
      key: 'userinfo',
      success: (result)=>{
        const user_id = result.data.userId;
        this.setData({
          userId:user_id
        })
        this.getBookInfo(user_id,bookId)
      },
      fail: ()=>{
        wx.switchTab({url: '/pages/mine/mine'});
      }
    });
  },
  getBookInfo(userId,bookId){
    request({url:url_prefix+"/bookInfo/"+bookId,data:{'userId':userId}})
    .then(result=>{
        if(result.data.code == 200){
          let bookInfo = result.data.data;
          this.setData({
            bookInfo
          })
        }
    })
  },
  readerBook(e){
    let {bookId} = this.data;
    //wx.navigateTo("/Pages/reader/reader?bookId="+bookId);
    wx.navigateTo({url: '/pages/reader/reader?bookId='+bookId});
  },
  addShell(){
    let{bookId} = this.data;
    let {userId} = this.data;
    request({url:url_prefix+"/bookShell/add",
            data:{bookId,userId},
            method:'POST',
          dataType:'json'})
          .then(result =>{
              if(result.data.code == 200){
                let {bookInfo} = this.data;
                bookInfo.isInShell = true;
                this.setData({
                  bookInfo
                })
              }
          })
  },
  removeShell(){
    let{bookId} = this.data;
    let {userId} = this.data;
    request({url:url_prefix+"/bookShell/remove",
            data:{bookId,userId},
            method:'POST',
          dataType:'json'})
          .then(result =>{
              if(result.data.code == 200){
                let {bookInfo} = this.data;
                bookInfo.isInShell = false;
                this.setData({
                  bookInfo
                })
              }
          })
  }

 
})