<!doctype html>
<html lang="kr">
<head>
	<title>assignment</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="css/style_assignment.css">

	<!-- fontawesome 으로 icon 사용하기 -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</head>

<body class="text-center">

	<!-- Container -->
	<div class="container">
		<div class="row justify-content-md-center justify-content-xs-center row justify-content-sm-center justify-content-lg-center">
			<div class="col col-md-6 col-md-auto col-xs-6 col-xs-auto col-sm-6 col-sm-auto col-lg-6 col-lg-auto login_box ">

				<!-- header (상단바) class="app-header" -->
				<div id="header"></div>

				<!-- 상단바와 하단바를 제외한 부분 class="main-area" -->
				<div class="jumbotron m-2 p-0 text-white rounded main_area">

					<!--  개인인지 팀플인지,,, 추가 등 설정 버튼들 있는 영역-->
					<div class="row col-auto justify-content-end setting">
						<div class= "p-0 col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
							<div class="btn-group" role="group" aria-label="Basic example">
								<button type="button" class="btn btn-sm btn_assign_type">Team</button>
								<button type="button" class="btn btn-sm btn_assign_type">Non</button>
							</div>
						</div>
						<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
						</div>
						<!-- log-out 아이콘 버튼 누르면 assignment_add.html로 이동 -->
						<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
							<a class="btn btn-lg btn_add p-0" aria-label="Left Align" href="assignment_add.html">
								<span class="fas fa-plus-square"></span>
							</a>
							<button class="btn btn-lg btn_add p-0" aria-label="Left Align">
								<span class="fas fa-minus-square"></span>
							</button>
						</div>
					</div>

					<!-- 과제전체 보여주는 영역(스크롤바 포함) -->
					<!-- 과제가 여러개 이면 스크롤 생김 -->
					<div style="overflow:auto; width:auto; height:300px;" class="mt-3 scrollbar scrollbar-track scrollbar-thumb" data-offset="0">

						<!-- 스크롤바 제외한 과제들 보여주는 영역 -->
						<div class="px-2 content_show_assign">
							<!-- Modal -->
							<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered modal-sm" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title"></h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body"></div>

										<div class="modal-footer">
											<a class="btn btn-sm btn-primary " aria-label="Left Align" href="assignment_add.html">Change</a>
											<button type="button" id='assignDel' class="btn btn-sm btn-danger" data-dismiss="modal">Delete</button>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

				<!-- footer 하단바 class="app-footer" -->
				<div id="footer"></div>

			</div>
		</div>
	</div>
	<input type="hidden" id="hiddenAssign" name="hide" value="" >
</body>
</html>

<!-- modal-->
<script type="text/javascript">
	$(function(){
		$(document).on("click",'.btn_pop_assignment',function() {
			var title = $(this).data('title');
			var body = '';

			var assignNo = $(this).data('assignNo');
			$('#hiddenAssign').val(assignNo).trigger('change');

			body += 'due-date=' + $(this).data('dueDate');
			body += ' , importance=' + $(this).data('importance');
			body += ' , contents=' + $(this).data('contents');

			$('.modal-title').text(title);
			$('.modal-body').text(body);

			$('div.modal').modal();
		});

	})
</script>


<!-- 과제 삭제 버튼 클릭-->
<script type="text/javascript">
	$('#assignDel').on('click', function (){
		var assignNo = $('#hiddenAssign').val();
		event.preventDefault();
		$.ajax({
			url:"http://localhost:8090/homework/deleteHomework.json",
			type : "GET",
			data : {
				'stuId':'1',
				'assignNo': assignNo
			},
			success : function(result){
				if(result['result'] === "1"){
					alert('삭제 성공');
					console.log(result);
				}else{
					alert('삭제 실패');
				}
			},
			error : function(){
				alert('삭제 에러');
			}
		});
	});
</script>

<!-- 화면에 과제 표시 -->
<script type="text/javascript">
	$(document).ready(function(){

		$.ajax({
			url:"http://localhost:8090/homework/selectHomework.json",
			type : "GET",
			data : {
				'stuId':'1',
				'select':1
			},
			success : function(result){
				if(result['result'] === 'no data'){
					alert('불러오기 실패');
				}else{
					alert('불러오기 성공');
					console.log(result);

					for(var i=0 ; i<result['result'].length ; i++){

						var assign_title = result['result'][i]['title'];
						var assign_contents = result['result'][i]['contents'];

						var str = '';
						str += '<button type="button" ';
						str += 'class="btn btn-lg btn-block btn-outline-danger btn_pop_assignment" ';
						str += 'data-due-date= " ' + result['result'][i]['dueDate'] + ' " ';
						str += 'data-importance= " ' + result['result'][i]['importance'] + ' " ';
						str += 'data-title= " ' + assign_title + ' " ';
						str += 'data-assign-no= " ' + result['result'][i]['assignNo'] + ' " ';
						str += 'data-contents= " ' + assign_contents + '">';
						str += '<h6 id="assign' + (i+1) + 'Title" ';
						str += 'style="font-weight: bold" class="mb-2">-' + assign_title + '</h6>';
						str += '<p6 id="assign' + (i+1) + 'Context">' + assign_contents + '</p6>';
						str += '</button>';
						$('.content_show_assign').append(str);
					}
				}

			},
			error : function(){
				alert('불러오기 에러');
			}
		});
	});
</script>


<script src="js/common/header.js"></script>
<script src="js/common/footer.js"></script>
