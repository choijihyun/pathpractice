var timetableHtml='';


timetableHtml+='<link rel="stylesheet" type="text/css" href="css/style_timetable.css">';
timetableHtml+='		<!-- 시간표 테이블 -->';
timetableHtml+='		<table class="table table-striped table-bordered table-time">';
timetableHtml+='			<thead>';
timetableHtml+='				<!-- 맨 윗줄-->';
timetableHtml+='				<tr>';
timetableHtml+='					<th class="text-center">#</th>';
timetableHtml+='					<th class="text-center">Mon</th>';
timetableHtml+='					<th class="text-center">Tue</th>';
timetableHtml+='					<th class="text-center">Wen</th>';
timetableHtml+='					<th class="text-center">Thu</th>';
timetableHtml+='					<th class="text-center">Fir</th>';
timetableHtml+='				</tr>';
timetableHtml+='			</thead>';
timetableHtml+='			<tbody>';
timetableHtml+='			<!-- 1행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>9-9:30</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 2행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>9:30-10</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 3행 -->';
timetableHtml+='				<tr>';
timetableHtml+='					<td>10-10:30</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 4행 -->';
timetableHtml+='				<tr class="success">';
timetableHtml+='					<!-- success 는 색깔 떄문에 넣은거! -->';
timetableHtml+='					<td>10:30-11</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>';
timetableHtml+='					<td>#</td>a';
timetableHtml+='				</tr>';
timetableHtml+='				<!-- 5행 -->';
timetableHtml+='				<tr class="danger">';
timetableHtml+='				<!-- danger 는 색깔 떄문에 넣은거! -->';
timetableHtml+='					<td>11-11:30</td>';
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




