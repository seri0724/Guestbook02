﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.javaex.vo.GuestbookVo"%>
<%
List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="gb" method="get">
	<input type="hidden" name="a" value="add">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br/>

	<% for(GuestbookVo vo : list) { %>
			<table width=510 border=1>
				<tr>
					<td><%=vo.getNo() %></td>
					<td><%=vo.getName() %></td>
					<td><%=vo.getRegDate() %></td>
					<td><a href="gb?a=deleteform&no=<%=vo.getNo() %>">삭제</a></td>
				</tr>
				<tr>
					<td colspan=4><%=vo.getContent() %></td>
				</tr>
			</table>
		    <br/>
	<%
	}
	%>	    	
</body>
</html>