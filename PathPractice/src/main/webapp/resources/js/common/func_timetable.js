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
				for (var j = 0; j < 5; j++)
					$(cell[j]).css("border-right", "1px solid #e5e5e5");
					$(cell[j]).addClass("content");
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
	