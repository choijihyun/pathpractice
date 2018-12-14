<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
<head>
	<title>announcement</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_announcement.css">

	<!-- fontawesome img -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</head>

<body class="text-center">

	<!-- Container -->
			<div class="container m-0 p-0  justify-content-md-center justify-content-xs-center" >
			<div class="login_box">

				<!-- header  class="app-header" -->
				<div id="header"></div>

				<!-- 상당바와 하단바를 제외한 부분 class="main-area" -->
				<div class="jumbotron m-2 p-0 text-white rounded main_area">
						
						<!-- 과제 전체 보여주는 영역(스트롤바 포함¨) -->
					<!-- 과제가 여러개 이면 스크롤 생김 -->
					<div style="overflow-x:hidden; overflow-y:auto; width:auto; height:480px;" class="mt-3 scrollbar scrollbar-track scrollbar-thumb" data-offset="0">

						<!-- 스크롤바 제외한 과제를 보여주는 영역 -->
						<div class="px-2 content_show_assign">							
						</div>
					</div>
						
				</div>

			</div ><!--login-box -->

			<!-- footer 하단바 class="app-footer" -->
			<div id="footer"></div>

		</div>
	</div>
	<input type="hidden" id="hiddenAssign" name="hide" value="" >
	<input type="hidden" id="hiddenSub" name="hide" value="" >
</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_announcement.js"></script>

<!-- 모든 과제 불러오기,띄우기 -->
<script type="text/javascript">
	$(document).ready(function(){
		var subject = '${subject}';
		<% String id = (String)session.getAttribute("id"); %>
		showAllAnnounce(<%=id%>,subject);
	});
</script>
