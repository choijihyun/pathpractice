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
	<title>announcement</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_announcement.css">


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
						<!-- log-out 아이콘 버튼 누르면 assignment_add로 이동 -->
						<div class= "col-2 col-xs-2 col-sm-2 col-lg-2 col-md-2">
							
							
							
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
	
										<!-- 	<div class="modal-footer"> </div> -->
										</div>
									</div>
								</div>
						</div>
					</div>

					<!-- 과제 전체 보여주는 영역(스트롤바 포함¨) -->
					<!-- 과제가 여러개 이면 스크롤 생김 -->
					<div style="overflow:auto; width:auto; height:300px;" class="mt-3 scrollbar scrollbar-track scrollbar-thumb" data-offset="0">

						<!-- 스크롤바 제외한 과제를 보여주는 영역 -->
						<div class="px-2 content_show_assign">
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
					
											<div class="col-md-3 col-xs-3 my-2 label_input">공지 날짜</div>
											<div class="col-md-7 col-xs-7">
												<input type="text" class="form-control form-control-sm mt-1 mb-1 flat_input" title="날짜" id="date"  disabled="true">
											</div>
											
											<div class="col-md-3 col-xs-3 my-2 label_input">내용</div>
											<div class="col-md-7 col-xs-7">
												<input type="text" class="form-control form-control-sm mt-1 mb-1 flat_input" title="내용" id="contexts"  disabled="true">
											</div>
										</div>
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
<script src="${pageContext.request.contextPath}/resources/js/common/func_announcement.js"></script>


<!-- 모든 과제 불러오기,띄우기 -->
<script type="text/javascript">
	$(document).ready(function(){
		var subject = '${subject}';
		//이거 주호한테 url에서 parameter로 가져오는거 안되냐고 물어보기!!!
		//주호가 보고 고쳤음><ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
		<% String id = (String)session.getAttribute("id"); %>
		alert("subect : "+subject);
		showAllAnnounce(<%=id%>,"고급C프로그래밍및실습");
	});
</script>
