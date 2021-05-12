<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<h1>File Upload (파일 업로드)</h1>
<!-- action의 값이 endpoint를 바라봅니다., enctype은 멀티파트 옵션을 줌 -->
<!-- form 태그의 enctype 속성은 multipart/form-data로 세팅하여 브라우져가 파일 업로드 방식으로 동작하도록 설정 -->
<form action="fileUpload"  method="post" enctype="multipart/form-data">
	<!-- input 태그 file 속성으로 작성하여 form 방식으로 파일 업로드를 할 수 있습니다.-->
	<input type="file"  name="uploadfile" placeholder="파일 선택" /><br /> 
	<input type="submit" value="upload">
</form>

<h1>Multi File Upload (다중 파일 업로드)</h1>
<form action="multi_fileUpload"  method="post" enctype="multipart/form-data">
	<!-- multiple 속성추가 -->
    <input type="file" name="uploadfiles" placeholder="파일 선택" multiple/><br/>
    <input type="submit" value="upload">
</form>
</html>