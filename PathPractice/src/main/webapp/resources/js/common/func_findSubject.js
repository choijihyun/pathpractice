var locateAssign = function(location,title,dueDate,importance,contents,assignNo,subNo,subjectkey){
	window.location.href=location+"?title="+title
		+"&dueDate="+dueDate
		+"&importance="+importance
		+"&contents="+contents
		+"&assignNo="+assignNo
		+"&subNo="+subNo
		+"&subjectKey="+subjectkey;
}

var searchSubject = function(subjectName, select){
	$.ajax({
   			url:"/subject/searchSubject.json",
   			type : "GET",
   			data : {
   				'word':subjectName,
   				'select':select
   			},
   			success : function(result){
           		if(result['result'] === "no data"){ 
           			alert('없는 과목입니다.');
           		}else{ 
  					console.log(result);
           			var	tableLen =result['result'].length
           			var table = document.getElementById("tableFindSub"),rIndex,cIndex;
           			var cell = new Array();
          	  		for(var i=0 ; i <tableLen ; i++){
           				addRow(table,cell,result);
          	  		}
           		}
           		
           		var table = document.getElementById("tableFindSub"),rIndex,cIndex;
        		
        		for(var i = 1 ; i < table.rows.length ; i++){
        			for(var j = 1 ; j<table.rows[i].cells.length ; j++){
        				table.rows[i].cells[j].onclick = checkedRow(this);
        			}
        		}
         	},
         	error : function(request,status,error){
        		alert('검색 에러');
        		console.log("code:"+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
        	}
		});
}

var addRow = function(table,cell,result,i){
		console.log(result);
		newRow = table.insertRow(table.length),
		cell[0] = newRow.insertCell(0),
		cell[1] = newRow.insertCell(1),
		cell[2] = newRow.insertCell(2),
		cell[3] = newRow.insertCell(3),
		cell[4] = newRow.insertCell(4),
		cell[5] = newRow.insertCell(5),
		cell[6] = newRow.insertCell(6),
		cell[0].innerHTML = i+1;
		cell[1].innerHTML = '<input type="radio" id="radio'+(i+1)+'" name="select" data-sub-no="'+result['result'][i]['subNo']+'" data-subject-key="'+result['result'][i]['subjectKey']+'">';
		cell[2].innerHTML = result['result'][i]['subName'];
		cell[3].innerHTML = result['result'][i]['day'];
		cell[4].innerHTML = result['result'][i]['startHour'];
		cell[5].innerHTML = result['result'][i]['classroom'];
		cell[6].innerHTML = result['result'][i]['profName'];
		
		$(newRow).addClass("stripe_border_top");
		$(newRow).addClass("add_row");
		for(var j=0 ; j<6 ; j++)
			$(cell[j]).css("border-right","1px solid #e5e5e5");
}
var checkedRow = function(table){
	rIndex = table.parentElement.rowIndex;
	cIndex = table.cellIndex;

	console.log("ROW:" +rIndex+ ", Cell:" +cIndex);
	$('input:radio[id="radio'+rIndex+'"]:radio[name="select"]').prop("checked", true);
	
	var subNo = $("#radio"+rIndex).data('subNo');
	var subjectKey = $("#radio"+rIndex).data('subjectKey');
	$('#hiddenAssign').val(subNo).trigger('change');
	$('#hiddenSubjectKey').val(subjectKey).trigger('change');
}