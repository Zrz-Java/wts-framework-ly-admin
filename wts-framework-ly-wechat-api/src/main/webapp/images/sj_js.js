(function(win,doc){
		var fresh = function(){
			var html = doc.documentElement;
			var w = html.clientWidth;
			if(w>750){
				html.style.fontSize = (750/10)+'px';
			}
			else{
				html.style.fontSize = (w/10)+'px';
			}
			$(".tu").height($(".tu img").height());
		}
		if(document.State === "complete"){
				fresh();
		}
		else{
			document.addEventListener( "DOMContentLoaded", fresh, false );
		}
		win.addEventListener('resize', fresh, false)
	})(window,document)
function sx_con(){
	$(".bg1,.con1").animate({"left":0},500);
}
$(".icon_l").click(function(){
	$(".bg1,.con1").animate({"left":"100%"},500);
})

$(".tit_hov li").hover(function(){
	var index=$(this).index();
		$(this).addClass("on").siblings("li").removeClass("on");
		$(this).parents(".tit_hov").next().children("[tid="+index+"]").stop(true,true).addClass("block").siblings().removeClass("block");
})
$(".con").click(function(){
	$(this).addClass("on").siblings().removeClass("on");
	$(this).siblings().find(".con3").slideUp();
	$(this).find(".con3").slideDown();
})
$(".return").click(function(){
	history.go(-1);
})
