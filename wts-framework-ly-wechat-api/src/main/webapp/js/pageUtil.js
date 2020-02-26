<!--
/*

showPages v1.1
=================================

Infomation
----------------------
Author : Lapuasi
E-Mail : lapuasi@gmail.com
Web : <a href="http://www.lapuasi.com" target="_blank">http://www.lapuasi.com</a>
Date : 2005-11-17


Example
----------------------
var pg = new showPages('pg');
pg.pageCount = 12; //定义总页数(必要)
pg.argName = 'p';    //定义参数名(可选,缺省为page)
pg.printHtml();        //显示页数


Supported in Internet Explorer, Mozilla Firefox
*/

function showPages(_docCount, _nPageCount, _nCurrIndex, _sPageName, _sPageExt, _pageSize, _urlType) { //初始化属性
	this.name = "pg";      //对象名称
	this.page = parseInt(_nCurrIndex, 10) || 1;         //当前页数
	this.pageCount = parseInt(_nPageCount, 10) || 1;    //总页数
	this.argName = 'pageNo'; //参数名
	this.showTimes = 1;    //打印次数
	this.docCount = _docCount || 0;
	this.pageName = _sPageName || "index";
	this.pageExt = _sPageExt || "htm";
	this.pageSize = _pageSize || 15;
	this.urlType = _urlType || 1;
}

showPages.prototype.getPage = function(){ //丛url获得当前页数,如果变量重复只获取最后一个
	
}
showPages.prototype.checkPages = function(){ //进行当前页数和总页数的验证
	if (isNaN(parseInt(this.page))) this.page = 1;
	if (isNaN(parseInt(this.pageCount))) this.pageCount = 1;
	if (this.page < 1) this.page = 1;
	if (this.pageCount < 1) this.pageCount = 1;
	if (this.page > this.pageCount) this.page = this.pageCount;
	this.page = parseInt(this.page);
	this.pageCount = parseInt(this.pageCount);
}
showPages.prototype.createHtml = function(mode){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1;
	if (mode == '' || typeof(mode) == 'undefined') mode = 0;
	switch (mode) {
	case 0 : //模式1 (10页缩略,首页,前页,后页,尾页)
		if(this.pageCount != 0 && this.pageCount != 1){
			strHtml += '<div class="fy_list" >'
			
			if (prevPage < 1) {
				strHtml += '<a href="#" class="bor_l1">&lt;&nbsp;上一页</a>';
			} else {
				strHtml += '<a href="javascript:' + this.name + '.toPage(' + prevPage + ');" class="bor_l1">&lt;&nbsp;上一页</a>';
			}
			var num,counts,pages=1;
			if((this.pageCount-this.page) < 7)
			{
				if(this.pageCount < 7)
				{
					num = 1;
				}else if((this.pageCount - 6) > 1){
					num = (this.pageCount - 6);
				}else{
					num = this.page;
				}
				counts = this.pageCount;
			}else{
				num = this.page;
				if((this.pageCount-this.page) >= 6)
				{
					counts = (this.page+6);
				}else{					
					counts = 7;
				}
			}			
			for (var i = num; i <= counts; i++) {		
				if (i == this.page) {						
					strHtml += ' <a class="on">'+i+'</a>';
				} else {
					strHtml += '<a href="javascript:' + this.name + '.toPage(' + i + ');">' + i + '</a>';	
				}
				pages = i;
			}
			if(this.pageCount >= 8 && pages < this.pageCount){
				if(pages != (this.pageCount-1))
				{
					strHtml += '...';
				}
				strHtml += '<a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">' + this.pageCount + '</a>';
			}
			if (nextPage > this.pageCount) {
			    //strHtml += '<span class="disabled">&nbsp;&gt;&nbsp;</span>';
				strHtml += '<a href="javascript:' + this.name + '.toPage(' + nextPage + ');">下一页&nbsp;&gt;</a>&nbsp;&nbsp;';
			} else {	
				strHtml += '<a href="javascript:' + this.name + '.toPage(' + nextPage + ');">下一页&nbsp;&gt;</a>&nbsp;&nbsp;';
			}
			var pathName=window.document.location.pathname;
			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
			strHtml += '<span>跳转到第&nbsp; <input name="textfield3" type="text" class="inp6" placeholder="5" value="'+this.page+'" id="CP">&nbsp;&nbsp;页&nbsp;&nbsp;<img src="'+projectName+'/images/18xmghj_grzx_go.jpg"  style="cursor:pointer" align="absmiddle" onclick="return ' + this.name + '.toPage(CP.value)"/>';
			strHtml +='<div class="clear"></div>';
		}else{ document.getElementById("table12").style.display = "none"; }
          
			break;
		case 2 : //模式2 (前后缩略,页数,首页,前页,后页,尾页)
            if(this.pageCount <= 1){
            	break;
            }
						
			strHtml += '<table width="650" id="pageUtiler" border="0" cellspacing="0" cellpadding="0" align="center" class="h32 mar_t20 mar_b10"><tr><td width="225" class="tgray2">第<span class="tred1">' + this.page + '</span>页&nbsp;&nbsp;共<span class="tred1">' + this.pageCount + '</span>页&nbsp;&nbsp;每页<span class="tred1">' + this.pageSize + '</span>条&nbsp;&nbsp;</td>';
			//共<span class="tred1">' + this.docCount + '</span>条
			strHtml += '<td><div>';
			if (prevPage < 1) {	
				strHtml += '<span title="首页" class="disabled">&lt;&lt;</span>';
				//strHtml += '<span title="上一页" class="disabled">&lt;</span>';
			} else {
				strHtml += '<span title="首页"><a href="javascript:' + this.name + '.toPage(1);">&lt;&lt;</a></span>';
				//strHtml += '<span title="上一页"><a href="javascript:' + this.name + '.toPage(' + prevPage + ');">&lt;</a></span>';
			}
			if (this.page != 1) strHtml += '<span title="第1页" ><a href="javascript:' + this.name + '.toPage(1);">1</a></span>';
			if (this.page >= 5) strHtml += '<span>...</span>';
			if (this.pageCount > this.page + 2) {
				var endPage = this.page + 2;
			} else {
				var endPage = this.pageCount;
			}
			for (var i = this.page - 2; i <= endPage; i++) {
				if (i > 0) {
					if (i == this.page) {
						strHtml += '<span title="第' + i + '页" class="current">' + i + '</span>';
					} else {
						if (i != 1 && i != this.pageCount) {
							strHtml += '<span title="第' + i + '页"><a href="javascript:' + this.name + '.toPage(' + i + ');">' + i + '</a></span>';
						}
					}
				}
			}
			if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
			if (this.page != this.pageCount) strHtml += '<span title="第' + this.pageCount + '页"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">' + this.pageCount + '</a></span>';
			if (nextPage > this.pageCount) {
				//strHtml += '<span title="下一页" class="disabled">&gt;</span>';
				strHtml += '<span title="尾页" class="disabled">&gt;&gt;</span>';
			} else {
				//strHtml += '<span title="下一页"><a href="javascript:' + this.name + '.toPage(' + nextPage + ');">&gt;</a></span>';
				strHtml += '<span title="尾页"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">&gt;&gt;</a></span>';		
			}
			strHtml += '</div></td><td width="105">转到<input type="text" id="CP" maxlength="3" size="2" value="1" name="CP" style="width:35px; height:18px; border:1px solid #a9a9dd;" /> <' + 'img src="http://new.fjzzy.org/images/12zd_tw_gl_icon1.jpg" width="20" height="20" align="absmiddle" style="cursor:pointer" onclick="return ' + this.name + '.toGoPage(CP.value)"/></td>';
			break;
		case 3 : //模式2 (前后缩略,页数,首页,前页,后页,尾页)
            if(this.pageCount <= 1){
            	break;
            }
			strHtml += '<div class="list2 twhite1"><ul>';
			if (prevPage < 1) {	
				strHtml += '<li>首  页</li>';
				strHtml += '<li>上一页</li>';
			} else {
				strHtml += '<li><a href="javascript:' + this.name + '.toPage(1);">首  页</a></li>';
				strHtml += '<li><a href="javascript:' + this.name + '.toPage(' + prevPage + ');">上一页</a></li>';
			}
			if (nextPage > this.pageCount) {
				strHtml += '<li>下一页</li>';
				strHtml += '<li>尾  页</li>';
			} else {
				strHtml += '<li><a href="javascript:' + this.name + '.toPage(' + nextPage + ');">下一页</a></li>';
				strHtml += '<li><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">尾  页</a></li>';		
			}
			strHtml += '</ul><div class="clear"></div></div>';
			break;
	}
	return strHtml;
}
showPages.prototype.createUrl = function (page) { //生成页面跳转url
	if (isNaN(parseInt(page))) page = 1;
	if (page < 1) page = 1;
	if (page > this.pageCount) page = this.pageCount;
	var url = location.protocol + '//' + location.host + location.pathname;
	var args = location.search;
	var reg = new RegExp('([\?&]?)' + this.argName + '=[^&]*[&$]?', 'gi');
	args = args.replace(reg,'$1');
	if (args == '' || args == null) {
		args += '?' + this.argName + '=' + page;
	} else if (args.substr(args.length - 1,1) == '?' || args.substr(args.length - 1,1) == '&') {
			args += this.argName + '=' + page;
	} else {
			args += '&' + this.argName + '=' + page;
	}
	return url + args;
}
showPages.prototype.toPage = function(page){ //页面跳转
	var turnTo = 1;
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	self.location.href = this.createUrl(turnTo);
}


