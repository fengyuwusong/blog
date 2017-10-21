/**
 * setting设置
 * Created by fengyuwusong on 2017/10/18.
 */

var setting = {
    URL: {
        getConfig: function () {
            return "/config/admin";
        },
        update: function () {
            return "/config"
        }
    },
    getConfig: function () {
        $.get(setting.URL.getConfig(), {}, function (res) {
            $("#blogName").attr('placeholder', res.data.blogName);
            $("#adminName").attr('placeholder', res.data.adminName);
            $("#adminPw").attr('placeholder', res.data.adminPw);
            $("#footer").attr('placeholder', res.data.footer);
            $("#git").attr('placeholder', res.data.git);
            $("#gitUrl").attr('placeholder', res.data.blogName);
            $("#address").attr('placeholder', res.data.address);
            $("#title").attr('placeholder', res.data.title);
            $("#nav1Name").val(res.data.nav1Name);
            $("#nav1Url").val(res.data.nav1Url);
            $("#nav2Name").val(res.data.nav2Name);
            $("#nav2Url").val(res.data.nav2Url);
            $("#nav3Name").val(res.data.nav3Name);
            $("#nav3Url").val(res.data.nav3Url);
            $("#nav4Name").val(res.data.nav4Name);
            $("#nav4Url").val(res.data.nav4Url);
            $("#nav5Name").val(res.data.nav5Name);
            $("#nav5Url").val(res.data.nav5Url);
        });
    },

    getInputConfig: function () {
        const config = {
            blogName: $("#blogName").val() === "" ? null : $("#blogName").val(),
            adminName: $("#adminName").val() === "" ? null : $("#adminName").val(),
            adminPw: $("#adminPw").val() === "" ? null : $("#adminPw").val(),
            footer: $("#footer").val() === "" ? null : $("#footer").val(),
            git: $("#git").val() === "" ? null : $("#git").val(),
            gitUrl: $("#gitUrl").val() === "" ? null : $("#gitUrl").val(),
            address: $("#address").val() === "" ? null : $("#address").val(),
            title: $("#title").val() === "" ? null : $("#title").val(),
            nav1Name: $("#nav1Name").val() === "" ? null : $("#nav1Name").val(),
            nav1Url: $("#nav1Url").val() === "" ? null : $("#nav1Url").val(),
            nav2Name: $("#nav2Name").val() === "" ? null : $("#nav2Name").val(),
            nav2Url: $("#nav2Url").val() === "" ? null : $("#nav2Url").val(),
            nav3Name: $("#nav3Name").val() === "" ? null : $("#nav3Name").val(),
            nav3Url: $("#nav3Url").val() === "" ? null : $("#nav3Url").val(),
            nav4Name: $("#nav4Name").val() === "" ? null : $("#nav4Name").val(),
            nav4Url: $("#nav4Url").val() === "" ? null : $("#nav4Url").val(),
            nav5Name: $("#nav5Name").val() === "" ? null : $("#nav5Name").val(),
            nav5Url: $("#nav5Url").val() === "" ? null : $("#nav5Url").val(),
        };
        return JSON.stringify(config);
    },

    //提交更改
    update: function () {
        $.ajax({
            type: "PUT",
            url: setting.URL.update(),
            contentType: "application/json; charset=utf-8",
            data: setting.getInputConfig(),
            dataType: "json",
            success: function (res) {
                if (res.code === 200) {
                    alert("更新完成");
                    location.reload();
                } else {
                    alert(res.message);
                }
            },
            error: function () {

            }
        });
    },

    init: function () {
        setting.getConfig();

        $("#submit").on("click", function (e) {
            setting.update();
            // console.log($("#blogName").val() === "" ? null : $("#blogName").val());
        })
    }

};
