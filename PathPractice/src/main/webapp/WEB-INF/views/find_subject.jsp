<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">

<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
<title>과목 찾기</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<!-- Costumize CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_find_subject.css">

<!-- fontawesome ì¼ë¡ icon ì¬ì©íê¸° -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>


<body class="text-center">
	<!-- Container -->
	<div class="container m-0 p-0 justify-content-md-center justify-content-xs-center">
		<div class="login_box ">
			<h1 class="text-center m-0 wdi_red mt-1">FIND!</h1>
			<hr>
			<div class="jumbotron p-0 rounded main_area">
				<div class="form-row m-3 p-3">
					<div class="col-md-3 col-xs-3 mt-1 mb-1 label_input">과목명</div>
					<div class="col-md-7 col-xs-7 ">
						<input type="text"
							class="form-control form-control-sm mt-1 mb-1 flat_input"
							id="subjectName">
					</div>
					<div class="col-md-2 col-xs-2 mt-1 mb-1">
						<a class="btn btn-sm btn_icon" aria-label="Left Align"> <span
							class="fas fa-search" id="click"></span>
						</a>
					</div>
					<table style="overflow: auto;" id="tableFindSub"
						class="table table-sm table_time">
						<colgroup>
							<col width="5%" />
							<col width="5%" />
							<col width="30%" />
							<col width="10%" />
							<col width="20%" />
							<col width="15%" />
							<col width="15%" />
						</colgroup>
						<thead>
							<tr>
								<th class="text-center">NO</th>
								<th class="text-center" style="border-left: 1px solid #e5e5e5;">CK</th>
								<th class="text-center" style="border-left: 1px solid #e5e5e5;">과목명</th>
								<th class="text-center" style="border-left: 1px solid #e5e5e5;">요일</th>
								<th class="text-center" style="border-left: 1px solid #e5e5e5;">시간</th>
								<th class="text-center" style="border-left: 1px solid #e5e5e5;">장소</th>
								<th class="text-center" style="border-left: 1px solid #e5e5e5;">교수명</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>

					<div class="col-md-6 col-xs-6">
						<button class="btn btn-sm btn-block btn_submit" id="submit">
							OK</button>
					</div>
					<div class="col-md-6 col-xs-6">
						<button class="btn btn-sm btn-block btn_cancel" id="cancel">CANCEL</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="hiddenAssign" name="hide" value="">
	<input type="hidden" id="hiddenSubjectKey" name="hide" value="">
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/common/func_findSubject.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_assignment.js"></script>

<script type="text/javascript">
   	$('#click').on('click',function(){ 
		$('.add_row ').remove(); //  .add_row 
		var checkList = [];
   		
		$.ajax({
   			url:"/subject/searchSubject.json",
   			type : "GET",
   			data : {
   				'word':$('#subjectName').val(),
   				'select':0
   			},
   			success : function(result){
           		if(result['result'] === "no data"){ 
           			alert('없는 과목입니다.');
           		}else{ 
  					console.log(result);
           			var	tableLen =result['result'].length
           			var table = document.getElementById("tableFindSub"),rIndex,cIndex;
           			var cell = new Array();
          	  		for(var i=0 ; i <tableLen ; i++){
           				newRow = table.insertRow(table.length),
           				cell[0] = newRow.insertCell(0),
           				cell[1] = newRow.insertCell(1),
           				cell[2] = newRow.insertCell(2),
           				cell[3] = newRow.insertCell(3),
						cell[4] = newRow.insertCell(4),
						cell[5] = newRow.insertCell(5),
						cell[6] = newRow.insertCell(6),
           				cell[0].innerHTML = i+1;
           				cell[1].innerHTML = '<input type="radio" id="radio'+(i+1)+'" name="select" data-sub-name="'+ result['result'][i]['subName'] +'" data-sub-no="'+result['result'][i]['subNo']+'" data-subject-key="'+result['result'][i]['subjectKey']+'"/>';
           				cell[2].innerHTML = result['result'][i]['subName'];
           				cell[3].innerHTML = result['result'][i]['day'];
           				cell[4].innerHTML = result['result'][i]['startHour'];
						cell[5].innerHTML = result['result'][i]['classroom'];
           				cell[6].innerHTML = result['result'][i]['profName'];
							
          	 			$(newRow).addClass("stripe_border_top");
						$(newRow).addClass("add_row");
           				for(var j=0 ; j<6 ; j++)
           					$(cell[j]).css("border-right","1px solid #e5e5e5");
          	  		}
           		}
           		
           		var table = document.getElementById("tableFindSub"),rIndex,cIndex;
        		
        		for(var i = 1 ; i < table.rows.length ; i++){
        			for(var j = 1 ; j<table.rows[i].cells.length ; j++){
        				table.rows[i].cells[j].onclick = function(){
        					rIndex = this.parentElement.rowIndex;
        					cIndex = this.cellIndex;
        					
        					$('input:radio[id="radio'+rIndex+'"]:radio[name="select"]').prop("checked", true);
        					
        					var subName = $("#radio"+rIndex).data('subName');
        					var subNo = $("#radio"+rIndex).data('subNo');
        					var subjectKey = $("#radio"+rIndex).data('subjectKey');
        					
        					$('#subjectName').val(subName).trigger('change');
        					$('#hiddenAssign').val(subNo).trigger('change');
        					$('#hiddenSubjectKey').val(subjectKey).trigger('change');
        				}
        			}
        		}
         	},
         	error : function(request,status,error){
        		alert('검색 에러');
        		console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
        	}
		});
    });
</script>

<script type="text/javascript">	
	var page = '${page}'; 
	var title = '${title}'; 
	var dueDate = '${dueDate}'; 
	var importance = '${importance}'; 
	var contents = '${contents}'; 
	var assignNo = '${assignNo}'; 
	
	$('#submit').on('click',function(){ 
		$.ajax({
   			url:"/subject/searchSubject.json",
   			type : "GET",
   			data : {
   				'word':$('#subjectName').val(),
   				'select':0
   			},
   			success : function(result){
           		if(result['result'] === "no data"){ 
           			alert('없는 과목입니다.');
           		}else{
           			href(page);
           		}
         	},
         	error : function(request,status,error){
        		alert('검색 에러');
        		console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
        	}
		});
	});
	
	$('#cancel').on('click',function(){ 
		href(page);
	});
	
	function href(page){
		if(page == 'assignmentAdd'){
			page="/assignment_add"
			locateAssign(page,title,dueDate,importance,contents,assignNo,$('#hiddenAssign').val(),$('#hiddenSubjectKey').val());
		}
		else if (page == 'assignmentAddTeam'){
			page="/assignment_add_team"
			locateAssign(page,title,dueDate,importance,contents,assignNo,$('#hiddenAssign').val(),$('#hiddenSubjectKey').val());
		}
		else if (page == 'timetablePage'){
			location.href="/timetable_page?subjectKey="+$('#hiddenSubjectKey').val();
		}
	}
</script>

