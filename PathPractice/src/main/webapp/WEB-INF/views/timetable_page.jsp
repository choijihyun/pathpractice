<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
	<title>timetable_page</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous"> 
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_timetable_page.css">

	<!-- fontawesome img -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</head>

<body class="text-center">
	<!-- Container -->
	<div class="container m-0 p-0 justify-content-md-center justify-content-xs-center">
		<div class="login_box ">
			<!-- header (상단바) class="app-header" -->
			<div id="header"></div>

			<!-- 상단바와 하단바를 제외한 부분 class="main-area" -->
			<div class="jumbotron p-md-0 m-0 main_area p-0">
				<!-- + 버튼  -->
				<div class="row col-auto justify-content-end setting">
					<div class="col-10 col-xs-10 col-sm-10 col-lg-10 col-md-10"></div>
					<button id="plusTime" class="btn-lg btn_add p-0 mx-2" type="button"
						data-toggle="collapse" data-target="#collapseAdd"
						aria-label="Left Align" aria-expanded="false"
						aria-controls="collapseAdd">
						<span class="fas fa-plus-square"></span>
					</button>
					<button id="minusTime" class="btn-lg btn_add p-0 mr-4" type="button"
						aria-label="Left Align" aria-expanded="false">
						<span class="fas fa-minus-square"></span>
					</button>
				</div>

				<!-- +버튼 클릭시 추가 창 띄움-->
				<div id="collapseAdd"
					class="collapse p-0 md-1 col-12 col-xs-12 col-sm-12 col-lg-12 col-md-12">
					<div id="topbar" class="card card-body card_plus">
						<div class="list-group">

							<button id="addByDirectly" class="btn p-1 btn-sm list-group-item"
								type="button" data-toggle="collapse"
								data-target="#collapseDirect" aria-expanded="false"
								aria-controls="collapseDirect">직접 추가하기</button>
							<!-- <button id="addBySearching"
								class="btn p-1 btn-sm list-group-item" type="button"
								data-toggle="collapse" data-target="#collapseSearching"
								aria-expanded="false" aria-controls="collapseSearching"
								href="/find_subject">검색하기</button> -->
							<button type="button" id="btnUndo2"
								class="p-1 btn-sm list-group-item">취소하기</button>
						</div>

						<!-- 직접 추가하기 창 -->
						<div class="collapse mt-1 form-add" id="collapseDirect">
							<div class="card card-body card_directly p-2 commonForm">
								<!-- <form class="commonForm"> -->
									<div class="form-group m-0 ">
										<label for="subjectName" class="col-12 label_input">과목명</label>
										<input type="text" class="col-10 m-0 form-control input" title="과목명"
											id="subjectName" style="display:inline-block;">
										<a class="btn btn-sm btn_icon" aria-label="Left Align" id="search"> 
											<span class="fas fa-search"></span>
										</a>
									</div>
									<div class="form-group m-0">
										<label for="professorName" class="col-12 label_input">교수명</label>
										<input type="text" class="form-control input" title="교수명"
											id="professorName">
									</div>
									<div class="form-group m-0">
										<label for="time" class="col-12 label_input">시간</label>
										<div id="time" class="col-12 form-row">
											<label class="mx-auto time_label">요일</label>
											<div class="mx-auto">
												<select id="day1" class="form-control-xs">
													<option value="">요일</option>
													<option value="월">월</option>
													<option value="화">화</option>
													<option value="수">수</option>
													<option value="목">목</option>
													<option value="금">금</option>
												</select>
												 <select id="day2" class="form-control-xs">
													<option value="">요일</option>
													<option value="월">월</option>
													<option value="화">화</option>
													<option value="수">수</option>
													<option value="목">목</option>
													<option value="금">금</option>
												</select>
											</div>
											<label class="mx-auto time_label">시작시간</label>
											<div class="mx-auto">
												<select id="sHour" class="form-control-xs">
													<option value="">시</option>
													<option value="09">09</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
												</select> 
												<select id="sMinute" class="form-control-xs">
													<option value="">분</option>
													<option value="00">00</option>
													<option value="30">30</option>
												</select>
											</div>
											<label class="mx-auto time_label">종료시간</label>
											<div class="mx-auto">
												<select id="eHour" class="form-control-xs">
													<option value="">시</option>
													<option value="09">09</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
												</select> <select id="eMinute" class="form-control-xs">
													<option value="">분</option>
													<option value="00">00</option>
													<option value="30">30</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group m-0">
										<label for="place" class="col-12 label_input">장소</label> <input
											type="text" class="form-control input" title="장소" id="place">
									</div>
								<!--</form>  -->
								<div class="form-row mx-auto btn_submit">
									<button type="button" id="btnSuccess"
										class="mx-auto col-5 btn btn-sm btn_add_sub">추가</button>
									<button type="button" id="btnUndo1"
										class="mx-auto col-5 btn btn-sm btn_add_sub">취소</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div id="timetable"></div>
				<div id="tableChart"></div>
			</div>
			<!-- footer 하단바 class="app-footer" -->
			<div id="footer"></div>
		</div>
		<input type="hidden" id="hiddenSubKey" name="hide" value="" >
	</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/timetable.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_check_input.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_cookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_timetable.js"></script>

<!-- 취소하기 버튼 클릭 시 다시 창 닫기 -->
<script type="text/javascript">
	$('#btnUndo2').on('click', function () {
		$('#plusTime').trigger('click');
		$('#collapseDirect').collapse('hide');
	});
	$('#plusTime').on('click', function () {
		$('#collapseDirect').collapse('hide');
		$('#collapseAdd').collapse('hide'); 
	});
	$('#btnUndo1').on('click', function () {
		$('#addByDirectly').trigger('click');
		$('.commonForm input[type="text"]').val("");
	});
	$('#subjectName').on('click', function () {
		$('#search').trigger('click');
	}); 
	
</script>

<script type="text/javascript">
	var userInputId = getCookie("userInputId");
	console.log(userInputId);
	<% System.out.println("timetable session : " + session.getAttribute("id")); %>
</script>

<script type="text/javascript">
$('#search').on('click', function() {
		location.href="/find_subject?&page=timetablePage";
	});
</script>

<!--시간표 추가 -->
<script>
	<% String id = (String) session.getAttribute("id"); %>
	var subjectKey = '${subjectKey}'; 

 	if(subjectKey != 0){
 		fillInfomation(subjectKey,3);//직접 추가할 때 subNo어떻게 하는지 물어보기//직접추가하면 교수명 과목명 시작시간 종료시간 장소 모두 입력받은걸로 찾아와서 색칠
	}
 	
	$('#btnSuccess').on('click', function () {
		if( !chkInput() ) return; 
		insertTimetable( $('#hiddenSubKey').val(),<%=id%> );
	});
</script>

<!-- 시작할때 시간표 불러오기 -->
<script>
	$(document).ready(function(){
		showAllTimetable(<%=id%>); 
	});
</script>

<!-- 테이블 클릭 시 수정할 수 있게 -->
<script>
$('#minusTime').on('click', function () {
	showAlltable(<%=id%>);	
	
	var table = document.getElementById("tableDel"), rIndex, cIndex;
});


</script>
 

<!-- 처음페이지 시작, 추가 수정 삭제시 시간표 정보들 모두 불러오기 -->
