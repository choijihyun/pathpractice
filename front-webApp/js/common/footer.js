var footerHtml='';

footerHtml+='<footer class="app-footer py-1">';
footerHtml+='<div class="nav-scroller mb-2">';
footerHtml+='<nav class="nav justify-content-between">';
footerHtml+='<a class="btn btn-default btn-icon  home"  aria-label="Left Align" href="home.html">';
footerHtml+='<span class="glyphicon glyphicon-home" aria-hidden="true"></span>';
footerHtml+='</a>';
footerHtml+='<a class="btn btn-default btn-icon  menu" aria-label="Left Align" href="menu.html">';
footerHtml+='<span class="glyphicon glyphicon glyphicon-align-justify" aria-hidden="true"></span>';
footerHtml+='</a>';
footerHtml+='<a  class="btn btn-default btn-icon  time-table" aria-label="Left Align" href="timetable_page.html">';
footerHtml+='<span class="glyphicon glyphicon glyphicon-th" aria-hidden="true"></span>';
footerHtml+='</a>';
footerHtml+='<a class="btn btn-default btn-icon  my-page" aria-label="Left Align"" href="mypage.html">';
footerHtml+='<span class="glyphicon glyphicon-user" aria-hidden="true"></span>';
footerHtml+='</a>';
footerHtml+='<a class="btn btn-default btn-icon assignment" aria-label="Left Align" href="assignment.html">';
footerHtml+='<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>';
footerHtml+='</a>';
footerHtml+='</nav>';
footerHtml+='</div>';
footerHtml+='</footer>';

var footer = document.getElementById("footer");
footer.innerHTML=footerHtml;








