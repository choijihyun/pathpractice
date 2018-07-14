var headerHtml='';


headerHtml+='<link rel="stylesheet" type="text/css" href="css/style_header.css">';
headerHtml+='<header class="app-header">';
headerHtml+='	<div class="row col-auto justify-content-end">';
headerHtml+='		<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">';
headerHtml+='		</div>';
headerHtml+='		<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">';
headerHtml+='			<h2 class="app-header-logo"> PATH </h2>';
headerHtml+='		</div>';
headerHtml+='		<div class= "col-4 col-xs-4 col-sm-4 col-lg-4 col-md-4">';
headerHtml+='			<a class="btn btn-default btn-log-out" aria-label="Left Align" href="index.html">';
headerHtml+='				<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>';
headerHtml+='			</a>';
headerHtml+='		</div>';
headerHtml+='	</div>';
headerHtml+='</header>';

var header = document.getElementById("header");
header.innerHTML=headerHtml;