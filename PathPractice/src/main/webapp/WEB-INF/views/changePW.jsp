<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">

<head>
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
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>

<body class="text-center">
  <!-- Container -->
  <div class="container" id="content">
    <div class="row justify-content-md-center justify-content-xs-center">
      <div class="col-md-6 col-md-auto col-xs-6 col-xs-auto login_box ">
        <h1 class="text-center wdi_red">changePW</h1>
        <hr>
        <form>
          <div class="form-row">

            <!-- input id, pw -->
            <div class= "col-md-12 col-xs-12">
             <input type="text" id="id" class="form-control form-control-lg flat_input" placeholder="username">
           </div>
           <div class= "col-md-12 col-xs-12 ">
             <input type="password" id="pw" class="form-control form-control-lg flat_input" placeholder="password" >
           </div>
            <div class= "col-md-12 col-xs-12 mt-3 ">
              <button class="btn btn-md btn_submit mx-0" id="submit" >
          		     íì¸
              </button>
              <button type="button" class="btn btn-md btn_cancel mx-0" id="cancel" onclick="location.href='/mypage'">
          		      ì·¨ìÂÂ
              </button>
            </div>

            <div class="col-md-12 col-xs-12">
              <input type="password" id="rePw" class="form-control form-control-lg flat_input" placeholder="password" style="display:none;" >
            </div>
            <div class= "col-md-12 col-xs-12" >
              <a class="btn btn-lg btn-block btn_submit" id="change" style="display:none;">
              	  ë³ê²½
              </a>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script type="text/javascript">
    $(document).ready(function() {
      $('#submit').on('click', function() { //í´ë¹ ìì´ëì ë¹ë°ë²í¸ì ìë ¥ë ë¹ë²ì´ ê°ìì§ íì¸
        $.ajax({
          url: "/user/checkUser.json",
          type: "GET",
          data: {
            'stuId':$('#id').val(),
            'pw':$('#pw').val()
          },
          success: function(result) {
            console.log(result);
            if (result['result'] === "1") { 
              //document.getElementById('pw').style.display="";
              $('#rePw').css('display', '');
              $('#change').css('display', '');
            } else {
              alert("íìì ë³´ ìì");
            }
          },
          error: function() {
            alert('ë¹ë°ë²í¸ ì°¾ê¸° ìë¬');
          }
        });
      });

      $('#change').on('click', function() {  //í´ë¹ ìì´ëì ë¹ë°ë²í¸ì ìë ¥ë ë¹ë²ì´ ê°ìì§ íì¸
        $.ajax({
          url: "http://localhost:8090/user/updatePW.json",
          type: "GET",
          data: {'stuId':$('#id').val(), 'pw':$('#rePw').val()},
          success: function(result) {
            console.log(result);
            if (result['result'] === "1") {ÂÂ
              alert("ë¹ë°ë²í¸ ë³ê²½ ì±ê³µ");
              location.href = "/mypage";
            } else {//íìì ë³´ ìì
              alert("ë¹ë°ë²í¸ ë³ê²½ ì¤í¨");
            }
          },
          error: function() {
            alert('ë¹ë°ë²í¸ ë³ê²½ ìë¬');
          }
        });
      });
    });
  </script>
</body>

</html>
