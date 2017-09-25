$(function(){  
	$(window).bind("scroll", function(){ 
	    var top = $(this).scrollTop(); // 当前窗口的滚动距离
	    if(top>=100){
	    	// move();
	        $("header").css({"background-color":'white',"border-bottom":"1px solid #eee"});
	    	$("header").css({"margin-top":"0"});
	    	$(".name").css({"color":"black"});
	    	$(".navs a").css({"color":"black"});
	    	//hover样式
			$(".navs a").removeClass("nav");
			$(".navs a").addClass("nonav");
	    }
	    else{
	    	$("header").css({"margin-top":"36px","background-color":"#4183c4","border-bottom":"0px"});
	    	$(".name").css({"color":"white"});
	    	$(".navs a").css({"color":"#9acfea"});
	    	//hover样式
	    	$(".navs a").removeClass("nonav");
			$(".navs a").addClass("nav");
	    }
	});

})