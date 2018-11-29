var deleteAssign = function(assignNo,id){
	$.ajax({
		url:"/homework/deleteHomework.json",
		type : "GET",
		async : false,
		data : {
			'stuId':id,
			'assignNo': assignNo
		},
		success : function(result){
			if(result['result'] === "1"){ 
				console.log(result);
			}else{
				alert('삭제 실패');
			}
		},
		error : function(){
			alert('삭제 에러');
		}
	});//ajax

	location.reload();
}

var completeAssign = function(dueDate,importance,title,contents,subNo,id,assignNo,success,team){
	$.ajax({
		url:"/homework/updateHomework.json",
		type : "GET",
		data : {
			'dueDate': dueDate,
			'importance': importance,
			'title': title,
			'contents': contents,
			'subNo': subNo,
			'stuId': id,
			'assignNo': assignNo,
			'success': 1,
			'team': team
		},
		success : function(result){
			if(result['result'] === "1"){ 
				console.log(result);
				location.reload();
			}else{
				alert('과제완료 실패');
			}
		},
		error : function(){
			alert('과제완료 에러');
		}
	});//ajax
}

var locateUpdateAssign = function(location,title,dueDate,importance,contents,assignNo,subNo){

	window.location.href=location+"?title="+title
		+"&dueDate="+dueDate
		+"&importance="+importance
		+"&contents="+contents
		+"&assignNo="+assignNo
		+"&subNo="+subNo;
}

var updateAssign = function(due,radioVal,title,contents,subNo,id,success,team){
	$.ajax({
		url:"/homework/updateHomework.json",
		type : "GET",
		data : {
			'dueDate': due,
			'importance': radioVal,
			'title': title,
			'contents': contents,
			'subNo': subNo,
			'stuId': id,
			'assignNo': assignNo,
			'success': success,
			'team': team
		},
		success : function(result) {
			console.log(result);
			if (result['result'] === '1') {
				location.href = "/assignment";
			} else {
				alert('과제수정실패');
			}
		},
		error : function(request,status,error){
    		alert('과제수정에러');
    		console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
    	}
	});//ajax
	
}

var insertNewHomework = function(id,radioVal,due,title,contents,success,subNo,team){
	
	$.ajax({
		url : "/homework/insertHomework.json",
		type : "GET",
		data : {
			'stuId' : id,
			'importance' : radioVal,
			'dueDate' : due,
			'title' : title,
			'contents' : contents,
			'success' : success,
			'subNo' : subNo,
			'team' : team
		},
		success : function(result) {
			console.log(result);
			if (result['result'] === '1') {
				location.href = "/assignment";
			} else {
				alert('과제등록실패');
			}
		},
		error : function() {
			alert('과제등록에러');
		}
	}); //ajax

}


var showAllAssignment = function(id){
	$.ajax({
		url:"/homework/selectHomework.json",
		type : "GET",
		data : {
			'stuId':id
		},
		success : function(result){
			if(result['result'] === 'no data'){
				alert('등록과제 없음');
			}else{ 
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
					str += 'data-sub-no= " ' + result['result'][i]['subNo'] + ' " ';
					str += 'data-team= " ' + result['result'][i]['team'] + ' " ';
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
	});//ajax
}

var showTeamAssignment = function(id){
	event.preventDefault();
	$.ajax({
		url:"/homework/selectTeamHomework.json",
		type : "GET",
		data : {
			'stuId':id
		},
		success : function(result){
			if(result['result'] === 'no data'){
				alert('등록과제 없음');
			}else{ 
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
					str += 'data-sub-no= " ' + result['result'][i]['subNo'] + ' " ';
					str += 'data-team= " ' + result['result'][i]['team'] + ' " ';
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
	});//ajax
}

var showSuccessAssignment = function(id){
	event.preventDefault();
	$.ajax({
		url:"/homework/selectSuccessHomework.json",
		type : "GET",
		data : {
			'stuId':id
		},
		success : function(result){
			if(result['result'] === 'no data'){
				alert('등록과제 없음');
			}else{ 
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
					str += 'data-sub-no= " ' + result['result'][i]['subNo'] + ' " ';
					str += 'data-team= " ' + result['result'][i]['team'] + ' " ';
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
	});//ajax
}

var showNotSuccessAssignment = function(id){
	event.preventDefault();
	$.ajax({
		url:"/homework/selectNotSuccessHomework.json",
		type : "GET",
		data : {
			'stuId':id
		},
		success : function(result){
			if(result['result'] === 'no data'){
				alert('등록과제 없음');
			}else{ 
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
					str += 'data-sub-no= " ' + result['result'][i]['subNo'] + ' " ';
					str += 'data-team= " ' + result['result'][i]['team'] + ' " ';
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
	});//ajax
}


var fillInfomation = function(subNo,select,title,contents,dueDate,importance){
	$("#title").val(title);
	$("#contents").val(contents);
	
	$.ajax({
			url:"/subject/searchSubject.json",
			type : "GET",
			data : {
				'word':subNo,
				'select':select
			},
			success : function(result){
	   		if(result['result'] === "no data"){ 
	   			alert('검색하려는 과목 없음.');
	   		}else{
					subName = result['result'][0]['subName'];
					$("#subjectName").val(subName);
	   		}
	 	},
	 	error : function(request,status,error){
			alert('검색 에러');
			console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	});
	
	//setting datepicker
	$(function() {
		$("#dueDate").datepicker({ 
			changeMonth: true, 
	        changeYear: true,
	        dayNames: ['월', '화', '수', '목', '금', '토', '일'], 
	        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dateFormat : "yy-mm-dd"
		});
		
		strArray = dueDate.split('-');
		realDate = new Date(strArray[0], strArray[1]-1, strArray[2]); 
		$("#dueDate").datepicker('setDate',realDate);
	});
	
	//setting importance(star)
	for(var i=1 ; i<=importance ; i++){
		$('input:radio[id="p'+i+'"]:radio[name="star-input"]').prop("checked", true);
	}
	
}
