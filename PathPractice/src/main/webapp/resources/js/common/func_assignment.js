var addmember = function( positionId, positionName, where){
	//check user is exist	
	var idObj = $(positionId);
	var nameObj =  $(positionName);
	if( !( idObj.val() == '') && !(nameObj.val() == '') ){
		
		var who='btn_del_member';
		if(positionId == ".reader-id"){
			$(positionId).prop('disabled',true);
			$(positionName).prop('disabled',true);
			who="btn_del_reader";
		}
		
		//실제로 존재하는 사용자 인지도 확인 해주기(Student table의 name,stuId로 보면 됨)
		var str = '';
		str += '<div class="input-group-prepend mb-1 btn-parent">';
		str += '<button class="' +who+' btn btn-sm btn-outline-danger block "';
		str += 'type="button">';
		str += 'DEL</button>';
		str += '<input type="text" disabled="true"';
		str += 'class="col-6 form-control form-control-sm flat_input userId my-0 mx-1"';
		str += 'value="'+ idObj.val() +'" >';
		str += '<input type="text" disabled="true"';
		str += 'class="col-6 form-control form-control-sm flat_input userName my-0 mx-1"';
		str += 'value="'+ nameObj.val() +'" >';
		str += '</div>';
				
		idObj.val('').trigger('change');
		nameObj.val('').trigger('change');
		$(where).append(str);
			
	}else{
		alert("팀원의 정보를 모두 입력 해 주세요");
	}
	return true;
}

var deleteAssign = function(select,no,id){

	console.log("assignNo===="+no);
	console.log("id===="+id);
	$.ajax({
		url:"/homework/deleteHomework.json",
		type : "GET",
		async : false,
		data : {
			'stuId':id,
  			'select':select,
			'assignNo': no
		},
		success : function(result){
			console.log(result);
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

var locateUpdateAssign = function(location,title,dueDate,importance,contents,assignNo,subNo,team){
	
	window.location.href=location+"?title="+title
		+"&dueDate="+dueDate
		+"&importance="+importance
		+"&contents="+contents
		+"&assignNo="+assignNo
		+"&subNo="+subNo
		+"&team="+team;
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
	console.log("func!!!============"+id);
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
				if(team == 0){
					console.log("func!!!============"+id);
					location.href = "/assignment";
				}
			} else {
				alert('과제등록실패');
			}
		},
		error : function() {
			alert('과제등록에러');
		}
	}); //ajax

}


var showAllAssignment = function(id,select,order){
	$.ajax({
		url:"/homework/selectHomework.json",
		type : "GET",
		data : {
			'stuId':id,
			'select':select,
			'order':order
			//order는 assignment페이지에서 드롭박스에서 선택 된거 보고 파라미터로 받아와서 그 값으로 바꿔주면 댕!!
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
		error : function(request,status,error){
    		alert('불러오기에러');
    		console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
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
		strArray = dueDate.split('-');
		realDate = new Date(strArray[0], strArray[1]-1, strArray[2]); 
		$("#dueDate").datepicker('setDate',realDate);
	});
	
	//setting importance(star)
	for(var i=1 ; i<=importance ; i++){
		$('input:radio[id="p'+i+'"]:radio[name="star-input"]').prop("checked", true);
	}
	
}

var fillMember = function(team){
	
	$.ajax({
			url:"/team/searchTeam.json",
			type : "GET",
			data : {
				'teamNum':team
			},
			success : function(result){
	   		if(result['result'] === "no data"){ 
	   		}else{
	   			console.log(result['result']);
	   			if(result['result'][0]['leaderName'] != null){
		   			$(".reader-id").val(result['result'][0]['leaderNum']);
		   			$(".reader-name").val(result['result'][0]['leaderName']);
	   				$('#btn_add_reader').trigger('click');
	   			}
	   			if(result['result'][0]['memOneName'] != null){
		   			$(".mem-id").val(result['result'][0]['memOneNum']);
		   			$(".mem-name").val(result['result'][0]['memOneName']);
	   				$('#btn_add_member').trigger('click');
	   			}
	   			if(result['result'][0]['memTwoName'] != null){
		   			$(".mem-id").val(result['result'][0]['memTwoNum']);
		   			$(".mem-name").val(result['result'][0]['memTwoName']);
	   				$('#btn_add_member').trigger('click');
	   			}
	   			if(result['result'][0]['memThreeName'] !=  null){
		   			$(".mem-id").val(result['result'][0]['memThreeNum']);
		   			$(".mem-name").val(result['result'][0]['memThreeName']);
	   				$('#btn_add_member').trigger('click');
	   			}
	   			if(result['result'][0]['memFourName'] != null){
		   			$(".mem-id").val(result['result'][0]['memFourNum']);
		   			$(".mem-name").val(result['result'][0]['memFourName']);
	   				$('#btn_add_member').trigger('click');
	   			}				
	   		}
	 	},
	 	error : function(request,status,error){
			alert('멤버 검색 에러');
			console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	});
		
}