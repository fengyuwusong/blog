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
    text: null,
    windows: [0],
    flag: null,
    last: null,
    click: function (index) {
//         console.log(index);
        if (index == 0) {
            $("iframe").attr("src", "list.html");
        } else if (index == 1) {
            $("iframe").attr("src", "edit.html");
        } else if (index == 2) {
            $("iframe").attr("src", "classify.html");
        } else if (index == 3) {
            $("iframe").attr("src", "tags.html");
        } else if (index == 4) {
            $("iframe").attr("src", "setting.html");
        } else {
            $("iframe").attr("src", "navSetting.html");
        }
        //上方窗口显示
        admin.flag = ($.inArray(index, admin.windows));
        if (admin.flag == -1) {
            admin.text = $(".a" + index).text();
            $("#top").append("<div class='window on window" + index + "' data-nav='" + index + "'>" + admin.text + "&nbsp;<i class='fa fa-times-circle fa-1x' data-nav=" + index + "></i></div>");
            // console.log( $(".window"+index).siblings());
            $(".window" + index).siblings().removeClass("on");
            admin.windows.push(index);
        } else {
            $(".window" + index).addClass("on");
            $(".window" + index).siblings().removeClass("on");
        }
    },

//点击上边
    changeStyle: function (index) {
        // console.log(index);
        if (index == 0) {
            $("iframe").attr("src", "list.html");
        } else if (index == 1) {
            $("iframe").attr("src", "edit.html");
        } else if (index == 2) {
            $("iframe").attr("src", "classify.html");
        } else if (index == 3) {
            $("iframe").attr("src", "tags.html");
        } else if (index == 4) {
            $("iframe").attr("src", "setting.html");
        } else {
            $("iframe").attr("src", "navSetting.html");
        }
        $(".window" + index).addClass("on");
        $(".window" + index).siblings().removeClass("on");
    },

//点击×
    dele: function (index) {
        //删除这个window
        $(".window" + index).remove();
        admin.flag = ($.inArray(index, admin.windows));
        admin.windows.splice(admin.flag, 1);
        admin.last = admin.windows[admin.windows.length - 1];
        admin.changeStyle(admin.last);
        $(".window" + index).unbind();
        // alert(windows);
        // admin.stopBubbling(index);

    },
    //点击×时不会触发父类,被下面的i的点击事件的 return false代替，阻止父元素的点击事件;

    // stopBubbling: function (e) {
    //     e = window.event || e;
    //     if (e.stopPropagation) {
    //         e.stopPropagation();      //阻止事件 冒泡传播
    //     } else {
    //         e.cancelBubble = true;   //ie兼容
    //     }
    // },


    init: function () {
        //先检查是否已经登录
        admin.checkLogin();


        //    切换导航绑定
        $(".sec-nav").on("click", function (e) {
            //获得data-nav的值并调用click方法
            // console.log( $(this).data("nav"));
            admin.click($(this).data("nav"));
        })

        //添加元素无法触发绑定的事件的解决方案 $(父元素).on("click","这个元素",function(){})
        $("#top").on("click", ".window", function (e) {
            //获得data-nav的值并调用click方法
            // console.log( $(this).data("nav"));
            admin.changeStyle($(this).data("nav"));
        })

        //删除窗口
        $("#top").on("click", ".fa-times-circle", function (e) {
            admin.dele($(this).data("nav"));
            return false;
            // admin.stopBubbling($(this).data("nav"));
        })
    }
}