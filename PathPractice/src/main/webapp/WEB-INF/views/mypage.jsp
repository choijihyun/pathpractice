<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html lang="kr">
<head>
	<title>mypage</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_mypage.css">

	<!-- fontawesome ì¼ë¡ icon ì¬ì©íê¸° -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</head>

<body class="text-center">

	<!-- Container -->
	<div class="container">
		<div class="row justify-content-md-center justify-content-xs-center justify-content-sm-center justify-content-lg-center">
			<div class="col-md-6 col-md-auto col-xs-6 col-xs-auto col-sm-6 col-sm-auto col-lg-6 col-lg-auto login_box ">

				<!-- header (ìë¨ë°) class="app-header" -->
				<div id="header">
				</div>

				<!-- ìë¨ë°ì íë¨ë°ë¥¼ ì ì¸í ë¶ë¶ class="main-area" -->
				<div class="jumbotron p-0 rounded main_area">
					<div class="d-flex align-items-center p-3 my-3 rounded" style="background: #f2f2f2;">
						<i class="fas fa-user m-3 user_icon"></i>
						<div class="lh-100">
							<h6 class="mb-0 lh-100 info">ì ì£¼í¸(ì´ë¦!!)</h6>
							<%
								String id = (String)session.getAttribute("id");
							%>
							<small class="info">
								<%=id %>님 환영합니다.
							</small>
							
						</div>
					</div>

					<div class="list-group">
						<button class="btn p-1 btn-sm list-group-item" type="button" onclick="location.href='/update_information'">ì ë³´ ìì </button>
						<button class="btn p-1 btn-sm list-group-item" type="button" onclick="location.href='/changePW'">ë¹ë°ë²í¸ ë³ê²½</button>
						<button class="btn p-1 btn-sm list-group-item" type="button">ìë¦¼ ì¤ì </button>
						<button class="btn p-1 btn-sm list-group-item" type="button">íí´</button>
					</div>
				</div>

				<!-- footer íë¨ë° class="app-footer" -->
				<div id="footer">
				</div>

			</div>
		</div>
	</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
