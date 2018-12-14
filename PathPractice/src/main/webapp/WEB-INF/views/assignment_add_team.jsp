<!doctype html>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<html lang="kr">
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>

<head>
<title>ADD ASSINGMENT TEAM</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<!-- Costumize CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_assignment_add.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/5star.css">

<!-- fontawesome-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
	
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
</head>

<body class="text-center">
	<!-- Container -->
	<div class="container m-0 p-0 row justify-content-md-center justify-content-xs-center" data-spy="scroll">
			<div class="login_box">
				<h2 class="text-center wdi_red mt-1">ADD TEAM ASSINGMENT</h2>
				<hr class="m-1">
				<div class="commonForm">
					<div class="form-row m-3" >
						<div class="col-md-3 col-xs-3 my-2 label_input">과목명</div>
						<div class="col-md-7 col-xs-7 ">
							<input type="text" class="form-control form-control-sm mt-1 mb-1  flat_input" id="subjectName" title="과목명">
						</div>
						<div class="col-md-2 col-xs-2 mt-1 mb-1">
							<a class="btn btn-sm btn_icon" aria-label="Left Align" id="search"> 
								<span class="fas fa-search"></span>
							</a>
						</div>
						<div class="col-md-3 col-xs-3 my-2 label_input">제목</div>
						<div class="col-md-7 col-xs-7">
							<input type="text" class="form-control form-control-sm mt-1 mb-1 flat_input" title="제목" id="title">
						</div>

						<div class="col-md-3 col-xs-3 my-2 label_input">내용</div>
						<div class="col-md-7 col-xs-7">
							<input type="text" class="form-control form-control-sm mt-1 mb-1 flat_input" title="내용" id="contents">
						</div>
						
						<div class="col-md-3 col-xs-3 my-3 label_input">팀장</div>
						<div class="col-md-7 col-xs-7 my-2" id="reader"> 
							<div class="input-group input-reader">
								<div class="input-group-prepend mb-1">
							    	<button class="btn btn-sm btn-outline-danger block" type="button" id="btn_add_reader">ADD</button>
						
									<input type="text" class="col-6 form-control form-control-sm flat_input my-0 mx-1 reader-id" placeholder="학번" aria-describedby="btn_add_reader">
									<input type="text" class="col-6 form-control form-control-sm flat_input my-0 mx-1 reader-name" placeholder="이름" aria-describedby="btn_add_reader">
								</div>
							</div>
						</div>
						
						<div class="col-md-3 col-xs-3 my-3 label_input">팀원</div>
						<div class="col-md-7 col-xs-7 my-2" id="member"> 
							<div class="input-group input-member">
								<div class="input-group-prepend mb-1">
							    	<button class="btn btn-sm btn-outline-danger block" type="button" id="btn_add_member">ADD</button>
						
									<input type="text" class="col-6 form-control form-control-sm flat_input my-0 mx-1 mem-id" placeholder="학번" aria-describedby="btn_add_member">
									<input type="text" class="col-6 form-control form-control-sm flat_input my-0 mx-1 mem-name" placeholder="이름" aria-describedby="btn_add_member">
								</div>
							</div>
						</div>
						<div class="col-md-2 col-xs-2 my-3"></div>
						
						<div class="col-md-3 col-xs-3 my-2 label_input">중요도</div>
						<span class="rate-area  col-md-6 col-xs-6 ">
						  <input type="radio" id="p5" name="star-input" value="5" /> <label for="p5">5</label>
						  <input type="radio" id="p4" name="star-input" value="4" /> <label for="p4">4</label>
						  <input type="radio" id="p3" name="star-input" value="3" /> <label for="p3">3</label>
						  <input type="radio" id="p2" name="star-input" value="2" /> <label for="p2">2</label>
						  <input type="radio" id="p1" name="star-input" value="1" /> <label for="p1">1</label>
						</span>
						<div class="col-md-2 col-xs-2 my-3"></div>

						<div class="col-md-3 col-xs-3 my-2 label_input">마감일</div>
						<div class="col-md-6 col-xs-6 my-2"">
							<form action="demo_form.asp">
								<input type='text' id='dueDate'>
							</form>
						</div>
						<div class="col-md-3 col-xs-3 my-2""></div>

						<!--join/cancel button -->
						<div class="col-md-6 col-xs-6">
							<button class="btn btn-sm btn-block btn_submit" id="submit">
								SUBMIT</button>
						</div>

						<div class="col-md-6 col-xs-6">
							<a class="btn btn-sm btn-block btn_cancel" href="/assignment">
								CANCEL </a>
						</div>
					</div>

				</div>
			</div>
	</div>
	<input type="hidden" id="hiddenIsNew" name="hide" value="" >
	<input type="hidden" id="hiddenCnt" name="hide" value=0 >
