//index.js

import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'

//获取应用实例
const app = getApp()

Page({
  data: {
    tabs:[
      {
        id: 0,
        name: '男生小说',
        is_active:true,
      },
      {
        id: 1,
        name: '女生小说',
        is_active:false,
      }
    ],
    swiperList:[],  //轮播图
    boy_carousel_list:[],
    girl_carousel_list:[],
    //首页导航栏
    navList:[{
      id:0,
      name:'分类',
      url:'/pages/category/category',
      img:url_prefix+"/img/1.jpg"
    },
    {
      id:1,
      name:'免费',
      url:'/pages/free/free',
      img:url_prefix+"/img/2.jpg"
    },
    {
      id:2,
      name:'完本',
      url:'/pages/finally/finally',
      img:url_prefix+"/img/3.jpg"
    },
    {
      id:3,
      name:'活动',
      url:'/pages/activity/activity',
      img:url_prefix+"/img/4.jpg"
    }
    //
    ],
    indexItem:[],
    
  },
  onLoad: function(options) {
    // 页面创建时执行
    this.getCarousel();
    this.getIndexItem('男');
  },

  getCarousel(){
      //获取男生小说轮播图列表
    request({url:url_prefix+'/carousel',data:{sex:'男'}})
      .then(result=>{
      //  console.log(result);
        if(result.data.code == 200){
          const arr = result.data.data;
          this.setData({
            swiperList:arr, //初始值为男生小说的
            boy_carousel_list:arr    
          });
        }
      })
     //获取女生小说轮播图列表
    request({url:url_prefix+'/carousel',data:{sex:'女'}})
      .then(result=>{
      //  console.log(result);
      if(result.data.code == 200){
        const arr = result.data.data;
        this.setData({
          girl_carousel_list:arr
        });
      }
      })
  },

  getIndexItem(sex){
    request({url:url_prefix+'/indexconfig',data:{'sex':sex}})
      .then(result=>{
       //console.log(result);
        if(result.data.code == 200){
          const arr = result.data.data;
          this.setData({
            indexItem:arr, //初始值为男生小说的
          });
        }
      })
  },

  handerItemChange(e){
    // console.log(e);
    const index = e.detail.index;
    
    let {tabs} = this.data;
    let {boy_carousel_list} = this.data;
    let {girl_carousel_list} = this.data;

    tabs.forEach((v,i) => {
       i === index ? v.is_active = true : v.is_active = false;
    });
    this.setData({
      tabs
    })
    if(index == 0){
      this.setData({
        swiperList:boy_carousel_list
      });
      this.getIndexItem('男');
      //console.log(this.data.indexItem);
    }
    else{
      this.setData({
        swiperList:girl_carousel_list
      });
      this.getIndexItem('女');
      //console.log(this.data.indexItem);
    }
    
  }
})
