<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link href="${pageContext.request.contextPath}/resources/css/review_write.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
<div class="row">
<div class="col-md-2"></div>
<div class="col-md-8">
<h2>Review</h2>
	<form method="post" action="reInsert.do">
		<div class="review_write_bg p-5">
			<div class="input-group">
				<label for="title" class="p-3">제목</label>
				<input type="text" name="title" class="form-control" placeholder="제목을 입력해주세요.">
			</div>

            <div class="form-group">
				<label for="writer" class="p-3">작성자 :</label>
			${userName}
			</div>

            <div class="input-group">
                
				    <label for="file" class="">이미지 첨부:</label>
				    <input type="file" name="file" class="form-control" value="파일선택">
                
                
                    <label for="do" class="form-control d-flex">지역선택 :
                        <select name="do" class="">
                            <option value="시/도">시/도 선택 </option>
                            <option value="시/도">경기도 </option>
                            <option value="시/도">서울 </option>
                            <option value="시/도">강원도 </option>
                        </select>  
                         <select name="si" class="">
                            <option value="시/도">시/군/구 선택 </option>
                            <option value="시/도">경기도 </option>
                            <option value="시/도">서울 </option>
                            <option value="시/도">강원도 </option>
                        </select>  
                        
                       
                    </label>
			</div>
            <textarea class="form-control mt-3" rows="28"></textarea>
            
            <div class="d-flex justify-content-between mt-3">
            <button class="btn btn-primary">작성완료</button>
                <div class="button-group">
                    <button class="btn btn-primary">작성완료</button>
                    <button class="btn btn-danger">다시작성</button>
                </div>
            </div>
        </div>
        <button class="btn list_button mt-2">목록</button>
	</form>
</div>
<div class="col-md-2"></div>

</div>
</div>
</body>
</html>