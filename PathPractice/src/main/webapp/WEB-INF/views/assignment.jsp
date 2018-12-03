<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
<style>
.star-input>.input,
.star-input>.input>input:checked+label{display: inline-block;vertical-align:top;background:url('${pageContext.request.contextPath}/resources/img/grade_img.png')no-repeat;}
.star-input{display:inline-block; white-space:nowrap;width:225px;height:20px;line-height:10px;}
.star-input>.input{display:inline-block;width:150px;background-size:150px;height:28px;white-space:nowrap;overflow:hidden;position: relative;}
.star-input>.input>input{position:absolute;width:1px;height:1px;opacity:0;}
.star-input>.input>label{width:30px;height:0;padding:28px 0 0 0;overflow: hidden;float:left;position: absolute;top: 0;left: 0;}
.star-input>.input>input:checked+label{background-size: 150px;background-position: 0 bottom;}
.star-input>.input>label[for="p1"]{width:30px;z-index:5;}
.star-input>.input>label[for="p2"]{width:60px;z-index:4;}
.star-input>.input>label[for="p3"]{width:90px;z-index:3;}
.star-input>.input>label[for="p4"]{width:120px;z-index:2;}
.star-input>.input>label[for="p5"]{width:150px;z-index:1;}
.star-input>output{display:inline-block;width:60px; font-size:18px;text-align:right; vertical-align:middle;}
.form-row>.col, .form-row>[class*=col-]{padding-top : 5px;}
</style>

<head>
	<title>assignment</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_assignment.css">


	<!-- fontawesome img -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</head>

<body class="text-center">

	<!-- Container -->
			<div class="container m-0 p-0 justify-content-md-center justify-content-xs-center">
			<div class="login_box">

				<!-- header  class="app-header" -->
				<div id="header"></div>

				<!-- 상당바와 하단바를 제외한 부분 class="main-area" -->
				<div class="jumbotron m-2 p-0 text-white rounded main_area">

					<!-- 개인인지 팀플인지 추가 등 설정 버튼들 있는 곳­-->
					<div class="row col-auto justify-content-end setting">
						<div class= "p-0 col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
							<div class="btn-group" role="group" aria-label="Basic example">
								<button type="button" class="btn  btn_assign_type" id="all">ALL</button>
								<button type="button" class="btn  btn_assign_type" id="none">Personal</button>
								<button type="button" class="btn  btn_assign_type" id="team">Team</button>
								<button type="button" class="btn  btn_assign_type" id="done">Done</button>
							</div>
						</div>
						<div class= "col-6 col-xs-6 col-sm-6 col-lg-6 col-md-6">
						</div>
						<!-- log-out 아이콘 버튼 누르면 assignment_add로 이동 -->
						<div class= "col-2 col-xs-2 col-sm-2 col-lg-2 col-md-2">
							<button class="btn btn-lg btn_add p-0" aria-label="Left Align" >
								<span class="fas fa-plus-square"></span>
							</button>
							
							
							<!-- Modal_addBtn -->
								<div class="modal btn_modal fade" tabindex="-1" role="dialog" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered modal-sm" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title btn-modal-title"> CHOOSE </h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body btn-modal-body">
												<button type="button" id='defaultAssign' class="btn btn-sm btn-primary " aria-label="Left Align">assign</button>
												<button type="button" id='teamAssign' class="btn btn-sm btn-info" data-dismiss="modal">team assign</button>
											</div>
	
										</div>
									</div>
								</div>
						</div>
					</div>

					<!-- 과제 전체 보여주는 영역(스트롤바 포함¨) -->
					<!-- 과제가 여러개 이면 스크롤 생김 -->
					<div style="overflow-x:hidden; overflow-y:auto; width:auto; height:300px;" class="mt-3 scrollbar scrollbar-track scrollbar-thumb" data-offset="0">

						<!-- 스크롤바 제외한 과제를 보여주는 영역 -->
						<div class="px-2 content_show_assign">
						
						</div>
						
						<!-- Modal -->
						<div class="modal assign_modal fade" tabindex="-1" role="dialog" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title assign-modal-title"></h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body assign-modal-body row">
					
											<div class="col-md-3 col-xs-3 my-2 label_input">내용</div>
											<div class="col-md-7 col-xs-7">
												<input type="text" class="form-control form-control-sm mt-1 mb-1 flat_input" title="내용" id="contents"  disabled="true">
											</div>
											
											<div class="col-md-3 col-xs-3 my-2 label_input">마감일</div>
											<div class="col-md-7 col-xs-7">
												<input type="text" class="form-control form-control-sm mt-1 mb-1 flat_input" title="마감일" id="dueDate"  disabled="true">
											</div>
											
											<div class="col-md-3 col-xs-3 m-0 label_input my-2">중요도</div>
											<span class="star-input col-md-7 col-xs-7 my-2" id="importance"> <span class="input">
											<input type="radio" name="star-input" value="1" id="p1" disabled="true"> <label for="p1">1</label>
											<input type="radio" name="star-input" value="2" id="p2" disabled="true"> <label for="p2">2</label>
											<input type="radio" name="star-input" value="3" id="p3" disabled="true"> <label for="p3">3</label> 
											<input type="radio" name="star-input" value="4" id="p4" disabled="true"> <label for="p4">4</label> 
											<input type="radio" name="star-input" value="5" id="p5" disabled="true"> <label for="p5">5</label>
											
										</div>

										<div class="modal-footer">
											<button type="button" id='assignChange' class="btn btn-sm btn-info"  data-dismiss="modal">Change</button>
											<button type="button" id='assignComplete' class="btn btn-sm btn-primary" data-dismiss="modal">Complete</button>
											<button type="button" id='assignDel' class="btn btn-sm btn-danger" data-dismiss="modal">Delete</button>
										</div>
									</div>
								</div>
							</div>
							
					</div>
				</div>

				<!-- footer 하단바 class="app-footer" -->
				<div id="footer"></div>

			</div>
	</div>
	<input type="hidden" id="hiddenAssign" name="hide" value="" >
	<input type="hidden" id="hiddenSub" name="hide" value="" >