showPages.prototype.toGoPage = function(_pageNo){ //页面跳转
	var turnTo = 1;
	if (typeof(_pageNo) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
		self.location.href = this.createUrl(turnTo);
		return true;
	} else {
		turnTo = _pageNo;
		if(!(this.IsAllNumeric(turnTo))){
			alert("请填写数字。");
			return false;
	  } 
	  else{
		if(_pageNo>this.pageCount ||_pageNo==0){
				alert("请填写在总页数范围内的数字。");
				return false;
		}		
		else{
			turnTo = _pageNo;
			self.location.href = this.createUrl(turnTo);
			return true;  
		}
	  }  
	}
	
}


showPages.prototype.IsAllNumeric = function(number){ //页面跳转
	  var l=number.length;
	  var i,s;
	  for(i=0;i<l;i++)
	  { s=number.charAt(i);
		if(!(s>='0'&&s<='9')) return(false);
	  }
	  return(true);
}

showPages.prototype.printHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<div id="pages_' + this.name + '_' + this.showTimes + '" class="digg"></div>');
	document.getElementById('pages_' + this.name + '_' + this.showTimes).innerHTML = this.createHtml(mode);
	
}
showPages.prototype.formatInputPage = function(e){ //限定输入页数格式
	var ie = navigator.appName=="Microsoft Internet Explorer"?true:false;
	if(!ie) var key = e.which;
	else var key = event.keyCode;
	if (key == 8 || key == 46 || (key >= 48 && key <= 57)) return true;
	return false;
}
//-->