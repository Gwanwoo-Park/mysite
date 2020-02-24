<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath }/guestbook">
		<input type='hidden' name="no" value="${param.no }">
		<table>
			<tr>
				<input type="hidden" name="a" value="deleteAdd">
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><input type="submit" value="확인"></td>
				<td><a href="${pageContext.request.contextPath }/guestbook">방명록으로 돌아가기</a></td>
			</tr>
		</table>
	</form>
</body>
</html>