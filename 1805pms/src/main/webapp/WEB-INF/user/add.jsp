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

<!-- 引入jquery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8"/>
<title>用户管理-添加</title>

<!-- 添加表单验证  -->
<script type="text/javascript">
	// 用户名:数字加字母结束前不能全部是数字 6-16位   
	var CHKLOGINNAME="^(?![0-9]+$)[a-zA-Z0-9]{6,16}$";
	// 密码:数字加字母结束前不能全部是数字和字母 6-16位   
	var CHKPASSWORD="^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16}$";
	// 出生日期  yyyy-MM-dd 月份1-12 日期1-31 
	var CHKDATE="^[0-9]{4}-0?[1-9]|1[0-2]-0?[1-9]|[1-2][0-9]|3[0-1]$";
	// 邮箱 xxxx.@xxx.com也可包含_, 也可.com.cn 
	var CHKEMAIL="^[a-zA-Z0-9_]+@[a-z0-9]{2,5}(\\.[a-z]{2,3}){1,2}$";
	// 真实姓名  
	var CHKREALNAME="^[\u4e00-\u9fa5]{2,5}$";
	
/*	//验证用户名(js)
	function chkloginname(){
		//获取用户名 
		var lgEle=document.getElementById("loginname");
		var loginname = lgEle.value;
		//定义匹配用户名的正则表达式   
		var reg=new RegExp(CHKLOGINNAME);
		//获取用户名是否输入成功的元素对象 
		var spanEle=document.getElementById("resultName");
		if(reg.test(loginname)){
			// 表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败
		}else{
			spanEle.innerHTML="用户名必须包含数字和字母，且不能低于六位 ";
			spanEle.style.color="red";
			lgEle.value="";
			lgEle.focus();
			return false;
		}
	}
	*/
	//验证用户名(jQuery)
	function chkloginname(){
		//获取用户名
		var loginname=$("#loginname").val();
		//定义匹配用户的正则表达式
		var reg=new RegExp(CHKLOGINNAME);
		if(reg.test(loginname)){// 表示正确
			if(chkExistLoginname(loginname)){
				return true;	
			}else{
				return false;
			}
		}else{
			$("#resultName").html("用户名必须包含数字和字母，且不能低于六位");
			$("#resultName").css("color","red");
			$("#loginname").val("");
			$("#loginname").focus();
			return false;
		}	
	}
	
	//检测用户名是否重复
	function chkExistLoginname(loginname){
		var flag=false;
		$.ajax({
			//请求路径
			url:'chkexist.do',
			//请求方式
			type:'post',
			//请求参数
			data:'type=1&loginname='+loginname,
			//是否异步
			async:false,
			//预期服务器返回的数据类型
			dataType:'text',
			//响应成功调用回调函数
			success:function(flag){
				if(flag=='true'){//没有重复
					$("#resultName").html("√");	
					$("#resultName").css("color","green");
					flag=true;
				}else{
					$("#resultName").html("此用户名已存在");	
					$("#resultName").css("color","red");
					flag=false;
				}
			},
			error:function(){
				alert('请求数据失败....');
			}
		});
		return flag;
	}
	
	//检测邮箱是否唯一
	function chkExistEmail(email){
		var flag=false;
		$.ajax({
			//请求路径
			url:'chkexist.do',
			//请求方式
			type:'post',
			//请求参数
			data:'type=2&email='+email,
			//是否异步
			async:false,
			//预期服务器返回的数据类型
			dataType:'text',
			//响应成功调用回调函数
			success:function(flag){
				if(flag=='true'){//没有重复
					$("#resultemail").html("√");	
					$("#resultemail").css("color","green");
					flag=true;
				}else{
					$("#resultemail").html("此邮箱已存在");	
					$("#resultemail").css("color","red");
					flag=false;
				}
			},
			error:function(){
				alert('请求数据失败....');
			}
		});
		return flag;
	}
	
/*	//验证密码(js)
	function chkpassword(){
		//获取密码 
		var pwdEle=document.getElementById("password");
		var password=pwdEle.value;
		//定义匹配密码的正则表达式 
		var reg=new RegExp(CHKPASSWORD);
		//获取密码是否输入成功的元素对象 
		var spanEle=document.getElementById("resultPwd");
		if(reg.test(password)){
			// 表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败
		}else{
			spanEle.innerHTML="密码必须包含数字和字母，且不能低于六位 ";
			spanEle.style.color="red";
			// 清空文本框
			pwdEle.value="";
			// 重新聚焦
			pwdEle.focus();
			return false;
		}	
	}  */
	//验证密码(jQuery)
	function chkpassword(){
		//获取密码 
		var password=$("#password").val();
		//定义匹配密码的正则表达式 
		var reg=new RegExp(CHKPASSWORD);
		if(reg.test(password)){// 表示正确
			$("#resultPwd").html("√");
			$("#resultPwd").css("color","green");
			return true;
		}else{
			$("#resultPwd").html("密码必须包含数字和字母，且不能低于六位");
			$("#resultPwd").css("color","red");
			$("#password").val("");
			$("#password").focus();
			return false;
		}
	}
	
