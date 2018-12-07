var showAllAnnounce = function(id, subject){
	$.ajax({
		url:"/blackboard/getAnnounce.json",
		type : "GET",
		data : {
			'stuId':id,
			'subject':subject
		},
		success : function(result){
			if(result['result'] === 'no data'){
				alert('등록과제 없음');
			}else{
				console.log(result);
				for(var i=0 ; i<result['result'].length ; i++){

					var announce_date = result['result'][i]['date'];
					var announce_contest = result['result'][i]['contest'];

					var str = '';
					str += '<button type="button" ';
					str += 'class="btn btn-lg btn-block btn-outline-danger btn_pop_announce" ';
					str += 'data-date= " ' + result['result'][i]['date'] + ' " ';
					str += 'data-team= " ' + result['result'][i]['contest'] + ' " ';
					str += 'data-contents= " ' + announce_contest + '">';
					str += '<h6 id="assign' + (i+1) + 'Title" ';
					str += 'style="font-weight: bold" class="mb-2">-' + announce_date + '</h6>';
					str += '<p6 id="assign' + (i+1) + 'Context">' + announce_contest + '</p6>';
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
