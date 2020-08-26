// Compoments/Tabs/Tabs.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    //接收属性
      tabs:{
          //类型
          type:Array,
          //默认值
          value:[]
      }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    handTitleItem(e){
      // console.log(e);
      let {index} = e.currentTarget.dataset;

      //通过事件传递索引给父组件
      this.triggerEvent("itemChange",{index});
    }
  }
})
