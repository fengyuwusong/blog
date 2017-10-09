/**
 * 登录验证js
 * Created by fengyuwusong on 2017/10/9.
 */

var login = {
    URL: {
        login: function () {
            return "/config";
        }
    },
    init: function () {
        //绑定登录事件
        $("#submit").on("click", function (e) {
            $("#submit").attr('disabled', true);
            e.preventDefault();
            $.post(login.URL.login(), {adminName: $("#name").val(), adminPw: $("#pw").val()}, function (res) {
                $("#submit").attr('disabled', false);
                if (res.code === 200) {
                    location.href = "./admin.html";
                } else {
                    alert(res.message);
                }
            })
        });
    }
};
