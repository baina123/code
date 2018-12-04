<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 引时间控件 -->
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="res/css/style.css" />

<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!-- 引入jquery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8"/>
<title>广告管理-添加</title>
<script type="text/javascript">
	// 公告人  
	var CHKCRMAN="^[\u4e00-\u9fa5]{2,10}$";
	//广告标题 必须是汉字或大小写字母或数字，至少2位
	var CHKTITLE="^[0-9a-zA-Z\u4e00-\u9fa5]{2,}$";
	//广告内容 必须是汉字或大小写字母或数字，,不少于5位
	var CHKCONTENT="^[0-9a-zA-Z\u4e00-\u9fa5]{5,}$";
	
/*	//验证公告人(js)
	function chkcrman(){
		//获取公告人 
		var crmanEle=document.getElementById("crman");
		var crman=crmanEle.value;
		//定义匹配公告人的正则表达式 
		var reg=new RegExp(CHKCRMAN);
		//获取公告人是否输入成功的元素对象 
		var spanEle=document.getElementById("resultcrman");
		if(reg.test(crman)){
			//表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			//表示输入失败	
		}else{
			spanEle.innerHTML="请输入中文姓名  ";
			spanEle.style.color="red";
			// 重新聚焦
			crmanEle.focus();
			return false;
		}	
	} */
	//验证公告人(jQuery)
	function chkcrman(){
		//获取公告人 
		var crman=$("#crman").val();
		//定义匹配公告人的正则表达式 
		var reg=new RegExp(CHKCRMAN);
		if(reg.test(crman)){
			$("#resultcrman").html("√");
			$("#resultcrman").css("color","green");
			return true;
		}else{
			$("#resultcrman").html("请输入中文姓名");
			$("#resultcrman").css("color","red");
			$("#crman").val("");
			$("#crman").focus();
			return false;
		}
	}
	
/*	//验证广告标题(js)
	function chktitle(){
		//获取广告标题 
		var titleEle=document.getElementById("title");
		var title=titleEle.value;
		//定义匹配广告标题的正则表达式 
		var reg=new RegExp(CHKTITLE);
		//获取广告标题是否输入成功的元素对象  
		var spanEle=document.getElementById("resulttitle");
		if(reg.test(title)){
			//表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败	
		}else{
			spanEle.innerHTML="请输入广告标题";
			spanEle.style.color="red";
			// 清空文本框
			titleEle.value="";
			//重新聚焦
			titleEle.focus();
			return false;
		}	
	}  */
	
	//验证广告标题(jQuery)
	function chktitle(){
		//获取广告标题 
		var title=$("#title").val();
		//定义匹配广告标题的正则表达式 
		var reg=new RegExp(CHKTITLE);
		if(reg.test(title)){
			$("#resulttitle").html("√");
			$("#resulttitle").css("color","green");
			return true;
		}else{
			$("#resulttitle").html("请输入广告标题");
			$("#resulttitle").css("color","red");
			$("#title").val("");
			$("#title").focus();
			return false;
		}
	}
	
/*	//验证广告内容(js)
	function chkcontent(){
		//获取广告内容
		var contentEle=document.getElementById("content");
		var content=contentEle.value;
		//定义匹配广告内容的正则表达式 
		var reg=new RegExp(CHKCONTENT);
		//获取广告内容是否输入成功的元素对象  
		var spanEle=document.getElementById("resultcontent");
		if(reg.test(content)){
			//表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败	
		}else{
			spanEle.innerHTML="请输入广告内容，不少于5位";
			spanEle.style.color="red";
			//重新聚焦
			contentEle.focus();
			return false;
		}	
	}  */
	//验证广告内容(jQuery)
	function chkcontent(){
		//获取广告内容
		var content=$("#content").val();
		//定义匹配广告标题的正则表达式 
		var reg=new RegExp(CHKCONTENT);
		if(reg.test(content)){
			$("#resultcontent").html("√");
			$("#resultcontent").css("color","green");
			return true;
		}else{
			$("#resultcontent").html("请输入广告内容，不少于5位");
			$("#resultcontent").css("color","red");
			$("#content").val("");
			$("#content").focus();
			return false;
		}
	}
	
/*	//验证所有
	function chkAll(){
		return chktitle()&&chkcontent()&&chkcrman();
	}*/
</script>	
<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>

</head>
<body>
	 <div>
	 	<img src="../images/logo.png" />
	 </div>
<div class="box-positon">
	<div class="rpos">当前位置: 广告管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="add.do" method="post">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${MSG }</span>
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						广告标题:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  class="required" name="title" id="title" onblur="chktitle()"/>	
						<span id="resulttitle"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						广告内容:
					</td>
					<td width="80%" class="pn-fcontent">
						<textarea rows="10" cols="100" class="required" name="content" id="content" onblur="chkcontent()"></textarea>
						<span id="resultcontent"></span>
					</td>
				</tr>
				
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						公告日期:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  name="crtime" maxlength="80" class="Wdate" onclick="WdatePicker()"/>	
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						公告人:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="crman" id="crman" onblur="chkcrman()"/>
						<span id="resultcrman"></span>
					</td>
				</tr>	
			</tbody>
			
			<tbody id="tab_3" style="display: none">
				<tr>
					<td >
						<textarea rows="15" cols="136" id="productList" name="packageList"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp;&nbsp;&nbsp;&nbsp;
					    <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>