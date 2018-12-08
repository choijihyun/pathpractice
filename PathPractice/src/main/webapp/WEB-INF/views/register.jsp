<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<%
	System.out.println("register from android.");

	String token =  null;
	String pageName =  null;
	String userId = null;
	
	request.setCharacterEncoding("utf-8");
					    
	token = request.getParameter("Token");
	System.out.println("token is "+token);

	pageName = request.getParameter("page");
	userId = request.getParameter("user");
	
	if((token!=null) && (pageName==null)){
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		request.setAttribute("token", token);
		System.out.println("request==="+request.getAttribute("token"));
		System.out.println("back to index");
		dispatcher.forward(request, response);
		
	} 
	
	
%>
<!-- <script>
var location= function(token){
	System.out.println("back to index");
	location.href="/?token="+token;
}
</script> -->
<script type="text/javascript">
var token = <%=token%>;
var page = <%=pageName%>;
var userId = <%=userId%>;

	console.log("page==="+page);
	console.log("id==="+userId);
	console.log("token==="+token); 
	if(token==null){
		token = '${token}';
		console.log("new token==="+token); 
	}
	if(page == 'index'){
					 
		$.ajax({
			url:"/user/insertToken.json",
			type : "get",
			async : false,
			data : {
				'stuId': userId,
				'token': token
			},
			success : function(result){
				if(result['result'] === "1"){ 
					console.log(result);
					location.href = "/home";
										
				}else{
					console.log('토큰 등록 실패');
				}
			},error : function(){
				console.log('토큰 등록 에러');
			}
		});//ajax	
	}/* 
	else{
		console.log("back to index");
		location.href = "/?"+token;
	} */

</script>



