<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
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
	<title>广告列表</title>
	
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 广告管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toadd.do'"/>
		</form>
	<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="list.do" method="post" style="padding-top:5px;">
		标题: <input type="text" value="${QUERY.title }" name="title"/>
			<select  name="isenabled">
			<c:if test="${QUERY.isenabled==0 }">
				<option  value="0" selected="selected">请选择</option>
				<option  value="1">有效</option>
				<option  value="-1">无效</option>
			</c:if>
			<c:if test="${QUERY.isenabled==1 }">
				<option  value="0" >请选择</option>
				<option  value="1" selected="selected">有效</option>
				<option  value="-1">无效</option>
			</c:if>
			<c:if test="${QUERY.isenabled==-1 }">
				<option  value="0" >请选择</option>
				<option  value="1">有效</option>
				<option  value="-1" selected="selected">无效</option>
			</c:if>
			</select>
			<input type="submit" class="query" value="查询"/>
		</form>
		<form action="deletes.do" method="post" id="tableForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox" /></th>
						<th>广告id</th>
						<th>广告标题</th>
						<th>广告内容</th>
						<th>公告日期</th>
						<th>公告人</th>
						<th>是否可用</th>	
						<th>操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
				  <c:forEach items="${LIST }" var="advert">
					<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
						<td><input type="checkbox" name="ids" value="${advert.id}"/></td>
						<td align="center">${advert.id}</td>
						<td align="center">${advert.title }</td>
						<td align="center">${advert.content}</td>
						<td align="center">${advert.crtimeTxt}</td>
						<td align="center">${advert.crman }</td>
						<td align="center">${advert.isenabledTxt }</td>
						<td align="center">
							<a href="get.do?id=${advert.id }" class="pn-opt">修改</a> 
						 	<a href="delete.do?id=${advert.id }" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a> 
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page pb15" style="float:right;">
			  <input class="del-button" type="submit" value="删除" onclick="if(!confirm('您确定批量删除吗？ ')) {return false;}"/>
			<span class="r inb_a page_b">
			<!-- [当前页/尾页] -->
			<a href="list.do?page=1&title=${QUERY.title }&isenabled=${QUERY.isenabled}">首页</a>
		
			<a href="list.do?page=${(PAGE-1)<=0?1:(PAGE-1) }&title=${QUERY.title }&isenabled=${QUERY.isenabled}">上一页</a>

			<a href="list.do?page=${PAGE+1>PAGECOUNT?PAGECOUNT:PAGE+1 }&title=${QUERY.title }&isenabled=${QUERY.isenabled}">下一页</a>
		
			<a href="list.do?page=${PAGECOUNT }&title=${QUERY.title }&isenabled=${QUERY.isenabled}">尾页</a>
	
			当前第${PAGE }/共${PAGECOUNT }页
			</span>
			</div>
		</form>
	</div>
</body>
</html>