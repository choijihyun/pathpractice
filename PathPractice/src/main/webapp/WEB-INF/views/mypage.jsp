<!doctype html>
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

	<!-- fontawesome 으로 icon 사용하기 -->
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

				<!-- header (상단바) class="app-header" -->
				<div id="header">
				</div>

				<!-- 상단바와 하단바를 제외한 부분 class="main-area" -->
				<div class="jumbotron p-0 rounded main_area">
					<div class="d-flex align-items-center p-3 my-3 rounded" style="background: #f2f2f2;">
						<i class="fas fa-user m-3 user_icon"></i>
						<div class="lh-100">
							<h6 class="mb-0 lh-100 info">정주호(이름!!)</h6>
							<small class="info">16011016(학번!!)</small>
						</div>
					</div>

					<div class="list-group">
						<button class="btn p-1 btn-sm list-group-item" type="button">정보 수정</button>
						<button class="btn p-1 btn-sm list-group-item" type="button" onclick="location.href='/changePW'">비밀번호 변경</button>
						<button class="btn p-1 btn-sm list-group-item" type="button">알림 끄기</button>
						<button class="btn p-1 btn-sm list-group-item" type="button">탈퇴</button>
					</div>
				</div>

				<!-- footer 하단바 class="app-footer" -->
				<div id="footer">
				</div>

			</div>
		</div>
	</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>