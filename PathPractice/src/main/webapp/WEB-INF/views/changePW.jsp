<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="kr">
<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
  <title>find password</title>

  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
  <!-- Customize CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_changePW.css">

  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
  <!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>

<body class="text-center">
  <!-- Container -->
  <div class="container m-0 p-0 justify-content-md-center justify-content-xs-center" id="content">
      <div class=" login_box ">
        <h1 class="text-center wdi_red">changePW</h1>
        <hr>
          <div class="form-row">
            <!-- input id, pw -->
            <div class= "col-md-12 col-xs-12">
             <input type="text" id="id" class="form-control form-control-lg flat_input" placeholder="username" >
           </div>
           <div class= "col-md-12 col-xs-12 ">
             <input type="password" id="pw" class="form-control form-control-lg flat_input" placeholder="password" >
           </div>
            <div class= "col-md-12 col-xs-12 mt-3 ">
              <button class="btn btn-md btn_submit mx-0" id="submit" >
          		     확인
              </button>
              <button type="button" class="btn btn-md btn_cancel mx-0" id="cancel" onclick="location.href='/mypage'">
          		      취소
              </button>
            </div>
            
			<!-- 변경할 비밀번호 입력-->
            <div class="col-md-12 col-xs-12">
              <input type="password" id="rePw" class="form-control form-control-lg flat_input" placeholder="new_password" style="display:none;" >
            </div>
            <div class= "col-md-12 col-xs-12 mt-3 " >
              <button class="btn btn-md btn_submit mx-0"  id="change" style="display:none;" >
              	  변경
              </button>
            </div>
          </div>
      </div>
  </div>
</body>
</html>

 <script type="text/javascript"> 
		$('#submit').on( 'click', function() { 	
			$.ajax({
				url : "/user/checkUserExist.json",						
				type : "POST",
				data : {
					'stuId' : $("#id").val(),
					'pw' :  $("#pw").val()
				},
				success : function(RESULT) {
					if (RESULT.result === "1") {
						$("#rePw").toggle();
						$("#change").toggle();
					} else {
						alert("회원정보 없음");
					}
				},
				error : function(request, status, error) {
					console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
				location.href = "/changePW";
			}
		});//ajax */
	});//submit click
		
		
</script>
<script type="text/javascript">
	  $('#change').on('click', function() { 
		  //event.preventDefault();
		$.ajax({
		 	url: "/user/updatePw.json",
			type: "POST",
			data: {
				'stuId':$('#id').val(), 
				'pw':$('#rePw').val()
			},
			success : function(Result){
				/*if (Result.result == "1") {
					alert("비밀번호 변경 성공");
					
				} else { 
					alert("비밀번호 변경 실패");
				}*/
				location.href = "/mypage";
			},
			error: function() {
				alert('비밀번호 변경 에러');
				console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
			}
		});//ajax
	});//change click 
		
	
</script>
