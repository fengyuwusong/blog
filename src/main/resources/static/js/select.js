$(function () {

    var height = $(window).height();
    // alert(height);
    $(".content").height(height - $("header").height() - 2 + "px");
    $("iframe").height(height - $(".top").height() + "px");
    var windows = $(".window");
    // console.log(windows);


})