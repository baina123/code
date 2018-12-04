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

<link rel="stylesheet" href="../res/css/style.css" />

<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8"/>
<title>频道管理-添加</title>
<script type="text/javascript">
	// 频道名  
	var CHKCNAME="^[\u4e00-\u9fa5]{2,10}$";
	
	 //验证频道名(jQuery)
	 function chkcname(){
		//获取频道名 
		var cname=$("#cname").val();
		//定义匹配频道名的正则表达式 
		var reg=new RegExp(CHKCNAME);
		if(reg.test(cname)){
			$("#resultcname").html("√");
			$("#resultcname").css("color","green");
			return true;
		}else{
			$("#resultcname").html("请输入中文");
			$("#resultcname").css("color","red");
			$("#cname").val("");
			$("#cname").focus();
			return false;
	 }
 }
/*  //验证频道名(js)
	function chkcname(){
		//获取频道名 
		var cnameEle=document.getElementById("cname");
		var cname=cnameEle.value;
		//定义匹配频道名的正则表达式 
		var reg=new RegExp(CHKCNAME);
		//获取频道名是否输入成功的元素对象
		var spanEle=document.getElementById("resultcname");
		if(reg.test(cname)){
			// 表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败
		}else{
			spanEle.innerHTML="请输入中文  ";
			spanEle.style.color="red";
			// 清空文本框
			cnameEle.value="";
			// 重新聚焦
			cnameEle.focus();
			return false;
		}	
	} */	
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
	 	<img src="images/logo.png" />
	 </div>
<div class="box-positon">
	<div class="rpos">当前位置: 频道管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="add.do" method="post" >
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
						频道名:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  class="required" name="cname" id="cname" onblur="chkcname()"/>	
						<span id="resultcname"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						所属上级:
					</td>
					<td width="80%" class="pn-fcontent">
						<select name="pid">
							<c:forEach items="${pnames }" var="pnames" >
								<option value="${pnames.id }" name="id">${pnames.pname }</option>
							</c:forEach>			
						</select>
					</td>
				</tr>
			
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						级别:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="radio" class="required" name="lev" value="1" checked="checked"/>一级
						<input type="radio" class="required" name="lev" value="2" />二级
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否叶子:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="radio" name="isleaf" value="1"  checked="checked"/>是叶子
						<input type="radio" name="isleaf" value="2"  />不是叶子
					</td>
				</tr>
				<tr>
				
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