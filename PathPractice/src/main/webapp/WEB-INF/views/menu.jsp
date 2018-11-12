<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
	<title>menu</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_menu.css">

	<!-- fontawesome ì¼ë¡ icon ì¬ì©íê¸° -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</head>

<body class="text-center">

	<!-- Container -->
	<div
		class="container m-0 p-0 justify-content-md-center justify-content-xs-center">
		<div class="login_box ">

			<!-- header (상단바)-->
			<div id="header"></div>

			<!-- 상단바와 하단바를 제외한 부분 class="main-area" -->
			<div class="jumbotron p-md-3 text-white rounded main_area">

				<div class="d-flex align-items-center p-3 my-3 rounded"
					style="background: #f2f2f2;">
					<div class="lh-100">
						<div style="text-align: center">
							<strong class="info" style="text-align: center"> 공지사항 </strong>
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
									<button type="button" id='assignChange' class="btn btn-sm btn-primary " aria-label="Left Align">Change</button>
									<button type="button" id='assignComplete' class="btn btn-sm btn-primary" data-dismiss="modal">Complete</button>
									<button type="button" id='assignDel' class="btn btn-sm btn-danger" data-dismiss="modal">Delete</button>
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
</body>
</html>

<!-- modal -->
<script type="text/javascript">
	$(function(){
		$(document).on("click",'.btn_pop_assignment',function() {
			var body = '';
			var title = $(this).data('title');
			var dueDate = $(this).data('dueDate');
			var importance = $(this).data('importance');
			var contents = $(this).data('contents');
			var subNo = $(this).data('subNo');
			var assignNo = $(this).data('assignNo');
			
			$('#hiddenAssign').val(assignNo).trigger('change');
			$('#hiddenSub').val(subNo).trigger('change');

			body += 'due-date=' + dueDate;
			body += ' , importance=' + importance;
			body += ' , contents=' + contents;

			$('.modal-title').text(title);
			$('.modal-body').text(body);
			$('div.modal').modal();
			
			//modify assignment
			$('#assignChange').on('click', function (){
				location.href="/assignment_add?title="+title
				+"&dueDate="+dueDate
				+"&importance="+importance
				+"&contents="+contents
				+"&subNo="+subNo;
			});
		});
	})
</script>

<!-- 과제 가져오기? -->
<script type="text/javascript">
	var file = document.querySelector('#getfile');

	file.onchange = function() {

		var fileList = file.files;

		var reader = new FileReader();

		reader.readAsText(fileList[0]);

		reader.onload = function() {

			document.querySelector('#preview').textContent = reader.result;

		};

	};
</script>

<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
