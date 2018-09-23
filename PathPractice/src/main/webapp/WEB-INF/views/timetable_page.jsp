<!doctype html>
<html lang="kr">
<head>
<title>timetable_page</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<!-- Customize CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_timetable_page.css">

<!-- fontawesome 으로 icon 사용하기 -->
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
			<div class="jumbotron p-md-0 m-0 main_area">
				<!-- + 버튼  -->
				<div class="row col-auto justify-content-end setting">
					<div class="col-10 col-xs-10 col-sm-10 col-lg-10 col-md-10"></div>
					<button id="plusTime" class="btn-lg btn_add p-0 mx-2" type="button"
						data-toggle="collapse" data-target="#collapseAdd"
						aria-label="Left Align" aria-expanded="false"
						aria-controls="collapseAdd">
						<span class="fas fa-plus-square"></span>
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
							<button id="addBySearching"
								class="btn p-1 btn-sm list-group-item" type="button"
								data-toggle="collapse" data-target="#collapseSearching"
								aria-expanded="false" aria-controls="collapseSearching"
								href="/find_subject">검색하기</button>
							<button type="button" id="btnUndo2"
								class="p-1 btn-sm list-group-item">취소하기</button>
						</div>

						<!-- 직접 추가하기 창 -->
						<div class="collapse mt-1 form-add" id="collapseDirect">
							<div class="card card-body card_directly p-2">
								<form class="commonForm">
									<div class="form-group m-0">
										<label for="subjectName" class="col-12 label_input">과목명</label>
										<input type="text" class="form-control input" title="과목명"
											id="subjectName">
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
													<option value="mon">mon</option>
													<option value="tue">tue</option>
													<option value="wen">wen</option>
													<option value="thu">thu</option>
													<option value="fri">fri</option>
												</select> <select id="day2" class="form-control-xs">
													<option value="">요일</option>
													<option value="none">없음</option>
													<option value="mon">mon</option>
													<option value="tue">tue</option>
													<option value="wen">wen</option>
													<option value="thu">thu</option>
													<option value="fri">fri</option>
												</select>
											</div>
											<label class="mx-auto time_label">시작시간</label>
											<div class="mx-auto">
												<select id="sHour" class="form-control-xs">
													<option value="">시</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
												</select> <select id="sMinute" class="form-control-xs">
													<option value="">분</option>
													<option value="00">00</option>
													<option value="30">30</option>
												</select>
											</div>
											<label class="mx-auto time-label">종료시간</label>
											<div class="mx-auto">
												<select id="eHour" class="form-control-xs">
													<option value="">시</option>
													<option value="9">9</option>
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
								</form>
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
			</div>
			<!-- footer 하단바 class="app-footer" -->
			<div id="footer"></div>
		</div>
</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/timetable.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_check_input.js"></script>

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
	});
</script>

<!--select box 선택 값 가져오기 -->
<script>
	$('#btnSuccess').on('click', function () {
		if( !chkInput() ) return;
		var shour = document.getElementById("sHour");
		var val_shour = shour.options[shour.selectedIndex].value;
		var sminute = document.getElementById("sMinute");
		var val_sminute = sminute.options[sminute.selectedIndex].value;
		var ehour = document.getElementById("eHour");
		var val_ehour = ehour.options[ehour.selectedIndex].value;
		var eminute = document.getElementById("eMinute");
		var val_eminute = eminute.options[eminute.selectedIndex].value;

		var day1 = document.getElementById("day1");
		var val_day1 = day1.options[day1.selectedIndex].value;
		var day2 = document.getElementById("day2");
		var val_day2 = day2.options[day2.selectedIndex].value;
		var index_day1,index_day2;
		var table = document.getElementById("table"),rIndex,cIndex;
		var row_length = table.rows.length;
		var new_row_len = val_ehour;

		//동적으로 테이블 조정
		new_row_len = (new_row_len-8)*2;
		if(val_eminute == '30')	new_row_len++;
		var cell = new Array(); 
		if(row_length < new_row_len){
		for(var i = row_length+1 ; i < new_row_len ; i++){
			newRow = table.insertRow(table.length),
				cell[0] = newRow.insertCell(0),
				cell[1] = newRow.insertCell(1),
				cell[2] = newRow.insertCell(2),
				cell[3] = newRow.insertCell(3),
				cell[4] = newRow.insertCell(4),
				cell[5] = newRow.insertCell(5),
				time = (i%2 == 0 ?  (i/2)+8 : '' );
				text = '#';
				cell[0].innerHTML = time;
				for(var j =1 ; j<6 ; j++)
					cell[j].innerHTML = text;
					if(i%2 == 0 )
					$(newRow).addClass("stripe-top");
				for(var j=0 ; j<5 ; j++)
					$(cell[j]).css("border-right","1px solid #e5e5e5");
			}
		}
		if(val_sminute == "30") {val_shour = (val_shour -8)*2;}
		else if(val_sminute  == "00") {val_shour = (val_shour -8)*2-1;}
		if(val_eminute == "30") {val_ehour = (val_ehour -8)*2;}
		else if(val_eminute== "00") {val_ehour = (val_ehour -8)*2-1;}
		var days = ['mon','tue','wen','thu','fri'];
		for(var i=0 ; i<5 ; i++){
			if( days[i] == val_day1)
				index_day1 = i+1;
			if( days[i] == val_day2)
				index_day2 = i+1;
		}
			//color random
		var colorCode = "#"+Math.round(Math.random() * 0xFFFFFF).toString(16);
			//paint time
		for(i=val_shour ; i<val_ehour ; i++){
			$( 'table tr:nth-child(' + i + ') td:eq(' + index_day1 + ')' ).css('background-color', colorCode);
			$( 'table tr:nth-child(' + i + ') td:eq(' + index_day2 + ')' ).css('background-color', colorCode);
		}
		// select box value init
		$('select').find("option:eq(0)").prop("selected", true);
		$('#addByDirectly').trigger('click');
		$('#plusTime').trigger('click');
	});
</script>

<!-- 테이블 클릭 시 수정 -->
<script>
	var table = document.getElementById("table"),rIndex,cIndex;
		$('#btnSuccess').on('click', function (){
		for(var i = 1 ; i < table.rows.length ; i++){				
			for(var j = 1 ; j<table.rows[i].cells.length ; j++){
				table.rows[i].cells[j].onclick = function(){
					rIndex = this.parentElement.rowIndex;
					cIndex = this.cellIndex;
					console.log("ROW:" +rIndex+ ", Cell:" +cIndex);
				}
			}
		}	
	});
</script>