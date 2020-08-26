// pages/mine/mine.js
import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
    hasInfo:false
  },
  handGetUserInfo(e){
      
      // console.log(e);

      let {nickName} = e.detail.userInfo;
      let sex =  e.detail.userInfo.gender === 0 ? '男' : '女';
      let {avatarUrl} =  e.detail.userInfo;
      request({url:url_prefix+'/user/login',data:{nickName,sex,avatarUrl},method: 'POST'})
        .then(result =>{
          //console.log(result);
          if(result.data.code == 300){
                request({url:url_prefix+'/user/register',data:{nickName,sex,avatarUrl},method:'POST',dataType:'json'})
                .then(result =>{
                    let userInfo = result.data.data;
                    //console.log(userInfo);
                    wx.setStorage({
                          key: 'userinfo',
                          data: userInfo
                    });
                    this.setData({
                      hasInfo:true,
                      userInfo
                    })
                })
          }else if(result.data.code == 200){
              let userInfo = result.data.data;
              //console.log(userInfo);
              wx.setStorage({
                key: 'userinfo',
                data: userInfo
               });
              this.setData({
                 hasInfo:true,
                userInfo
              })
          }
        }).catch(res => {
          //报错
          wx.removeStorage({key:'userinfo'});
            this.setData({
              hasInfo:false,
              userInfo:{}
            })
        })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      wx.getStorage({
        key: 'userinfo',
        success: (result)=>{
          let userInfo = result.data;
          this.setData({
            userInfo,
            hasInfo:true
          })
        }
      });
  },

  
})