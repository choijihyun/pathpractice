<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
	<title>MENU</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_menunon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_menu.css">

	<!-- fontawesome ì¼ë¡ icon ì¬ì©íê¸° -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>

<body class="text-center">

	<!-- Container -->
	<div
		class="container m-0 p-0 justify-content-md-center justify-content-xs-center">
		<div class="login_box ">

			<!-- header (상단바)-->
			<div id="header"></div>

			<!-- 상단바와 하단바를 제외한 부분 class="main-area" -->
			<div class="jumbotron p-md-3 text-white rounded main_area">

				<div class="d-flex align-items-center p-3 my-3 rounded"
					style="background: #f2f2f2;margin-top: 0px;">
					<div class="lh-100">
						<div style="text-align: center">
							<strong class="info" style="text-align: center"> 블랙보드에 연동하세요! </strong>
						</div>
					</div>
				</div>
				<!-- 등록된 과제를 보여주는 scroll창 -->
				<div style="overflow: auto; width: auto; height: 480px;"
					class="mt-3 content_preview_assignment scrollbar scrollbar_track scrollbar_thumb"
					data-spy="scroll" data-offset="0">
					<div class="btn btn-sm btn_icon" aria-label="Left Align" align="left">
						<img src="${pageContext.request.contextPath}/resources/img/blackboard.jpg" width=225" height="190" align="left" style="border:10 solid black">
						
					</div>
					<h6 class="mb-0 lh-100 info"> 포털 로그인한 후에 위의 그림과 같이 들어가 설정에서 비밀번호를 uis와 동일하게 변경해주세요.
						</h6>
					
				</div>
					
			</div>
			
			<!-- footer 하단바 class="app-footer" -->
			<div id="footer"></div>

		</div>

	</div>
</body>
</html>



<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
