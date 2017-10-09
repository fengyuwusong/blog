/**
 * Created by fengyuwusong on 2017/10/9.
 */
var admin = {
    URL: {
        checkLogin: function () {
            return "/config/isLogin";
        }
    },
    checkLogin: function () {
        $.get(admin.URL.checkLogin(), {}, function (res) {
            //未登录跳转回来
            if (res.code === -7) {
                alert(res.message);
                location.href = "./login.html";
            }
        })
    },
    init: function () {
        //先检查是否已经登录
        admin.checkLogin();
    }
}