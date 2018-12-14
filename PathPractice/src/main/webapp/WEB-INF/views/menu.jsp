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
			<div class="jumbotron p-md-3 text-white rounded main_area" style="overflow: auto; width: auto; height: 480px;">

				<div class="d-flex align-items-center p-3 my-3 rounded"
					style="background: #f2f2f2;margin-top: 0px;">
					<div class="lh-100">
						<div style="text-align: center">
							<strong class="info" style="text-align: center"> 공지사항 </strong>
						</div>
					</div>
				</div>
				<!-- 등록된 과제를 보여주는 scroll창 -->
				<div style="overflow: auto; width: auto;"
					class="mt-3 content_preview_assignment scrollbar scrollbar_track scrollbar_thumb"
					data-spy="scroll" data-offset="0"></div>
					
			</div>
			
			<!-- footer 하단바 class="app-footer" -->
			<div id="footer"></div>

		</div>

	</div>
</body>
</html>

<script type="text/javascript">
	<%
	String id = (String) session.getAttribute("id");
	%>
	$(document).ready(function() {
		$.ajax({
			url : "/user/BlackboardUser.json",
			type : "POST",
			data : {
				'stuId' :<%=id%>
			},
			success : function(result){
				if(result.result == "0"){ // blackboard 유저가 아닐 때
					location.href = "/menu_non";
				}
			},
			error : function() {
				//alert('공지사항 불러오기 에러');
			}
		});
		
	//과제 불러오기
		//event.preventDefault();
		$.ajax({
			url : "/blackboard/getSubject.json",
			type : "POST",
			data : {
			'stuId' :<%=id%>
			}, 
			success : function(result) {
				if (result['result'] === 'no data') {
					//alert('등록된 공지사항 없음');
				} else { 
					console.log(result);
				var str = '';
				var subject='';
				str += '<div class="list-group">';
				console.log(result['result'].length);
				for (var i = 0; i < result['result'].length; i++) {
					subject = result['result'][i]['subject'];
					if(subject=="")continue;
					
					str += '<button class="btn p-1 btn-sm list-group-item" type="button" value="vv">';
					str += subject;
					str += '</button>';
					$('.scrollbar_track').append(str);
					str='';
					str += '<div class="list-group">'
					}
				}
			},
			error : function() {
				//alert('공지사항 불러오기 에러');
			}
		});//ajax
	});//$(document).ready
	
	$(document).on("click",'.list-group',function() {
		var subject = $(this).text();
		location.href="/announcement?subject="+subject;
	});// 과목명 선택 해서 그 과목에 해당하는 공지사항 보여줌
</script>


<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
