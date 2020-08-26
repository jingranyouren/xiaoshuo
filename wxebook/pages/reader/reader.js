// pages/reader/reader.js
const navigationBarHeight = (getApp().statusBarHeight + 44) + 'px'
var util = require('../../utils/util.js');
const { request, url_prefix } = require('../../request/request.js');
var touchDot = 0;//触摸时的原点 
var time = 0;// 时间记录，用于滑动时且时间小于1s则执行左右滑动 
var interval = "";// 记录/清理时间记录 
/**
* 计算总页数函数，需要理解行高---line-height和字体大小font-size之间的关系，可以查考http://www.jianshu.com/p/f1019737e155，以及http://www.w3school.com.cn/cssref/pr_dim_line-height.asp
* @param str 需要分页的内容
* @param fontSize 当前的字体大小
* @param lineHeight 当前的行高
* @param windowW 当前window的宽度
* @param windowH 当前window的高度
* @param pixelRatio 当前分辨率，用来将rpx转换成px
*/
function countPageNum(str, fontSize, lineHeight, windowW, windowH, pixelRatio) {
  var returnNum = 0;
  fontSize = fontSize;
  lineHeight = lineHeight;
  //将str根据‘\n’截成数组
  var strArray = str.split(/\n+/);
  var splitArray = [];//换行符的个数集合
  var reg = new RegExp('\n+', 'igm');
  var result = '';
  //这里写一个for循环去记录每处分隔符的\n的个数，这将会影响到计算换行的高度
  while ((result = reg.exec(str)) != null) {
    splitArray.push(result.toString().match(/\n/img).length);
  }
  var totalHeight = 0;
  strArray.forEach(function (item, index) {
    var wrapNum = 0;
    //splitArray的长度比strArray小1
    if (splitArray.length < index) {
      wrapNum = splitArray[index] - 1;
    }
    //Math.ceil向上取整
    totalHeight += Math.ceil(item.length / Math.floor((windowW - 120 / pixelRatio) / fontSize)) * lineHeight + wrapNum * lineHeight;

  });
  return Math.ceil(totalHeight / windowH) + 1;
}

