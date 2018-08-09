/**
 * 登陆服务层
 * @Author Giovani
 * @Create: 2018/8/9 14:46
 */
app.service('loginService', function ($http) {

    //读取登录人名称
    this.loginName = function () {
        return $http.get('../login/name.do');
    }

});