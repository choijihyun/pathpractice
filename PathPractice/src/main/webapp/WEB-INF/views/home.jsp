<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
	<title>HOME</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_home.css">

	<!-- fontawesome ì¼ë¡ icon ì¬ì©íê¸° -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</head>

<body class="text-center">

	<!-- Container -->
	<div class="container">
		<div class="row justify-content-md-center justify-content-xs-center row justify-content-sm-center justify-content-lg-center">
			<div class="col-md-6 col-md-auto col-xs-6 col-xs-auto col-sm-6 col-sm-auto col-lg-6 col-lg-auto login_box ">

				<!-- header (ìë¨ë°) class="app-header" -->
				<div id="header">
				</div>

				<!-- ìë¨ë°ì íë¨ë°ë¥¼ ì ì¸í ë¶ë¶ class="main-area" -->
				<div class="jumbotron p-md-0 text-white main_area">
					<div style="cursor:pointer; height: auto" onclick="location.href='/timetable_page';" class="scrollbar scrollbar_track scrollbar_thumb" data-spy="scroll" data-offset="0">
						<div id="timetable">
						</div>
					</div>

					<!-- ë±ë¡ë ê³¼ì ë¥¼ ë³´ì¬ì£¼ë scrollì°½ -->
					<div style="overflow:auto; width:auto; height:130px;" class="mt-3 content_preview_assignment scrollbar scrollbar_track scrollbar_thumb" data-spy="scroll" data-offset="0">
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
<script src="${pageContext.request.contextPath}/resources/js/common/timetable.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_cookie.js"></script>

<script type="text/javascript">
	var userInputId = getCookie("userInputId");
	console.log(userInputId);
	<%
		System.out.println("home session : "+session.getAttribute("id"));
	%>

</script>

<script type="text/javascript">
	<%
		String id = (String)session.getAttribute("id");
	%>
	$(document).ready(function(){

		$.ajax({
			url:"/homework/selectHomework.json",
			type : "GET",
			data : {
				'stuId':<%=id%>,
				'select':1
			},
			success : function(result){
				if(result['result'] === 'no data'){
					alert('등록된 과제 없음');
				}else{
					alert('과제 불러오기 성공');
					console.log(result);

					for(var i=0 ; i<result['result'].length ; i++){

						var assign_title = result['result'][i]['title'];
						var assign_contents = result['result'][i]['contents'];

						var str = '';
						str += '<button type="button" ';
						str += 'class="btn btn-lg btn-block btn-outline-danger">';
						str += '<h6 id="assign' + (i+1) + 'Title" ';
						str += 'style="font-weight: bold" class="mb-2">-' + assign_title + '</h6>';
						str += '<p6 id="assign' + (i+1) + 'Context">' + assign_contents + '</p6>';
						str += '</button>';
						$('.content_preview_assignment').append(str);
					}
				}

			},
			error : function(){
				alert('과제불러오기 에러');
			}
		});
	});
</script>