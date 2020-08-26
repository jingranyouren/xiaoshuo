// Compoments/list/list.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    list:{
      type:Array,
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
    selectedItem(e){
      //console.log(e);
      let {bookid} = e.currentTarget.dataset;
      //通过事件传递索引给父组件
      this.triggerEvent("handerItemSelected",{bookid});
    }
  }
})
