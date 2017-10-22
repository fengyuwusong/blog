$(function () {
    var right = $('.middle .right');
    var right_top = right.offset().top;
    var header_height = $("header").height();
    var right_right = screen.width - right.offset().left - right.width() - 20;

    $(".toTop").css({"right": screen.width - right.offset().left - 90 + "px"});

    $(window).bind("scroll", function () {
        var top = $(this).scrollTop(); // 当前窗口的滚动距离
        if (top >= 100) {
            // move();
            $("header").css({"background-color": 'white', "border-bottom": "1px solid #eee"});
            $("header").css({"margin-top": "0"});
            $(".name").css({"color": "black"});
            $(".navs a").css({"color": "black"});
            //hover样式
            $(".navs a").removeClass("nav");
            $(".navs a").addClass("nonav");
        }
        else {
            $("header").css({"margin-top": "36px", "background-color": "#4183c4", "border-bottom": "0px"});
            $(".name").css({"color": "white"});
            $(".navs a").css({"color": "#9acfea"});
            //hover样式
            $(".navs a").removeClass("nonav");
            $(".navs a").addClass("nav");
        }
        // 右侧
        if (top >= right_top) {
            $(".middle .right").css({"position": "fixed", "margin-top": "-396px", "right": right_right + "px"});
            $(".toTop").css({"display": "block"});
        } else {
            $(".middle .right").css({"position": "", "margin-top": "12px", "right": ""});
            $(".toTop").css({"display": "none"});
        }
    });
    //回到顶部
    $(".toTop").on("click", function () {
        $("html,body").animate({scrollTop: 0}, 300);
        return false;
    });


})