var timetableHtml='';


timetableHtml+='<link rel="stylesheet" type="text/css" href="css/style_timetable.css">';
timetableHtml+='		<!-- 시간표 테이블 -->';
timetableHtml+='		<table style="overflow:auto;" class="table table-bordered table-time">';

timetableHtml+='		<colgroup>';
timetableHtml+='			<col width="10%"/>';
timetableHtml+='			<col width="18%"/>';
timetableHtml+='			<col width="18%"/>';
timetableHtml+='			<col width="18%"/>';
timetableHtml+='			<col width="18%"/>';
timetableHtml+='			<col width="18%"/>';
timetableHtml+='			<col width="18%"/>';
timetableHtml+='		</colgroup>';

timetableHtml+='			<thead>';
timetableHtml+='				<!-- 맨 윗줄-->';
timetableHtml+='				<tr>';
timetableHtml+='					<th class="text-center">#</th>';
timetableHtml+='					<th class="text-center">Mon</th>';
timetableHtml+='					<th class="text-center">Tue</th>';
timetableHtml+='					<th class="text-center">Wen</th>';
timetableHtml+='					<th class="text-center">Thu</th>';
timetableHtml+='					<th class="text-center">Fri</th>';
timetableHtml+='				</tr>';
timetableHtml+='			</thead>';
timetableHtml+='			<tbody>';


timetableHtml+='			<!-- 1행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>9</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<tr>';
timetableHtml+='					<td></td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';



timetableHtml+='				<!-- 2행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>10</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 3행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>11</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 4행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>12</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>a';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 5행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>1</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 6행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>2</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 7행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>3</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='			</tbody>';
timetableHtml+='		</table>';

var timetable = document.getElementById("timetable");
timetable.innerHTML=timetableHtml;




