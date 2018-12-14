<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<head>
<title>join Page</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<!-- Customize CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_join.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>

<body class="text-center">
	<!-- Container -->
	<div
		class="container m-0 p-0 justify-content-md-center justify-content-xs-center" id="content">
		<div class="login_box ">
			<h1 class="text-center wdi_red">JOIN</h1>
			<hr>
				<div class="form-row commonForm">

					<!-- ID lable/input tag -->
					<div class="col-md-3 col-xs-3 label_input">
						<h6>ID</h6>
					</div>
					<div class="col-md-9 col-xs-9">
						<input type="text" class="form-control form-control-lg flat_input"
							title="ID" id="id">
					</div>

					<!--PW lable/input tag -->
					<div class="col-md-3 col-xs-3 label_input">
						<h6>PW</h6>
					</div>
					<div class="col-md-9 col-xs-9">
						<input type="password"
							class="form-control form-control-lg flat_input" title="PW"
							id="pw">
					</div>

					<!--NAME lable/input tag -->
					<div class="col-md-3 col-xs-3 label_input">
						<h6>NAME</h6>
					</div>
					<div class="col-md-9 col-xs-9">
						<input type="text" class="form-control form-control-lg flat_input"
							title="NAME" id="name">
					</div>

					<div class="col-md-3 col-xs-3 label_input">
						<h6>EMAIL</h6>
					</div>
					<div class="col-md-9 col-xs-9">
						<input type="text" class="form-control form-control-lg flat_input"
							title="EMAIL" id="email">
					</div>

					<!--GRADE lable/Dropdow tag -->
					<div class="col-md-3 col-xs-3 label_input">
						<h6>YEAR</h6>
					</div>
					<div class="container col-md-3 col-xs-3 dropdown_semester">
						<select class="form-control" id="semester">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select>
					</div>
					<div class="col-md-6 col-xs-6"></div>

					<!--join/cancel button -->
					<div class="col-md-6 col-xs-6 btns">
						<button class="btn btn-sm btn-block btn_join" id="btnJoin">
							JOIN</button>
					</div>
					<div class="col-md-6 col-xs-6 btns">
						<button type="button" class="btn btn-sm btn-block btn_cancel"
							onclick='location.href="/"'>CANCEL</button>
					</div>

				</div>
		</div>
	</div>
</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/common/func_check_input.js"></script>

<script type="text/javascript">
		$(document).ready(function(){
			$('#btnJoin').on('click',function(){

				if( !chkInput() ) {
					return;
				}
				event.preventDefault();

				$.ajax({
					url:"/user/insertUser.json",
					type : "GET",
					data : {
						'stuId':$('#id').val(),
						'pw':$('#pw').val(),
						'name':$('#name').val(),
						'semester':$('#semester').val(),
						'email':$('#email').val()
					},
					success : function(result){
						console.log(result);

		            if(result["result"] === "1"){
		            	//alert('회원가입 성공');
		            	location.href = "/";
		            }else{
		            	alert('회원가입 실패');
		            }
		        },
		        error : function(){
		        	alert('회원가입 에러');
		        }
		    });
		});
	});
</script>
