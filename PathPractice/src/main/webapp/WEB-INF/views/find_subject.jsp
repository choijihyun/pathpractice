<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">

<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
	<title>ê³¼ëª© ì°¾ê¸°</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Costumize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_find_subject.css">

	<!-- fontawesome Ã¬ÂÂ¼Ã«Â¡Â icon Ã¬ÂÂ¬Ã¬ÂÂ©Ã­ÂÂÃªÂ¸Â° -->
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
							ê³¼ëª©ëªÂ
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
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">ê³¼ëª©ëªÂ</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">ìê°Â</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">ì¥ìÂÂ</th>
									<th class="text-center" style="border-left: 1px solid #e5e5e5;">êµìëªÂ</th>
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
	     	$('#click').on('click',function(){ //ìë ¥í ê³¼ëª©ëª ê°ì§ê³  ì íí  ì ìê² ë§ë¤ì´ì¼í¨.(subNum ë°ìì¼í¨!)
				$('.add_row').empty(); //  .add_row í´ëì¤ë¥¼ ê°ì§í­ëª© ì§ì°ê¸°(íì´ë¸ ë´ì© ì§ì°ê¸°)

				var checkList = [];

	      		event.preventDefault();
				$.ajax({
	      			url:"/subject/searchSubject.json",
	      			type : "GET",
	      			data : {
	      				'word':$('#subjectName').val()
	      			},
	      			success : function(result){
	               		if(result['result'] === 'no data'){ //ê³¼ëª© ê²ì ì±ê³µ
	               			alert('ê²ì ì¤í¨');
	               		}else{
	               			alert('ê²ì ì±ê³µ');

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
	           			alert('ÃªÂ²ÂÃ¬ÂÂ Ã¬ÂÂÃ«ÂÂ¬');
	          	 	}
	      		});
	      	});
	  	});
	</script>
	<script type="text/javascript">
		$('#submit').on('click',function(){ //ìë ¥í ê³¼ëª©ëª ê°ì§ê³  ì íí  ì ìê² ë§ë¤ì´ì¼í¨.(subNum ë°ìì¼í¨!)
			//var checkBox =
			var subNo = $('#hiddenAssign').val();

			//ì¬ê¸°ì í´ì¼ëëê² ì´ì  subNoì assignment_add.htmlë¡ ë³´ë´ì¼í¨!!!!

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

		//íì´ë¸ í´ë¦­íë©´ í,ì´ì ì¸ë±ì¤ ì°ì´ì¤Â
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
