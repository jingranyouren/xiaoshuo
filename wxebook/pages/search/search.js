// pages/search/search.js
import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
     keyword:'',
     history:[],
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.getStorage({
      key: 'history',
      success: (result)=>{
        let history = result.data;
        this.setData({
          history
        })
      }
    });
  },
  setKeyword(e){
    console.log(e);
    let {keyword} = e.currentTarget.dataset;
    this.setData({
      keyword
    })
    this.getSearchBook(keyword);
  },
  getSearchBook(keyword){
    request({url:url_prefix+"/book/search",data:{keyword}})
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
  },
  getValue(e){
    //console.log(e);
    const keyword = e.detail.value;
    //console.log(keyword);
    let {history} = this.data;
    history.unshift({keyword});
    if(history.length >= 14){
      history.splice(13,1)
    }
    this.setData({
      history
    });
    wx.setStorage({key: 'history', data: history});
    this.getSearchBook(keyword);
  }
})