</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/func_assignment.js"></script>

<script type="text/javascript" scr="func_assignment.js">
<% String id = (String)session.getAttribute("id"); %>
	$('#all').on('click', function (){
		$('.content_show_assign').empty();
		showAllAssignment(<%=id%>);
	});
	$('#team').on('click', function (){
		$('.content_show_assign').empty();
		showTeamAssignment(<%=id%>);
	});
	$('#none').on('click', function (){ 
		$('.content_show_assign').empty();
		showNotSuccessAssignment(<%=id%>);
	});
	$('#done').on('click', function (){ 
		$('.content_show_assign').empty();
		showSuccessAssignment(<%=id%>);
	});
	
</script>

<!-- modal-->
<script type="text/javascript">
	$(function(){
		$(document).on("click",'.btn_add',function() {		
			$('div.btn_modal').modal();
			
			$('#defaultAssign').on('click', function (){
				location.href="/assignment_add";
			}); 
			
			$('#teamAssign').on('click', function (){
				location.href="/assignment_add_team";
			}); 

		});//btn_pop_assignment Click
	})//function
</script>

<!-- modal-->
<script type="text/javascript">

	$(document).on("click",'.btn_pop_assignment',function() {
		var body = '';
		var location='';
		var title = $(this).data('title');
		var dueDate = $(this).data('dueDate');
		var importance = $(this).data('importance');
		var contents = $(this).data('contents');
		var subNo = $(this).data('subNo');
		var assignNo = $(this).data('assignNo');
		var team = $(this).data('team');
			
		$('#hiddenAssign').val(assignNo).trigger('change');
		$('#hiddenSub').val(subNo).trigger('change');

		$("#dueDate").val(dueDate)
		$("#contents").val(contents);
		$('.assign-modal-title').text(title);
		$('div.assign_modal').modal();
			
		//중요도 색칠
		for(var i=1 ; i<=importance ; i++)
		 $('input:radio[id="p'+i+'"]:radio[name="star-input"]').prop("checked", true);
			 
		//과제 삭제 버튼 클릭  
		$('#assignDel').on('click', function (){
			var assignNo = $('#hiddenAssign').val();
			deleteAssign(assignNo,<%=id%>);
		});//assignDel Cllick

		//과제 완료버튼 클릭 
		$('#assignComplete').on('click', function (){ 
			completeAssign(dueDate,importance,title,contents,subNo,<%=id%>,assignNo,1,team); 
		});//assignComplete Click

		//과제 수정버튼 클릭 
		$('#assignChange').on('click', function (){ 
			 if(team == 0){
				location = "/assignment_add"; 
			 }
			else {
				location = "/assignment_add_team"; 
			}  
			locateUpdateAssign(location,title,dueDate,importance,contents,assignNo,subNo);
		});//assignChange Cllick
			
	});//btn_pop_assignment Click
</script>

<!-- 모든 과제 불러오기,띄우기 -->
<script type="text/javascript">
	$(document).ready(function(){
		showAllAssignment(<%=id%>);
	});
</script>