/*	//验证邮箱 (js)
	function chkemail(){
		//获取邮箱 
		var emailEle=document.getElementById("email");
		var email=emailEle.value;
		//定义匹配邮箱的正则表达式 
		var reg=new RegExp(CHKEMAIL);
		//获取邮箱是否输入成功的元素对象 
		var spanEle=document.getElementById("resultemail");
		if(reg.test(email)){
			// 表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败	
		}else{
			spanEle.innerHTML="请输入正确的邮箱格式";
			spanEle.style.color="red";
			// 清空文本框
			emailEle.value="";
			// 重新聚焦
			emailEle.focus();
			return false;
		}	
	}  */
	//验证邮箱 (jQuery)
	function chkemail(){
		//获取邮箱 
		var email=$("#email").val();
		//定义匹配邮箱的正则表达式 
		var reg=new RegExp(CHKEMAIL);
		if(reg.test(email)){//表示正确
			if(chkExistEmail(email)){
				return true;	
			}else{
				return false;
			}
		}else{
			$("#resultemail").html("请输入正确的邮箱格式");
			$("#resultemail").css("color","red");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	}
	
/*	//验证真实姓名 (js)
	function chkrealname(){
		//获取真实姓名 
		var realnameEle=document.getElementById("realname");
		var realname=realnameEle.value;
		//定义匹配真实姓名的正则表达式 
		var reg=new RegExp(CHKREALNAME);
		//获取真实姓名是否输入成功的元素对象 
		var spanEle=document.getElementById("resultrealname");
		if(reg.test(realname)){
			// 表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败
		}else{
			spanEle.innerHTML="请输入真实姓名";
			spanEle.style.color="red";
			// 清空文本框
			realnameEle.value="";
			// 重新聚焦
			realnameEle.focus();
			return false;
		}	
	} */
	//验证真实姓名 (jQuery)
	function chkrealname(){
		//获取真实姓名 
		var realname=$("#realname").val();
		//定义匹配真实姓名的正则表达式 
		var reg=new RegExp(CHKREALNAME);
		if(reg.test(realname)){
			$("#resultrealname").html("√");
			$("#resultrealname").css("color","green");
			return true;
		}else{
			$("#resultrealname").html("请输入真实姓名");
			$("#resultrealname").css("color","red");
			$("#realname").val("");
			$("#realname").focus();
			return false;
		}
	}
	
	//验证两次密码是否一致 
	function chkRePwd(){
		//获取密码
		var repwdEle=document.getElementById("repwd");
		var repwd=repwdEle.value;
		var password=document.getElementById("password").value;	
		//获取两次密码是否输入一致的元素对象 
		var spanEle=document.getElementById("resultRepwd");
		if(repwd==password){
			// 表示输入正确
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
			// 表示输入失败	
		}else{
			spanEle.innerHTML="两次密码不一致，请重新输入 ";
			spanEle.style.color="red";
			return false;
		}	
	} 
/*	//验证两次密码是否一致 
	function chkRePwd(){
		//获取密码 
		var password=$("#password").val();
		//获取确认密码 
		var repwd=$("#repwd").val();
		if(repwd==password){//表示正确
			$("#resultRepwd").html("√");
			$("#resultRepwd").css("color","green");
			return true;
	}else{
		$("#resultRepwd").html("1两次密码不一致，请重新输入");
		$("#resultRepwd").css("color","red");
		$("#repwd").val("");
		$("#repwd").focus();
		return false;
	}
}*/

	//文档就绪事件
	$(function(){
		//下拉框change事件
		$("#dep1").change(
				function(){
					//清空第二个下拉框
					$("#dep2").empty();
					//ajax异步提交
					$.post("getdep.do",//url
							{pid:this.value},//json类型数据 传值
							function(data){
								if(data!=null){
									$(data).each(
											function(){
												//添加数据到第二个下拉框
												$("#dep2").append("<option value="+this.id+">"+this.dname+"</option>");
											}
										);
								}
							},//成功后执行
								"json" );//返回类型			
				});
	});
	//文件上传
	function upload() {
		//ajax请求 局部提交
		//设置参数
		var args={
			//url绝对路径
			url:$("#path").val()+"/upload/common.do",
			//返回类型
			dataType:"text",
			//提交方式
			type:"post",
			success:function(result){
				//设置图片属性
				$("#img").attr("src",$("#path").val()+"/upload/"+result);
				//将路径设置到隐藏域中
				$("#pic").val(result);
			}
		}
		//表单局部提交
		$("#jvForm").ajaxSubmit(args);
	}

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
	<!-- 获得应用的绝对路径 -->
	<input type="hidden" id="path" value="${pageContext.request.contextPath }"/>
	 <div>
	 	<img src="../images/logo.png" />
	 </div>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form name="fm" id="jvForm" action="add.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${MSG}</span>
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						用户名:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  class="required" name="loginname" id="loginname"  onblur="chkloginname()"/>
							
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						密码:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="password"  class="required" name="password" id="password"  onblur="chkpassword()"/>
							<span id="resultPwd"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						确认密码:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="password"  class="required" name="repwd" id="repwd"  onblur="chkRePwd()"/>	
						<span id="resultRepwd"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						真实姓名:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  class="required" name="realname" id="realname"  onblur="chkrealname()"/>
						<span id="resultrealname"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						性别:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="radio" class="required" name="sex" value="男" checked="checked"/>男
						<input type="radio" class="required" name="sex" value="女"/>女
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						出生日期:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  name="birthday" class="Wdate" onclick="WdatePicker()"/>	
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						email:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="text"  class="required" name="email" id="email" onblur="chkemail()"/>
						<span id="resultemail"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门名称:
					</td>
					<td width="80%" class="pn-fcontent">
						<select id="dep1" name="dep1">
							<c:forEach items="${DLIST }" var="dep1" >
								<option value="${dep1.id }">${dep1.dname }</option>
							</c:forEach>			
						</select>
						<select id="dep2" name="dept.id">
							<c:forEach items="${DLIST2 }" var="dep2" >
								<option value="${dep2.id }">${dep2.dname }</option>
							</c:forEach>			
						</select>	
					</td>
				</tr>
	
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						头像:
					</td>
					<td width="80%" class="pn-fcontent">
						<input type="file" name="file" onchange="upload()"/>
						<img id="img" width="150px" height="150px" />
						<!-- 提交图片路径 -->
						<input type="hidden" name="pic" id="pic"/>
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