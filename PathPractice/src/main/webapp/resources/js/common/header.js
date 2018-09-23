var headerHtml='';


<<<<<<< HEAD
headerHtml+='<link rel="stylesheet" type="text/css" href="/resources/css/style_header.css">';
=======
headerHtml+='<link rel="stylesheet" type="text/css" href="resources/css/style_header.css">';
>>>>>>> d926a5495d954bbd5c0498790d81e0838357ff1b
headerHtml+='<header class="app-header">';
headerHtml+='	<div class="row col-auto justify-content-end">';
headerHtml+='		<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">';
headerHtml+='		</div>';
headerHtml+='		<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">';
headerHtml+='			<h2 class="app-header-logo"> PATH </h2>';
headerHtml+='		</div>';
headerHtml+='		<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">';
headerHtml+='			<a class="btn btn-default btn-log-out" aria-label="Left Align" onclick='+'location.href="/logout"'+'>';
headerHtml+='				<span class="fas fa-sign-out-alt"></span> ';
headerHtml+='			</a>';
headerHtml+='		</div>';
headerHtml+='	</div>';
headerHtml+='</header>';

var header = document.getElementById("header");
header.innerHTML=headerHtml;