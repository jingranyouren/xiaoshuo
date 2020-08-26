// pages/book/book.js

import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
      bookShell:[{
        id:0,
        name:'我的书架',
        is_active:true,
      },
      {
        id:0,
        name:'阅读历史',
        is_active:false,
      }
    ],
    add_book:{
       url: url_prefix +'/img/122.jpg',
       path:'/pages/index/index'
    },
    contentData:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getBook('/bookShell');
  },
  handerItemChange(e){
    //console.log(e);
    let {index} = e.detail;
    let {bookShell} = this.data;

    bookShell.forEach((v,i) => {
       index === i ? v.is_active = true : v.is_active = false;
    });
    this.setData({
      bookShell
    });
    // console.log(index);
    if(index == 0)
      this.getBook('/bookShell');
    else
      this.getBook('/readNotes');
  },
  getBook(path){

    wx.getStorage({
      key: 'userinfo',
      success: (result)=>{
        // console.log(result);
        // console.log(111)
        request({url:url_prefix+path+'/'+result.data.userId})
        .then(result=>{
          // console.log(result);
          if(result.data.code = 200){
            let list = result.data.data;
            this.setData({
              contentData:list
            })
          }
        })
      },
    });

 
  }

})