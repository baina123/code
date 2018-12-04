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
	<title>文章列表</title>

</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 文章管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toadd.do'"/>
		</form>
	<div class="clear"></div>
	</div>
	
	<div class="body-box">
	<form action="list.do" method="post" style="padding-top:5px;">
	标题名: <input type="text" value="${QUERY.title} " name="title" />
		<select  name="ishot" >
			<c:if test="${QUERY.ishot==0 }">
				<option  value="0" selected="selected">请选择</option>
				<option  value="1">热点</option>
				<option  value="-1">不是热点</option>
			</c:if>
			<c:if test="${QUERY.ishot==1 }">
				<option  value="0" >请选择</option>
				<option  value="1" selected="selected">热点</option>
				<option  value="-1">不是热点</option>
			</c:if>
			<c:if test="${QUERY.ishot==-1 }">
				<option  value="0" >请选择</option>
				<option  value="1">热点</option>
				<option  value="-1" selected="selected">不是热点</option>
			</c:if>
		</select>
			<select  name="isremod">
			<c:if test="${QUERY.isremod==0 }">
				<option  value="0" selected="selected">请选择</option>
				<option  value="1">推荐</option>
				<option  value="-1">不推荐</option>
			</c:if>
			<c:if test="${QUERY.isremod==1 }">
				<option  value="0" >请选择</option>
				<option  value="1" selected="selected">推荐</option>
				<option  value="-1">不推荐</option>
			</c:if>
			<c:if test="${QUERY.isremod==-1 }">
				<option  value="0" >请选择</option>
				<option  value="1">推荐</option>
				<option  value="-1" selected="selected">不推荐</option>
			</c:if>
		</select>
			<input type="submit" class="query" value="查询"/>
	</form>
		<form method="post" action="deletes.do"  id="tableForm" >
			<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox" /></th>
						<th>文章编码</th>
						<th>文章标题</th>
						<th>文章内容</th>
						<th>作者</th>
						<th>添加日期</th>
						<th>所属栏目</th>
						<th>是否推荐</th>
						<th>是否热点</th>
						<th>操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
				  <c:forEach items="${LIST }" var="article">
					<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
						<td><input type="checkbox" name="ids" value="${article.id }"/></td>
						<td align="center">${article.id }</td>
						<td align="center">${article.title }</td>
						<td align="center">${article.content }</td>
						<td align="center">${article.author }</td>
						<td align="center">${article.crtimeTxt }</td>
						<td align="center">${article.channel.cname}</td>
						<td align="center">${article.isremodTxt}</td>
						<td align="center">${article.ishotTxt}</td>
						<td align="center">
							<a href="get.do?id=${article.id }" class="pn-opt">修改</a> 
						 	<a href="delete.do?id=${article.id }" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a> 
						</td>
					</tr>
					</c:forEach>
				</tbody>
				</table>
			<div class="page pb15" style="float:right;">
			<input class="del-button" type="submit" value="删除" onclick="if(!confirm('您确定批量删除吗？ ')) {return false;}"/>
			  <span class="r inb_a page_b">
			     <!-- [当前页/尾页] -->
			     <a href="list.do?page=1&title=${QUERY.title}&author=${QUERY.author}">首页</a>
		
				<a href="list.do?page=${(PAGE-1)<=0?1:(PAGE-1) }&title=${QUERY.title }&author=${QUERY.author}">上一页</a>

				<a href="list.do?page=${PAGE+1>PAGECOUNT?PAGECOUNT:PAGE+1 }&title=${QUERY.title }&author=${QUERY.author}">下一页</a>
		
				<a href="list.do?page=${PAGECOUNT }&title=${QUERY.title}&author=${QUERY.author}">尾页</a>
	
				当前第${PAGE }/共${PAGECOUNT }页
			  </span>
			</div>
		</form>
	</div>
</body>
</html>