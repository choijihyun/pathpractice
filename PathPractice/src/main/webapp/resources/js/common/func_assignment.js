var deleteAssign = function(assignNo,id){
	$.ajax({
		url:"/homework/deleteHomework.json",
		type : "GET",
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

var updateAssign = function(location,title,dueDate,importance,contents,assignNo,subNo){

	console.log("123123");
	location.href=location+"?title="+title
		+"&dueDate="+dueDate
		+"&importance="+importance
		+"&contents="+contents
		+"&assignNo="+assignNo
		+"&subNo="+subNo;
	
	console.log(location+"?title="+title
			+"&dueDate="+dueDate
			+"&importance="+importance
			+"&contents="+contents
			+"&assignNo="+assignNo
			+"&subNo="+subNo);
	console.log("123123");
}

var showAllAsignment = function(id){
	
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


var fillInfomation = function(subNo,select,title,contents){
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
	   			//alert('검색하려는 과목 없음.');
	   		}else{
					subName = result['result'][0]['subName'];
					$("#subjectName").val(subName);
	   		}
	 	},
	 	error : function(request,status,error){
			//alert('검색 에러');
			console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	});
	
}
