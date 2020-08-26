
export const url_prefix = 'http://127.0.0.1:8080';

export const request =(params)=>{
    return new Promise((resolve,reject)=>{
       wx.request({
         ...params,
          success: (result)=>{
            resolve(result);
          },
          fail: (err)=>{
            reject(err);
          },
      });  
    })
}