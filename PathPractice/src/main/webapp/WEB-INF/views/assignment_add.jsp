<!doctype html>
<html lang="kr">

<style>
.star-input>.input,
.star-input>.input>label:hover,
.star-input>.input>input:focus+label,
.star-input>.input>input:checked+label{display: inline-block;vertical-align:top;background:url('img/grade_img.png')no-repeat;}
.star-input{display:inline-block; white-space:nowrap;width:225px;height:20px;/*padding:20px;*/line-height:10px;}
.star-input>.input{display:inline-block;width:150px;background-size:150px;height:28px;white-space:nowrap;overflow:hidden;position: relative;}
.star-input>.input>input{position:absolute;width:1px;height:1px;opacity:0;}
star-input>.input.focus{outline:1px dotted #ddd;}
.star-input>.input>label{width:30px;height:0;padding:28px 0 0 0;overflow: hidden;float:left;cursor: pointer;position: absolute;top: 0;left: 0;}
.star-input>.input>label:hover,
.star-input>.input>input:focus+label,
.star-input>.input>input:checked+label{background-size: 150px;background-position: 0 bottom;}
.star-input>.input>label:hover~label{background-image: none;}
.star-input>.input>label[for="p1"]{width:30px;z-index:5;}
.star-input>.input>label[for="p2"]{width:60px;z-index:4;}
.star-input>.input>label[for="p3"]{width:90px;z-index:3;}
.star-input>.input>label[for="p4"]{width:120px;z-index:2;}
.star-input>.input>label[for="p5"]{width:150px;z-index:1;}
.star-input>output{display:inline-block;width:60px; font-size:18px;text-align:right; vertical-align:middle;}
.col-md-12 {padding: 3px;}
.form-row>.col, .form-row>[class*=col-]{padding-top : 5px;}

</style>

<head>
	<title>ADD ASSINGMENT</title>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<!-- Costumize CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_assignment_add.css">

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
	<div class="container" data-spy = "scroll">
		<div class="row justify-content-md-center justify-content-xs-center">
			<div class="col-md-6 col-md-auto col-xs-6 col-xs-auto login_box  p-md-0 ">
				<h2 class="text-center wdi_red mt-1">ADD ASSINGMENT</h2>
				<hr class="m-1">
				<form class="commonForm">
					<div class="form-row m-3">
						<div class= "col-md-3 col-xs-3 my-2 label_input">
							과목명
						</div>
						<div class= "col-md-7 col-xs-7 ">
							<input type="text"  class="form-control form-control-sm mt-1 mb-1  flat_input" id="subjectName" title="ê³¼ëª©ëª">
						</div>
						<div class= "col-md-2 col-xs-2 mt-1 mb-1">
							<a  class="btn btn-sm btn_icon" aria-label="Left Align" href="/find_subject">
								<span class="fas fa-search"></span>
							</a>
						</div>
						<div class= "col-md-3 col-xs-3 my-2 label_input">
							제목
						</div>
						<div class= "col-md-7 col-xs-7">
							<input type="text"  class="form-control form-control-sm mt-1 mb-1 flat_input" title="ì ëª©" id="title">
						</div>

						<div class= "col-md-3 col-xs-3 my-2 label_input">
							내용
						</div>
						<div class= "col-md-7 col-xs-7">
							<input type="text"  class="form-control form-control-sm mt-1 mb-1 flat_input" title="ë´ì©" id="contents">
						</div>

						<div class= "col-md-3 col-xs-3 my-2 label_input">
							중요도
						</div>
						<span class="star-input col-md-7 col-xs-7 ">
							<span class="input">
								<input type="radio" name="star-input" value="1" id="p1">
								<label for="p1">1</label>
								<input type="radio" name="star-input" value="2" id="p2">
								<label for="p2">2</label>
								<input type="radio" name="star-input" value="3" id="p3">
								<label for="p3">3</label>
								<input type="radio" name="star-input" value="4" id="p4">
								<label for="p4">4</label>
								<input type="radio" name="star-input" value="5" id="p5">
								<label for="p5">5</label>
							</span>
						</span>

						<div class= "col-md-3 col-xs-3 my-2 label_input">
							마감일
						</div>
						<div class= "col-md-6 col-xs-6 mt-1 mb-1">
							<form action = "demo_form.asp">
								<input type="date" class = "col-mdname=date" id="dueDate">
							</form>
						</div>
						<div class= "col-md-3 col-xs-3 mt-1 mb-1">
						</div>

						<!--join/cancel button -->
						<div class= "col-md-6 col-xs-6">
							<button class="btn btn-sm btn-block btn_submit" id="submit">
								SUBMIT
							</button>
						</div>

						<div class= "col-md-6 col-xs-6">
							<a class="btn btn-sm btn-block btn_cancel" href="/assignment">
								CANCEL
							</a>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>


</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/common/func_check_input.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#submit').on('click',function(){

			if( !chkInput() ) return;

			event.preventDefault();

			var radioVal = $('input[name="star-input"]:checked').val();
			var due = $('#dueDate').val();
			
			$.ajax({
				url:"/homework/insertHomework.json",
				type : "GET",
				data : {
					'stuId':'16010946',
					'importance':radioVal,
					'dueDate':due,
					'title':$('#title').val(),
					'contents':$('#contents').val(),
					'success':0,
					'subNo':'111'
				},
				success : function(result){
					console.log(result);
	               if(result['result'] === '1'){ 
	               	location.href = "/assignment";
	               }else{
	               	alert('과제 등록 실패');
	               }
	           },
	           error : function(){
	           	alert('과제 등록 에러');
	           }
	       });
		});
	});
</script>


<script>
var title='${title}';
alert(title);
</script>