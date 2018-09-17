<!doctype html>
<html lang="kr">

<head>
	<title>과목 찾기</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Costumize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_find_subject.css">

	<!-- fontawesome 으로 icon 사용하기 -->
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
	<div class="container" data-spy = "scroll">
		<div class="row justify-content-md-center justify-content-xs-center">
			<div class="col-md-6 col-md-auto col-xs-6 col-xs-auto login_box ">
				<h1 class="text-center m-0 wdi_red">FIND!</h1>
				<hr>
				<div class="jumbotron p-0 rounded main_area">

					<div class="form-row m-3">
						<div class= "col-md-3 col-xs-3 mt-1 mb-1 label_input">
							과목명
						</div>
						<div class= "col-md-7 col-xs-7 ">
							<input type="text"  class="form-control form-control-sm mt-1 mb-1 flat_input" id="subjectName">
						</div>
						<div class= "col-md-2 col-xs-2 mt-1 mb-1">
							<a  class="btn btn-sm btn_icon" aria-label="Left Align">
								<span class="fas fa-search" id="click"></span>
							</a>
						</div>
						<table style="overflow:auto;" id="tableFindSub" class="table table-sm table_time">
							<colgroup>
								<col width="10%"/>
								<col width="5%"/>
								<col width="25%"/>
								<col width="20%"/>
								<col width="20%"/>
								<col width="20%"/>
							</colgroup>
							<thead>
								<tr>
									<th class="text-center">NO</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">CK</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">과목명</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">시간</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">장소</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">교수명</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>

					<div class= "col-md-12 col-xs-12" >
						<a class="btn btn-lg btn_login" id="submit" >
							OK
						</a>
						<a class="btn btn-lg btn_login" id="submit" href="/assignment_add">
							CANCEL
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type = "hidden" id="hiddenAssign" name="hide" value="" >
	<script type="text/javascript">
		$(document).ready(function(){
	     	$('#click').on('click',function(){ //입력한 과목명 가지고 선택할 수 있게 만들어야함.(subNum 받아야함!)
				$('.add_row').empty(); // .add_row 클래스를 가진항목 지우기(테이블 내용 지우기)

				var checkList = [];

	      		event.preventDefault();
				$.ajax({
	      			url:"http://localhost:8090//subject/searchSubject.json",
	      			type : "GET",
	      			data : {
	      				'word':$('#subjectName').val()
	      			},
	      			success : function(result){
	               		if(result['result'] === 'no data'){ //과목 검색 성공
	               			alert('검색 실패');
	               		}else{
	               			alert('검색 성공');

	               			var	tableLen = result['result'].length;
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

	               				cell[0].innerHTML = i+1;
	               				cell[1].innerHTML = '<input type="radio" id="radio'+(i+1)+'" name="select" data-sub-no="'+result['result'][i]['subNo']+'">';
	               				cell[2].innerHTML = result['result'][i]['subName'];
	               				cell[3].innerHTML = result['result'][i]['startHour'];
								cell[4].innerHTML = result['result'][i]['classroom'];
	               				cell[5].innerHTML = result['result'][i]['profName'];

	              	 			$(newRow).addClass("stripe_border_top");
								$(newRow).addClass("add_row");
	               				for(var j=0 ; j<6 ; j++)
	               					$(cell[j]).css("border-right","1px solid #e5e5e5");
	             	  		}
	             		}
	         	  	},
	          	 	error : function(){
	           			alert('검색 에러');
	          	 	}
	      		});
	      	});
	  	});
	</script>
	<script type="text/javascript">
		$('#submit').on('click',function(){ //입력한 과목명 가지고 선택할 수 있게 만들어야함.(subNum 받아야함!)
			//var checkBox =
			var subNo = $('#hiddenAssign').val();

			//여기서 해야되는게 이제 subNo을 assignment_add.html로 보내야함!!!!

			//location.href="/assignment_add?"+
		 	//$.ajax({
		 	//	type : "post",
		 	//	url : "assignment_add.html",
		 	//	success : function(result){
		 	//		console.log(result);
		 	//		$('#subNo').html(result);
		 	//	}
		 	//})
		});

		//테이블 클릭하면 행,열의 인덱스 찍어줌
		var table = document.getElementById("tableFindSub"),rIndex,cIndex;

		$('#tableFindSub').on('click', function (){
			for(var i = 1 ; i < table.rows.length ; i++){
				for(var j = 0 ; j<table.rows[i].cells.length ; j++){
					table.rows[i].cells[j].onclick = function(){
						rIndex = this.parentElement.rowIndex;
						cIndex = this.cellIndex;

						console.log("ROW:" +rIndex+ ", Cell:" +cIndex);

						$('input:radio[id="radio'+rIndex+'"]:radio[name="select"]').prop("checked", true);
						var subNo = $("#radio"+rIndex).data('subNo')

						$('#hiddenAssign').val(subNo).trigger('change');
					}
				}
			}
		});

	</script>
</body>
</html>