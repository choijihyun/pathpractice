<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html lang="kr">
<head>
<title>Login Page</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<!-- Customize CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_index.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

</head>

<body class="text-center">
	<!-- Container -->
	<div class="container m-0 p-0 justify-content-md-center justify-content-xs-center" >
		<div class="login_box">
			<h1 class="text-center wdi_red">Login</h1>
			<hr>
				<div class="form-row" style="overflow-x:hidden; overflow-y:auto; width:auto; height:480px;">
					<!-- input id, pw -->
					<div class="col-md-12 col-xs-12">
						<input type="text" id="id"
							class="form-control form-control-lg flat_input"
							placeholder="username">
					</div>
					<div class="col-md-12 col-xs-12">
						<input type="password" id="pw"
							class="form-control form-control-lg flat_input"
							placeholder="password">
					</div>
					
				<!-- 	
					
					<h6>Push 알림 메시지 입력</h6>
					 
					  <form action="/push_notification" method="post">
					    <textarea name="message" rows="4" cols="10" placeholder="메세지를 입력하세요"></textarea><br>
					    <input type="submit" name="submit" value="Send" id="submitButton">
					</form>
    
     -->
    
					<!-- remember user infomation - checkbox -->
					<div class="checkbox col-md-12">
						<label> <input type="checkbox" value="remember-me"
							id="idSaveCheck"> Remember me
						</label>
					</div>

					<!-- login/join/find pw button-->
					<div class="col-md-12 col-xs-12">
						<button class="btn btn-lg btn-block btn_login" id="submit">
							LOGIN</button>
					</div>
					<!--      <div id="home"></div> -->
					<div class="col-md-6 col-xs-6">
						<button type="button" class="btn btn-lg btn-xs btn-block btn_join"
							onclick='location.href="/join"'>SignUp</button>
					</div>
					<div class="col-md-6 col-xs-6">
						<button type="button" class="btn btn-lg btn-xs btn-block btn_join"
							onclick='location.href="/findPW"'>PW</button>
					</div>
				</div>
		</div>
	</div>

</body>
</html>
		

<script src="${pageContext.request.contextPath}/resources/js/common/func_cookie.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "/user/preHandle.json",
			method : "POST"
		});
		
<%
			int FF=0;
			System.out.println("index session : " + session.getAttribute("id"));
			if(session.getAttribute("id")!=null){
				FF=1;
				String id = (String)session.getAttribute("id");
				String pw = (String)session.getAttribute("pw");
				String check = (String)session.getAttribute("check");
				System.out.println("id, pw, checkbox : "+id+pw+check);
				if(FF == 1){
%>
				
				if(<%=check%>=="1"){
					$("#idSaveCheck").attr('checked', true) ;
					$('#id').val(<%=id%>);
					//$('#pw').val(<%=pw%>);
				}
<%		
				}
			}
%>
				
<!--
		$("#idSaveCheck").change(function() {
			flag=0;
			if ($("#idSaveCheck").is(":checked")) {
				console.log("check in remember me");
				flag=1;
				//setCookie("userInputId", userInputId, 7); 
			} else {
				console.log("no check");
				flag=0;
			}

			if ($('#id').val() != "") {
				$.ajax({
					url :"/user/checkbox.json",
					type : "GET",
					data :{
						"stuId" : $('#id').val(),
						"select" : flag
					}					
				});
			}
		});
		-->
 		$('#submit').on('click', function() {

			//setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			event.preventDefault();
	
			$.ajax({
				url : "/user/checkUser.json",
				method : "post",
				data : {
					'stuId' : $('#id').val(),
					'pw' : $('#pw').val()
				},
				success : function(result) {
					if (result.result === "1") {
						
						location.href = "/home";

					}else{
						alert('아이디나 비밀번호를 다시 확인해 주세요.');
					}
				},//success
				error : function() {
					alert('로그인 에러');
				}
			});
			
			
			flag=0;
			if ($("#idSaveCheck").is(":checked")) {
				//alert("check in remember me");
				flag=1;
				//setCookie("userInputId", userInputId, 7); 
			} else {
				//alert("no check");
				flag=0;
			}

			if ($('#id').val() != "") {
				event.preventDefault();
				$.ajax({
					url :"/user/checkbox.json",
					type : "GET",
					data :{
						"stuId" : $('#id').val(),
						"select" : flag
					}					
				});
			}
		}); 
	});
</script>
