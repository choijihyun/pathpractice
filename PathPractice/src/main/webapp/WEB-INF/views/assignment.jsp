<%@page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="kr">
<head>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("/");
%>
	<title>assignment</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Customize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_assignment.css">

	<!-- fontawesome Ã¬ÂÂ¼Ã«Â¡Â icon Ã¬ÂÂ¬Ã¬ÂÂ©Ã­ÂÂÃªÂ¸Â° -->
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

				<!-- header (ìë¨ë°) class="app-header" -->
				<div id="header"></div>

				<!-- ìë¨ë°ì íë¨ë°ë¥¼ ì ì¸í ë¶ë¶ class="main-area" -->
				<div class="jumbotron m-2 p-0 text-white rounded main_area">

					<!--  ê°ì¸ì¸ì§ ííì¸ì§,,, ì¶ê° ë± ì¤ì  ë²í¼ë¤ ìë ìì­-->
					<div class="row col-auto justify-content-end setting">
						<div class= "p-0 col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
							<div class="btn-group" role="group" aria-label="Basic example">
								<button type="button" class="btn btn-sm btn_assign_type">Team</button>
								<button type="button" class="btn btn-sm btn_assign_type">Non</button>
							</div>
						</div>
						<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
						</div>
						<!-- log-out ìì´ì½ ë²í¼ ëë¥´ë©´ assignment_add.htmlë¡ ì´ë -->
						<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">
							<a class="btn btn-lg btn_add p-0" aria-label="Left Align" href="/assignment_add">
								<span class="fas fa-plus-square"></span>
							</a>
							<button class="btn btn-lg btn_add p-0" aria-label="Left Align">
								<span class="fas fa-minus-square"></span>
							</button>
						</div>
					</div>

					<!-- ê³¼ì ì ì²´ ë³´ì¬ì£¼ë ìì­(ì¤í¬ë¡¤ë° í¬í¨) -->
					<!-- ê³¼ì ê° ì¬ë¬ê° ì´ë©´ ì¤í¬ë¡¤ ìê¹ -->
					<div style="overflow:auto; width:auto; height:300px;" class="mt-3 scrollbar scrollbar-track scrollbar-thumb" data-offset="0">

						<!-- ì¤í¬ë¡¤ë° ì ì¸í ê³¼ì ë¤ ë³´ì¬ì£¼ë ìì­ -->
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
											<button type="button" id='assignDel' class="btn btn-sm btn-danger" data-dismiss="modal">Delete</button>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

				<!-- footer íë¨ë° class="app-footer" -->
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
			var body = '';
			var title = $(this).data('title');
			var dueDate = $(this).data('dueDate');
			var importance = $(this).data('importance');
			var contents = $(this).data('contents');
			var subNo = $(this).data('subNo');
			
			$('#hiddenAssign').val(assignNo).trigger('change');

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


<!-- ê³¼ì  ì­ì  ë²í¼ í´ë¦­-->
<script type="text/javascript">
	$('#assignDel').on('click', function (){
		<%
			String id = (String)session.getAttribute("id");
		%>
		var assignNo = $('#hiddenAssign').val();
		event.preventDefault();
		$.ajax({
			url:"/homework/deleteHomework.json",
			type : "GET",
			data : {
				'stuId':<%=id%>,
				'assignNo': assignNo
			},
			success : function(result){
				if(result['result'] === "1"){
					alert('ì­ì  ì±ê³µ');
					console.log(result);
				}else{
					alert('ì­ì  ì¤í¨');
				}
			},
			error : function(){
				alert('ì­ì  ìë¬');
			}
		});
	});
</script>

<!-- íë©´ì ê³¼ì  íì -->
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url:"/homework/selectHomework.json",
			type : "GET",
			data : {
				'stuId':<%=id%>,
				'select':1
			},
			success : function(result){
				if(result['result'] === 'no data'){
					alert('ë±ë¡ë ê³¼ì  ìì');
				}else{
					alert('ë¶ë¬ì¤ê¸° ì±ê³µ');
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
				alert('ë¶ë¬ì¤ê¸° ìë¬');
			}
		});
	});
</script>


<script src="${pageContext.request.contextPath}/resources/js/common/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/footer.js"></script>