/**---------------------------------------------------------------------------- */
Page({
  data: {
    navigationBarTitle: '标题',
    navigationBarHeight: navigationBarHeight,
    scroll_top: 0,
    scroll_height: 0,
    //serialNumber:1,//章节号，无用
    chapter: "这是章节名123",
    bookContentId:0, //章节id
    Text: "正文",
    Content:"",
    initFontSize: '14',
    initLineHeight: '18',
    colorArr: [{
      value: '#f7eee5',
      name: '米白',
      font: ''
    }, {
      value: '#e9dfc7',
      name: '纸张',
      font: '',
      id: "font_normal"
    }, {
      value: '#a4a4a4',
      name: '浅灰',
      font: ''
    }, {
      value: '#cdefce',
      name: '护眼',
      font: ''
    }, {
      value: '#283548',
      name: '灰蓝',
      font: '#7685a2',
      bottomcolor: '#fff'
    }, {
      value: '#0f1410',
      name: '夜间',
      font: '#4e534f',
      bottomcolor: 'rgba(255,255,255,0.7)',
      id: "font_night"
    }],
    nav: 'none',
    ziti: 'none',
    select_chapter: 'none',
    _num: 1,
    bodyColor: '#e9dfc7',
    daynight: false,
    lastnext: 'none',
    pageIndex:1,
    pageNum:1,
    windows: { windowsHeight: 0, windowsWidth: 0, pixelRatio: 1 },
    chapterArr:{},
    bookId:0
  },
  onReady: function () {
    
  },
  onLoad: function (options) {
    var that = this;
    //获取屏幕的高度和宽度，为分栏做准备
    wx.getSystemInfo({
      success: function (res) {
        //console.log(res);
        that.setData({
          windows: {
            windowsHeight: res.windowHeight - 68,
            windowsWidth: res.windowWidth,
            pixelRatio: res.pixelRatio,
            //scroll_height: res.windowHeight
          }
        });
      }
    }); 
    //获取阅读设置
    wx.getStorage({
      key: 'initFontSize',
      success: function (res) {
        that.setData({
          initFontSize: res.data
        })
      }
    });
    wx.getStorage({
      key: 'initLineHeight',
      success: function (res) {
        that.setData({
          initLineHeight: res.data
        })
      }
    });
    wx.getStorage({
      key: 'bodyColor',
      success: function (res) {
        that.setData({
          bodyColor: res.data
        })
      }
    });
    wx.getStorage({
      key: '_num',
      success: function (res) {
        that.setData({
          _num: res.data
        })
      }
    });
    wx.getStorage({
      key: 'daynight',
      success: function (res) {
        that.setData({
          daynight: res.data
        })
      }
    });
    //load文本数据接口
    //console.log(options);
    let {bookId} = options;
    this.setData({
      bookId
    })
    wx.getStorage({
      key: 'userinfo',
      success: (result)=>{
        const user_id = result.data.userId;
        that.initChapter(url_prefix+"/read/"+bookId+"/"+user_id,url_prefix+"/capter/"+bookId);
      }
    });
   
  },
  

  //字体变大
  bindBig: function () {
    var that = this;
    if (that.data.initFontSize > 19) {
      return;
    }
    var FontSize = parseInt(that.data.initFontSize)
    that.setData({
      initFontSize: FontSize += 1
    })
    wx.setStorage({
      key: "initFontSize",
      data: that.data.initFontSize
    })

    var Content = that.data.Content;
    let pageIndex = this.data.pageIndex;
    var pageNum = countPageNum(Content, parseInt(that.data.initFontSize), parseInt(that.data.initLineHeight), that.data.windows.windowsWidth, that.data.windows.windowsHeight, that.data.windows.pixelRatio);
    let Text = this.data.Content.substring((pageIndex -1)*Content.length/pageNum,pageIndex*Content.length/pageNum);

    that.setData({
      Text: Text,
      pageNum: pageNum
    });
  },
  //字体变小
  bindSmall: function () {
    var that = this;
    if (that.data.initFontSize < 11) {
      return;
    }
    var FontSize = parseInt(that.data.initFontSize)
    that.setData({
      initFontSize: FontSize -= 1
    })
    wx.setStorage({
      key: "initFontSize",
      data: that.data.initFontSize
    })
    var Content = that.data.Content;
    let pageIndex = this.data.pageIndex;
    var pageNum = countPageNum(Content, parseInt(that.data.initFontSize), parseInt(that.data.initLineHeight), that.data.windows.windowsWidth, that.data.windows.windowsHeight, that.data.windows.pixelRatio);
    let Text = this.data.Content.substring((pageIndex -1)*Content.length/pageNum,pageIndex*Content.length/pageNum);

    that.setData({
      Text: Text,
      pageNum: pageNum
    });
  },
  //行距变大
  bindAdd: function () {
    var that = this;
    if (that.data.initLineHeight > 24) {
      return;
    }
    var LineHeight = parseInt(that.data.initLineHeight)
    that.setData({
      initLineHeight: LineHeight += 1
    })
    wx.setStorage({
      key: "initLineHeight",
      data: that.data.initLineHeight
    })
    var Content = that.data.Content;
    let pageIndex = this.data.pageIndex;
    var pageNum = countPageNum(Content, parseInt(that.data.initFontSize), parseInt(that.data.initLineHeight), that.data.windows.windowsWidth, that.data.windows.windowsHeight, that.data.windows.pixelRatio);
    let Text = this.data.Content.substring((pageIndex -1)*Content.length/pageNum,pageIndex*Content.length/pageNum);

    that.setData({
      Text: Text,
      pageNum: pageNum
    });
  },
  //行距变小
  bindSub: function () {
    var that = this;
    if (that.data.initLineHeight < 16) {
      return;
    }
    var LineHeight = parseInt(that.data.initLineHeight)
    that.setData({
      initLineHeight: LineHeight -= 1
    })
    wx.setStorage({
      key: "initLineHeight",
      data: that.data.initLineHeight
    })
    var Content = that.data.Content;
    let pageIndex = this.data.pageIndex;
    var pageNum = countPageNum(Content, parseInt(that.data.initFontSize), parseInt(that.data.initLineHeight), that.data.windows.windowsWidth, that.data.windows.windowsHeight, that.data.windows.pixelRatio);
    let Text = this.data.Content.substring((pageIndex -1)*Content.length/pageNum,pageIndex*Content.length/pageNum);

    that.setData({
      Text: Text,
      pageNum: pageNum
    });
  },

  //点击中间区域显示底部导航
  midaction: function () {
    if (this.data.nav == 'none') {
      this.setData({
        nav: 'block',
        lastnext: 'block',
      })
    } else {
      this.setData({
        nav: 'none',
        ziti: 'none',
        select_chapter:'none',
        lastnext: 'none'
      })

    }
  },
  //点击字体出现窗口
  zitiaction: function () {
    if (this.data.ziti == 'none') {
      this.setData({
        ziti: 'block',
        lastnext: 'none',
        select_chapter:'none',
      })
    } else {
      this.setData({
        ziti: 'none',
        lastnext: 'block',
        select_chapter:'none',
      })
    }
  },
  //点击目录出现目录窗口
  chapteraction(e){
    if(this.data.select_chapter == 'none'){
      this.setData({
        ziti: 'none',
        lastnext: 'none',
        select_chapter:'block',
      })
    }else{
      this.setData({
        ziti: 'none',
        lastnext: 'none',
        select_chapter:'none',
      })
    }

  },
  //目录上一页
  select_pre:function(){
      let {chapterArr} = this.data;
      let curr = chapterArr.currentPage;
      if(curr <= 1){
        return;
      }
      curr -= 1;
      let {bookId} = this.data;
      request({url:url_prefix+"/capter/"+bookId+"?page="+curr})
      .then(result=>{
        if(result.data.code == 200){
          let chapterArr = result.data.data;
          this.setData({
            chapterArr
          })
        }
      })
  },
  //目录下一页
  select_next:function(){
    let {chapterArr} = this.data;
    let curr = chapterArr.currentPage;
    let allPage = chapterArr.allPage;
    if(curr >= allPage){
      return;
    }
    curr += 1;
    let {bookId} = this.data;
      request({url:url_prefix+"/capter/"+bookId+"?page="+curr})
      .then(result=>{
        if(result.data.code == 200){
          let chapterArr = result.data.data;
          this.setData({
            chapterArr
          })
        }
      })
  },
  //目录的章节选择
  select_chapter(e){
    //console.log(e);
    var that = this;
    let {selectitem} = e.currentTarget.dataset;
    that.requstContent(that,selectitem,1);
  },
  requstContent(that,bookContentId,pageIndex){
    request({url:url_prefix+"/reader/"+bookContentId})
    .then(result =>{
      if(result.data.code == 200){
        let Content = result.data.data.bookContent;
        let chapter = result.data.data.bookContentName;
        this.setData({
          Content,
          chapter,
          pageIndex:pageIndex,
          bookContentId
        })
        try {
          var content = that.data.Content;
          //console.log(content);
          content = content.replace("\n\n", "\n");
          //content = content.replace("&emsp;&emsp;", "&emsp;");
          var name = (that.data.chapter || '').trim();
          var pageNum = countPageNum(content, parseInt(that.data.initFontSize), parseInt(that.data.initLineHeight), that.data.windows.windowsWidth, that.data.windows.windowsHeight, that.data.windows.pixelRatio);
          //var width = that.data.windows.windowsWidth;
          //var leftValue = width * (pageindex - 1);
    
          //重新排版
          that.setData({
            Text: content.substring((pageIndex -1)*content.length/pageNum,pageIndex*content.length/pageNum),
            chapter: name,
            //serialNumber: parseInt(serialNumber),
            pageNum: pageNum,
          });
        } catch (e1) {
          console.log(e1);
        }
      }
    })
  },
  //选择背景色
  bgChange: function (e) {
    // console.log(e.target.dataset.num)
    // console.log(e)
    this.setData({
      _num: e.target.dataset.num,
      bodyColor: this.data.colorArr[e.target.dataset.num].value
    })
    wx.setStorage({
      key: "bodyColor",
      data: this.data.colorArr[e.target.dataset.num].value
    })
    wx.setStorage({
      key: "_num",
      data: e.target.dataset.num
    })
  },
  //切换白天夜晚
  dayNight: function () {
    if (this.data.daynight == true) {
      this.setData({
        daynight: false,
        bodyColor: '#e9dfc7',
        _num: 1
      })
      wx.setStorage({
        key: "bodyColor",
        data: '#e9dfc7'
      })
      wx.setStorage({
        key: "_num",
        data: 1
      })

    } else {
      this.setData({
        daynight: true,
        bodyColor: '#000',
        _num: 5
      })
      wx.setStorage({
        key: "bodyColor",
        data: '#000'
      })
      wx.setStorage({
        key: "_num",
        data: 5
      })
    }
    wx.setStorage({
      key: "daynight",
      data: this.data.daynight
    })
  },
  //滚动隐藏窗口
  scrollContain: function () {
    this.setData({
      nav: 'none',
      ziti: 'none',
      lastnext: 'none'
    })
  },
  //滚动到底部
  bindscrolltolower: function () {
    this.setData({
      lastnext: 'block',
    })
    //console.log("触发了滑动到底部事件");
  },
  



  //上一页下一页
  lastPage: function () {
    let pageIndex = this.data.pageIndex;
    //还有上一页
    if(pageIndex - 1 > 0){
      pageIndex -= 1;
      let pageNum = this.data.pageNum;
      let Content = this.data.Content;
      let Text = this.data.Content.substring((pageIndex -1)*Content.length/pageNum,pageIndex*Content.length/pageNum);
      this.setData({
         Text,
         pageIndex,
      })
      return ;
    }
    //没有上一页则返回上一章节最后一页
    let {bookContentId} = this.data;
    let preId = 0;
    let {chapterArr} = this.data;
    let arr = chapterArr.list;
    let len = arr.length;
    var pageNum = 1;
    //获取当前章节页的上一个章节id
    var i = 0;
    for(; i < len; i++){
        if(arr[i].bookContentId == bookContentId)
          break;
    } //不存在 i >= len 的情况 ，除非程序逻辑不严谨出现了不可预知的错误
    //当 i != 0 时；
    if(i - 1 >= 0){
      preId = arr[i-1].bookContentId;
      //获取上一章节的最后一页内容,确定总页数
      request({url:url_prefix+"/reader/"+preId})
      .then(result => {
        if(result.data.code == 200){
          let Content = result.data.data.bookContent;
          let content = Content.replace("\n\n", "\n");
          pageNum = countPageNum(content, parseInt(this.data.initFontSize), parseInt(this.data.initLineHeight), this.data.windows.windowsWidth, this.data.windows.windowsHeight, this.data.windows.pixelRatio);
          this.requstContent(this,preId,pageNum);
        }
      })
    }//当 i == 0 时，要将说或目录上一页中的最后一记录中的章节id
    else{
      
      let currentPage = chapterArr.currentPage;
      if(currentPage == 1) //目录没有上一页了退出
        return ;
      //this.select_pre();//获取目录上一页
      let {bookId} = this.data;
      request({url:url_prefix+"/capter/"+bookId+"?page="+(currentPage-1)})
      .then(result=>{
        if(result.data.code == 200){
          let chapterArr = result.data.data;
          this.setData({
            chapterArr
          })
          chapterArr = this.data.chapterArr;
          len = chapterArr.list.length;
          preId = chapterArr.list[len-1].bookContentId;
          request({url:url_prefix+"/reader/"+preId})
          .then(result => {
            if(result.data.code == 200){
              let Content = result.data.data.bookContent;
              let content = Content.replace("\n\n", "\n");
              pageNum = countPageNum(content, parseInt(this.data.initFontSize), parseInt(this.data.initLineHeight), this.data.windows.windowsWidth, this.data.windows.windowsHeight, this.data.windows.pixelRatio);
              this.requstContent(this,preId,pageNum);
            }
         })
        }
      })
      
    }
  },
  nextPage: function () {
    let pageIndex = this.data.pageIndex;
    if(pageIndex + 1 <= this.data.pageNum){
      pageIndex += 1;
      let pageNum = this.data.pageNum;
      let Content = this.data.Content;
      //console.log(Content.length);
      let Text = this.data.Content.substring((pageIndex -1)*Content.length/pageNum,pageIndex*Content.length/pageNum);
        this.setData({
          Text,
          pageIndex,
        })
      return ;
    }
    //没有下一页则返回下一章节第一页
    let {bookContentId} = this.data;
    let nextId = 0;
    let {chapterArr} = this.data;
    let arr = chapterArr.list;
    let len = arr.length;
    var pageNum = 1;
    //获取当前章节页的下一个章节id
    var i = 0;
    for(; i < len; i++){
        if(arr[i].bookContentId == bookContentId)
          break;
    } //不存在 i >= len 的情况 ，除非程序逻辑不严谨出现了不可预知的错误
    //当 i+1 < 0 时；
    if(i + 1 < len){
      nextId = arr[i+1].bookContentId;
      this.requstContent(this,nextId,pageNum);
    }//当 i+1 == len 时，要将获取目录的下一页
    else{
      let currentPage = chapterArr.currentPage;
      if(currentPage == chapterArr.allPage) //目录没有下一页了退出
        return ;
      //this.select_next();//获取目录下一页
      let {bookId} = this.data;
      request({url:url_prefix+"/capter/"+bookId+"?page="+(currentPage+1)})
      .then(result=>{
        if(result.data.code == 200){
          let chapterArr = result.data.data;
          this.setData({
            chapterArr
          })
          chapterArr = this.data.chapterArr;
          nextId = chapterArr.list[0].bookContentId;
          this.requstContent(this,nextId,pageNum);
        }
      })
      
    }
  },
  // 触摸开始事件 
  touchStart: function (e) {
    touchDot = e.touches[0].pageX; // 获取触摸时的原点 
  },
  // // 触摸移动事件 
  // touchMove: function (e) {
    
  //   //console.log("touchMove:" + touchMove + " touchDot:" + touchDot + " diff:" + (touchMove - touchDot));
    
  // },
  // 触摸结束事件 
  touchEnd: function (e) {
    //console.log(e);
    var touchMove = e.changedTouches[0].pageX;
    // 向左滑动  
    if (touchMove - touchDot <= -40 && time < 10) {
      this.nextPage();
    }
    // 向右滑动 
    if (touchMove - touchDot >= 40 && time < 10) {
      this.lastPage();
    }
  },

  //获取文章
  initChapter: function (url,capterUrl) {
    var that = this;
    var pageindex = 1;
    request({url:url}).then(result=>{
        if(result.data.code = 200){
          let Content = result.data.data.bookContent;
          let chapter = result.data.data.bookContentName;
          let navigationBarTitle = result.data.data.bookName;
          let bookContentId = result.data.data.bookContentId;
          this.initMULV(capterUrl+"?bookContentId="+result.data.data.bookContentId);
          that.setData({
            Content,
            chapter,
            navigationBarTitle,
            bookContentId
          });

          try {
            var content = that.data.Content;
            //console.log(content);
            content = content.replace("\n\n", "\n");
            //content = content.replace("&emsp;&emsp;", "&emsp;");
            var name = (that.data.chapter || '').trim();
            var pageNum = countPageNum(content, parseInt(that.data.initFontSize), parseInt(that.data.initLineHeight), that.data.windows.windowsWidth, that.data.windows.windowsHeight, that.data.windows.pixelRatio);
            //var width = that.data.windows.windowsWidth;
            //var leftValue = width * (pageindex - 1);
      
            //重新排版
            that.setData({
              Text: content.substring(0,content.length/pageNum),
              chapter: name,
              //serialNumber: parseInt(serialNumber),
              pageNum: pageNum,
              pageIndex: pageindex,
            });
          } catch (e) {
            console.log(e);
          }

        }
    })
    
  },
  initMULV(url){
    request({url:url}).then(result=>{
      if(result.data.code == 200){
        let chapterArr = result.data.data;
        this.setData({
          chapterArr
        })
      }
    })
  }
})
