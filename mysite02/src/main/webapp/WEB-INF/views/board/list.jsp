<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board"
					method="post">
					<input type="hidden" name="a" value="searchAdd"> <input
						type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:set var='listCount' value='${fn:length(list) }'></c:set>
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>${listCount-status.index }</td>
							<td style="text-align:left; padding-left:${20*vo.depth }px">
								<c:if test="${vo.depth >= 1 }">
									<img src='/mysite02/assets/images/reply.png'>
								</c:if> <a
								href="${pageContext.servletContext.contextPath }/board?a=viewform&no=${vo.no }&title=${vo.title }&name=${vo.name }&oNo=${vo.oNo }&gNo=${vo.gNo }&depth=${vo.depth }">${vo.title }</a>
							</td>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>

							<td><c:if test="${authUser.name == vo.name }">
									<a
										href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }"
										class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${page == 1 }">
								<li><a
									href="${pageContext.servletContext.contextPath }/board?page=${page }">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.servletContext.contextPath }/board?page=${page-1 }">◀</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin='1' end='${listCount }' var='index' step='1'>
							
							<c:choose>
								<c:when test="${index == page }">
									<li class="selected"><a style="color:red" href="">${index }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.servletContext.contextPath }/board?page=${index }">${index }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${page == 1 }">
								<li><a
									href="${pageContext.servletContext.contextPath }/board?page=${page }">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.servletContext.contextPath }/board?page=${page+1 }">◀</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->

				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a
							href="${pageContext.request.contextPath }/board?a=writeform&no=${authUser.no }"
							id="new-book">글쓰기</a>
					</div>
				</c:if>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>