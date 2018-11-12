
var findSubjectInfo = function (subjectKey) {
	$.ajax({
		url:"/subject/searchSubject.json",
		type : "GET",
		data : {
			'word':subjectKey, //subNO로 subjectKey랑 과목 정보들 가져오기 //find_subject해서 url parameter로 가져옴
			'select':3
		},
		success : function(result){
   		if(result['result'] === "no data"){ 
//   			alert('없는 과목입니다.');
 		}else{
				console.log(result);
				
				subName = result['result'][0]['subName'];
				classroom = result['result'][0]['classroom'];
				profName = result['result'][0]['profName'];
				subjectKey = result['result'][0]['subjectKey'];
				startHour = result['result'][0]['startHour'];
				endHour = result['result'][0]['endHour'];
				day = result['result'][0]['day'];
				
				day1 = day.slice(0,1);
				day2 = day.slice(1,2);	
				shour = startHour.slice(0,2);//앞에2개 자르기
				sminute = startHour.slice(3,5);
				ehour = endHour.slice(0,2);
				eminute = endHour.slice(3,5);
				context = subName.concat(" ",classroom);

				displayTimetable(shour,sminute,ehour,eminute,day1,day2,context);
   		}
 	},
 	error : function(request,status,error){
		alert('검색 에러');
		console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
	}
});

}
var displayTimetable = function (val_shour, val_sminute, val_ehour, val_eminute, val_day1, val_day2, context) {
	var index_day1=0
	var index_day2=0;
	var table = document.getElementById("table"), rIndex, cIndex;
	var row_length = table.rows.length;
	var new_row_len = val_ehour; 
	
	//동적으로 테이블 조정
	new_row_len = (new_row_len - 8) * 2;
	if (val_eminute == '30')
		new_row_len++;
	var cell = new Array();
	if (row_length < new_row_len) {
		for (var i = row_length + 1; i < new_row_len; i++) {
			newRow = table.insertRow(table.length), 
			cell[0] = newRow.insertCell(0),  
			cell[1] = newRow.insertCell(1),
			cell[2] = newRow.insertCell(2), 
			cell[3] = newRow.insertCell(3), 
			cell[4] = newRow.insertCell(4),
			cell[5] = newRow.insertCell(5),
			time = (i % 2 == 0 ? (i / 2) + 8 : '');
			text = '#';
			cell[0].innerHTML = time;
			for (var j = 1; j < 6; j++)
				cell[j].innerHTML = text;
			if (i % 2 == 0)
				$(newRow).addClass("stripe-top");
			for (var j = 1; j < 6; j++){
				$(cell[j]).addClass("content"+j);
				$(cell[j]).css("border-left", "1px solid #e5e5e5");
			}
		}
	}
	if (val_sminute == "30") {
		val_shour = (val_shour - 8) * 2;
	} else if (val_sminute == "00") {
		val_shour = (val_shour - 8) * 2 - 1;
	}
	if (val_eminute == "30") {
		val_ehour = (val_ehour - 8) * 2;
	} else if (val_eminute == "00") {
		val_ehour = (val_ehour - 8) * 2 - 1;
	}
	var days = [ '월', '화', '수', '목', '금' ];
	for (var i = 0; i < 5; i++) {
		if (days[i] == val_day1)
			index_day1 = i + 1;
		if (days[i] == val_day2)
			index_day2 = i + 1;
	}
	//color random
	var colorCode = "#" + Math.round(Math.random() * 0xFFFFFF).toString(16);

	//paint time
	for (i = val_shour; i < val_ehour; i++) {
		if(index_day1 != 0) {
			$('table tr:nth-child(' + i + ') td:eq(' + index_day1 + ')').css('background-color', colorCode);
			table.rows[i].cells[index_day1].innerHTML = context;
		}
		if(index_day2 != 0){
			$('table tr:nth-child(' + i + ') td:eq(' + index_day2 + ')').css('background-color', colorCode);
			table.rows[i].cells[index_day2].innerHTML = context;
		}
	}
}
