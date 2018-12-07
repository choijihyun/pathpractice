<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	out.println("register from android.");

    String token = null;
    String requestMethod = null;
    
    request.setCharacterEncoding("utf-8");
    
    token = request.getParameter("Token");
    if (!token.equals("") ){%>
		<script>
			<%
			String id = (String) session.getAttribute("id");
			%>
			
			$.ajax({
				url:"/user/insertToken.json",
				type : "POST",
				async : false,
				data : {
					'stuId':<%=id%>,
					'token': token
				},
				success : function(result){
					if(result['result'] === "1"){ 
						console.log(result);
					}else{
						console.log('토큰 등록 실패');
					}
				},
				error : function(){
					console.log('토큰 등록 에러');
				}
			});//ajax
		</script>    
    <%}
%>

    