</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/common/func_check_input.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_cookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_assignment.js"></script>

<script type="text/javascript">
	var userInputId = getCookie("userInputId");
	console.log(userInputId);
	<%
	System.out.println("assignment_add session : " + session.getAttribute("id"));
	%>
</script>

<!-- 과제 수정시 url parameter로 받은 값들로 값 넣어주기 -->
<script type="text/javascript">
$(document).ready(function() {
	var title = '${title}';
 	var dueDate = '${dueDate}';
	var importance = '${importance}';
	var contents = '${contents}';
	var subNo = '${subNo}'; 
	var assignNo = '${assignNo}'; 
	var team = '${team}'; 

	var String = "00";
	subNo = String+subNo;
	subNo = subNo.replace(/(\s*)/g,"");
	var len = subNo.length;
	subNo = subNo.substr(len-6,6);
	//alert("##################subNo = "+subNo);
	
	if( title!=" " && dueDate!=" " && importance!="" && contents!="")
		$('#hiddenIsNew').val("update").trigger('change');
	else
		$('#hiddenIsNew').val("new").trigger('change');
	

	$('#subjectName').on('click', function () {
		$('#search').trigger('click');
	});
	
	$("#dueDate").datepicker({ 
		changeMonth: true, 
        changeYear: true,
        dayNames: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dateFormat : "yy-mm-dd"
	});
	
	if(subNo != 0){
		//alert("###############subNo = "+subNo);
		fillInfomation(subNo,1,title,contents,dueDate,importance);
		fillMember(team);
	}
	
	$('#search').on('click', function() {
		location.href="/find_subject?&page=assignmentAddTeam&title="+title+
														"&dueDate"+dueDate+
														"&importance"+importance+
														"&contents"+contents+
														"&assignNo"+assignNo;
	});
	
});
</script>

