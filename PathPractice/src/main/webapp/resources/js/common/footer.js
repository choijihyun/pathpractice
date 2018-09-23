var footerHtml='';


footerHtml+='<link rel="stylesheet" type="text/css" href="resources/css/style_footer.css">';
footerHtml+='<footer class="app-footer py-1">';
footerHtml+='<div class="nav-scroller mb-2">';
footerHtml+='<nav class="nav justify-content-between">';
footerHtml+='<a class="btn btn-default btn-icon  home"  aria-label="Left Align" href="/home">';
footerHtml+='<span class="fas fa-home"></span> ';
footerHtml+='</a>';

footerHtml+='<a class="btn btn-default btn-icon  menu" aria-label="Left Align" href="/menu">';
footerHtml+='<span class="fas fa-bars"></span> ';
footerHtml+='</a>';

footerHtml+='<a  class="btn btn-default btn-icon  time-table" aria-label="Left Align" href="/timetable_page">';
footerHtml+='<span class="fas fa-table"></span> ';
footerHtml+='</a>';

footerHtml+='<a class="btn btn-default btn-icon  my-page" aria-label="Left Align"" href="/mypage">';
footerHtml+='<span class="fas fa-user"></span> ';
footerHtml+='</a>';

footerHtml+='<a class="btn btn-default btn-icon assignment" aria-label="Left Align" href="/assignment">';
footerHtml+='<span class="fas fa-list-alt"></span> ';
footerHtml+='</a>';

footerHtml+='</nav>';
footerHtml+='</div>';
footerHtml+='</footer>';

var footer = document.getElementById("footer");
footer.innerHTML=footerHtml;








