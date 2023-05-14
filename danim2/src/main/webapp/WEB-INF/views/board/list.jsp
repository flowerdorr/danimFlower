<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- SimpleDateFormat --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">
.row {
	margin: 0px auto;
	width: 900px;
}

h1 {
	text-align: center;
}
</script>
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>스프링 게시판</h1>
			<table class="table">
				<tr>
					<td><a href="insert" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
			</table>
			<div>
				<div id="searchMember" style="height: 100px; margin: 0px auto; text-align: center;">
					<form method='get' action='../board/search'>
						<select name="searchType">
							<option value="" disabled selected>검색타입</option>
							<option value="name"
								<c:if test="${searchType eq 'writer'}">selected</c:if>>아이디</option>
							<option value="subject"
								<c:if test="${searchType eq 'review_title'}">selected</c:if>>제목</option>
							<option value="content"
								<c:if test="${searchType eq 'review_content'}">selected</c:if>>내용</option>	
						</select> <input type="text" name="keyword"
							value="${keyword!=null?keyword:''}" /> <input type="submit"
							value="검색">
					</form>
				</div>
			</div>
			<table class="table table-striped">
				<tr class="danger">
					<th class="text-center" width=10%>번호</th>
					<th class="text-center" width=45%>제목</th>
					<th class="text-center" width=15%>이름</th>
					<th class="text-center" width=20%>작성일</th>
					<th class="text-center" width=10%>조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td class="text-center" width=10%>${vo.review_num }</td>
						<td class="text-left" width=45%><a
							href="detail?no=${vo.review_num }">${vo.review_title }</a></td>
						<td class="text-center" width=15%>${vo.writer }</td>
						<td class="text-center" width=20%><fmt:formatDate
								value="${vo.insert_date }" pattern="yyyy-MM-dd" /></td>
						<td class="text-center" width=10%>${vo.view_count }</td>
					</tr>
				</c:forEach>
			</table>
			<table class="table">
				<td class="text-center"><a href="#"
					class="btn btn-sm btn-primary">이전</a> ${curpage } page /
					${totalpage } pages <a href="#" class="btn btn-sm btn-primary">다음</a>
				</td>
			</table>
		</div>
	</div>
</body>
</html>