/** 这个文件只允许放表单验证方法 **/

//验证手机号
export function isPhone(rule, value, callback) {
    if (!value) {
        return callback(new Error('输入不可以为空'));
    }
    var pattern = /^1[34578]\d{9}$/
    if(pattern.test(value)){
        return callback()
    }
    return callback(new Error('输入的手机号错误'))
}
export function isInteger(rule, value, callback) {
    // if (!value) {
    //  return callback(new Error('输入不可以为空'));
    // }
    setTimeout(() => {
     if (!Number(value)) {
      callback(new Error('请输入正整数'));
     } else {
      const re = /^[0-9]*[1-9][0-9]*$/;
      const rsCheck = re.test(value);
      if (!rsCheck) {
       callback(new Error('请输入正整数'));
      } else {
       callback();
      }
     }
    }, 1000);
   }
    
