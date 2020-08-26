// pages/category/category.js
import {request} from '../../request/request.js'
import {url_prefix} from '../../request/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    arr:[],
    catls:[],
    status:[{
        id:-1,
        name:'全部',
        is_active:true
      },
      {
        id:0,
        name:'连载中',
        is_active:false
      },
      {
        id:1,
        name:'完结',
        is_active:false
      },
      {
        id:2,
        name:'免费',
        is_active:false
      }
    ],
    rootId:-1,
    categotyId:-1,
    updateStatus:-1,
    freeStatus:-1,
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getRootCategory();
    this.getCategory(-1,-1);
    this.getCateBook(-1,-1,-1,-1);
  },
  selectCategory(e){
    //console.log('');
    let {root} = e.currentTarget.dataset;
    if(root == '1'){
      let categotyId = e.detail.categoryid;
      //console.log(categotyId);
      let {arr} = this.data;
      arr.forEach(e => {
        if(e.id == categotyId){
          e.is_active = true;
        }else{
          e.is_active = false;
        }
      });
      let {status} = this.data;
      status.forEach(e=>{
        if(e.id == -1){
          e.is_active = true;
        }else{
          e.is_active = false;
        }
      })
      this.setData({
        arr,
        rootId:categotyId,
        categotyId:-1,
        updateStatus:-1,
        freeStatus:-1,
        status
      })
      //获取对应的分类数据
      this.getCategory(this.data.rootId,this.data.categotyId);
      this.getCateBook(this.data.rootId,this.data.categotyId,this.data.updateStatus,this.data.freeStatus);
    }else if(root == '2'){
      let categotyId = e.detail.categoryid;
      let {catls} = this.data;
      catls.forEach(e =>{
        if(e.id == categotyId){
          e.is_active = true;
        }else{
          e.is_active = false;
        }
      });
      this.setData({
        catls,
        categotyId: categotyId
      })
      this.getCateBook(this.data.rootId,this.data.categotyId,this.data.updateStatus,this.data.freeStatus);
    }else{

      let categotyId = e.detail.categoryid;
      let {status} = this.data;
      let {updateStatus} = this.data;
      let {freeStatus} = this.data;

      switch(categotyId){
        case -1:
          updateStatus = -1;
          freeStatus = -1;
          break;
        case 0:
          updateStatus = 0;
          freeStatus = -1;
          break;
        case 1:
          updateStatus = 1;
          freeStatus = -1;
          break;
        case 2:
          updateStatus = -1;
          freeStatus = 0;
          break;
      }
      status.forEach(e =>{
        if(e.id == categotyId){
          e.is_active = true;
        }else{
          e.is_active = false;
        }
      });
      this.setData({
        status,
        updateStatus,
        freeStatus
      })
      this.getCateBook(this.data.rootId,this.data.categotyId,this.data.updateStatus,this.data.freeStatus); 
    }
    
  },
  getRootCategory(){
    request({url:url_prefix+"/rootCategory"})
    .then(result=>{
       if(result.data.code == 200){
          let arr = result.data.data;
          this.setData({
            arr
          })
       }
    })
  },
  getCategory(rootId,categoryId){
    let category = {rootId,categoryId};
    request({url:url_prefix+"/category",data:category})
    .then(result=>{
      if(result.data.code == 200){
        let catls = result.data.data;
        this.setData({
          catls
        })
      }
    })
  },
  getCateBook(rootId,categotyId,updateStatus,freeStatus){
    request({url:url_prefix+"/category/book?rootId="+rootId,data:{categotyId,updateStatus,freeStatus},method:'POST'})
    .then(result =>{
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