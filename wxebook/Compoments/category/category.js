// Compoments/category/category.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    //属性名称
    arr:{
      //类型
      type:Array,
      //属性
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
    handerChangeItem(e){
      // console.log(e.currentTarget.dataset);
      let {categoryid} = e.currentTarget.dataset;
      this.triggerEvent("selectCategory",{categoryid});
    }
  }
})