<script type="text/javascript">
	var subNo = '${subNo}';
	var assignNo = '${assignNo}';  

	var String = "00";
	subNo = String+subNo;
	subNo = subNo.replace(/(\s*)/g,"");
	var len = subNo.length;
	subNo = subNo.substr(len-6,6);
	//alert("##################subNo = "+subNo);
	
	
	
	$(document).ready(function() {
		var team = '${team}'; 
		console.log("########"+team);
		$('#submit').on('click', function() {
			<%
			String id = (String) session.getAttribute("id");
			%>
			$(".mem-id").prop('disabled',true);
			$(".mem-name").prop('disabled',true);
	
			/* if (!chkInput())
				return;  */

			$("#dueDate").datepicker();
			var due = document.getElementById("dueDate").value;
			var radioVal = $('input[name="star-input"]:checked').val();
			var title = $('#title').val()
			var contents = $('#contents').val()
 		 	var userInfomation = new Array();
			var i=1;
			var check=0;
			
			$(".userId").each(function() {
				var userId = $(this).val();
				if(userId==null){
					check=i;
				}
				console.log("id="+userId);
				userInfomation[i] = userId;
				i = i+2;
			});

			i=0;
			$(".userName").each(function() {
				var userName = $(this).val();
				console.log("name="+userName);
				userInfomation[i] = userName;
				i = i+2;
			});
			console.log(userInfomation);
			
			
			
			if($('#hiddenIsNew').val() == "new"){
				$.ajax({
					url:"/team/insertTeam.json",
					type : "GET",
					async : false,
					data : {
						'leaderName':userInfomation[0],
						'leaderNum': userInfomation[1],
						'memOneName':userInfomation[2],
						'memOneNum': userInfomation[3],
						'memTwoName':userInfomation[4],
						'memTwoNum': userInfomation[5],
						'memThreeName':userInfomation[6],
						'memThreeNum': userInfomation[7],
						'memFourName':userInfomation[8],
						'memFourNum': userInfomation[9],
					},
					success : function(result){
						if(result['result'] === "0"){ 
							alert('팀원등록 실패');
						}else{
							insertNewHomework(<%=id%>,radioVal,due,title,contents,0,subNo,result['result']);
							i = 1;
							console.log(i+"=i , userid="+<%=id%>);
							for(i=1 ; i<userInfomation.length ; i=i+2){
								console.log(i+"=i , id="+userInfomation[i]);
								if(userInfomation[i] == null) break;
								if(userInfomation[i] == <%=id%>) continue;
								insertNewHomework(userInfomation[i],radioVal,due,title,contents,0,subNo,result['result']);
							}
							location.href = "/assignment";
							alert('팀원등록 성공');
						}
					},
					error : function(request,status,error){
						alert('팀원등록 에러');
						console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
			    	}
				});//ajax
				
			}//new
			
			else{
				$.ajax({
					url:"/team/updateTeam.json",
					type : "GET",
					async : false,
					data : {
						'teamNum':team,
						'leaderName':userInfomation[0],
						'leaderNum': userInfomation[1],
						'memOneName':userInfomation[2],
						'memOneNum': userInfomation[3],
						'memTwoName':userInfomation[4],
						'memTwoNum': userInfomation[5],
						'memThreeName':userInfomation[6],
						'memThreeNum': userInfomation[7],
						'memFourName':userInfomation[8],
						'memFourNum': userInfomation[9],
					},
					success : function(result){
						console.log("%%%%%%%%5"+result);
						if(result['result'] === 0){ 
							alert('팀원수정 실패');
						}else{
							updateAssign(due,radioVal,title,contents,subNo,<%=id%>,0,team);
							alert('팀원수정 성공');
						}
					},
					error : function(request,status,error){
						alert('팀원수정 에러');
						console.log('message:'+request.responseText+'\n'+'error:'+error);
			    	}
				});//ajax
				
			}
			
		});//submit btn click
		
		
	});//function

</script>

<script type="text/javascript">
	
	$(document).ready(function() {
		
		$(document).on("click", '.btn_del_member', function() {
			$(this).parent().remove();
	
			cnt = Number($('#hiddenCnt').val())-1;
			$('#hiddenCnt').val(cnt).trigger('change');
			if(cnt<4){
				$(".mem-id").prop('disabled',false);
				$(".mem-name").prop('disabled',false);
			}
	    });
		
		$(document).on("click", '.btn_del_reader', function() {
			$(this).parent().remove();
			$(".reader-id").prop('disabled',false);
			$(".reader-name").prop('disabled',false);
	    });

		$('#btn_add_member').on('click', function() { 
			cnt = Number($('#hiddenCnt').val())+1;
			$('#hiddenCnt').val(cnt).trigger('change');
			
			if(cnt<5){
				positionId = ".mem-id";
				positionName = ".mem-name";
				where = ".input-member";
				addmember( positionId, positionName, where);
			}
			if(cnt==4){
				$(".mem-id").prop('disabled',true);
				$(".mem-name").prop('disabled',true);
			}
		});//#btn_add_member btn click
		
		$('#btn_add_reader').on('click', function() { 
			if(! $('.reader-id').is(":disabled") ){
				positionId = ".reader-id";
				positionName = ".reader-name";
				where = ".input-reader";
				addmember( positionId, positionName, where);
			}
		});//#btn_add_member btn click
		
	});//function

</script>

