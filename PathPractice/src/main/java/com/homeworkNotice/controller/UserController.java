 package com.homeworkNotice.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dto.UserDto;
import com.homeworkNotice.dao.SubjectDao;
import com.homeworkNotice.dto.SubjectDto;
import com.homeworkNotice.dto.TeamDto;
import com.homeworkNotice.dao.CompleteDao;
import com.homeworkNotice.dto.CompleteDto;
import com.homeworkNotice.dao.TimeTableDao;
import com.homeworkNotice.dto.TimeTableDto;
import com.homeworkNotice.dao.HomeworkDao;
import com.homeworkNotice.dto.HomeworkDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//C:\Users\은숙\.m2\repository
//라이브러리 모음집
//라이브러리들 가져오는거? 안되면 이 경로로 들어가서 안에있는 라이브러리들 다 지우고 다시 해보면 됨!

//외부(안드로이드)에서 유저 정보를 url로 요청하면 이리로 오게 된다! 
@Controller
public class UserController {
	
	//싱글톤으로 떠도는 userDao를 잡아옴
  @Autowired //만약에 따로 정해진 id 가 있으면 autowired 아니라 다른거로 ㅎㅎㅎㅎ;;
  private UserDao userDao;
  private SubjectDao subjectDao;
  private CompleteDao completeDao;
  private TimeTableDao timeTableDao;
  private HomeworkDao homeworkDao;
  
  //앞으로 많을 요청 중 하나의 함수
  //안드로이드에서 비밀번호를 달라고 요청하는 함수.
	@ResponseBody
    @RequestMapping(value = "/user/getUserPwdInfo.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
    public String getUserPwdInfo(//url에 맵핑(연결)된 함수
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "email", required=true) String email) {//안드로이드에서 받을 파라미터, 사실 요거 하나만 받음 댐
		// ?id=1 이런식으로 치면 1에 해당하는 password가 나오는 함수인데 여기에서 @RequestParam 부분은 id라는 거를 받아줄 통? 을 만들었다고 보면 됨
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();// 이부분은 잘 모르겠어요!!!!!!!!!!!!!!!!!!!!!!!!!
		//xml의 sql에서 필요로 하는 정보가 있다면 여기에 담아서 전달해줌. 이 함수에선 id겠지?(밑에 보이는)

		param.put("stuId",stuId);
		//파라미터에 안드로이드에서 건네받은 id를 등록한다
    	List<UserDto> userDtoList=userDao.selectUser(param);//쿼리문 만들고 싶으면 user-mapping.xml 참고
    	//id들을 userdto 리스트에 저장함
    	//그 파라미터를 sql에 던져준다!! 그러면 userDtoList 여기에 반환.
    	
    	//근데 이 부분에서 UserDao의 selectUser로 가잖아아영 
    	//근데 그 UserDao에서 return sqlSession.selectList("~~~~~~ 이런거 해주는데 이건 뭐에여???
    	//userDao(캐릭터)가 xml의 sql(무기)를 사용해서 결과를 얻은걸 userDtoList에 저장하는거야
    	//무기를 사용하는 행위가 sqlSession.selectList 이걸 실행시키는거인 거지
    	
    	
    	//Dao4. Dao3까지 진행해서 만든 sql을 userDao.?????로 만들어서 사용하면 되고, 파라미터 넣는법은 바로 위에 적었지??ㅎㅎ
    	
    	JSONObject jSONObject = new JSONObject();
    	if(!userDtoList.isEmpty() && userDtoList.get(0).getEmail().equals(email)) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
        	jSONObject.put("pw", userDtoList.get(0).getPw()); 
        	//여기도 궁금!!!! 그 userDtoList.get(0)은 뭘 의미하는거에영???
        	//db에서 받은 리스트들 중 첫번째 인덱스를 가져오는거야 (근데 사실 id로 가져오면 항상 1개밖에 없긴함;;;ㅎㅎ)
    	}
    	else {//없으면 에러라고 브라우저에 뿌려준다
    		jSONObject.put("result", "no data");
    	}
    	System.out.println(jSONObject.toString());
    	return jSONObject.toString();//요청한 내용들을 반환해준다.
    }
	

	//안드로이드에서 사용할 회원가입 url이야!!
	//서버 킨 후에 크롬,익스플로러 브라우저 아무거나에 url 치는 곳에다가
	//http://localhost:8080/main/user/insertUser.json?id=test123&password=test123&name=test123&grade=1&email=test123
	//이 url을 입력해보면
	//success 됐다는걸 볼수있을거야 ㅎㅎ
	//일부러 이 밑에는 주석 다 뺏으니까 어떤 내용인지 혼자 생각해보고 기억안나면 위에 함수로 올라가서
	//주석 보고 공부해봐 ㅎㅎ
	@ResponseBody
    @RequestMapping(value = "/user/insertUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String insertUser(
    			Model model,
    			@RequestParam(value = "name", required=true) String name,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "semester", required=true) final int semester,
    			@RequestParam(value = "pw", required=true) String pw,
    			@RequestParam(value = "email", required=true) String email) { // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
    			
		param.put("name",name);		
		param.put("stuId",stuId);		
		param.put("semester",semester);		
		param.put("pw",pw);		
		param.put("email",email);
		//
		System.out.println(param);
		
		//이 함수(url)은 회원가입이 주 목적이기 때문에
		//결과로 성공 or 실패만 알려 주면 돼
		//int 값으로 반환이 되는데 1이면 성공 나머지 값이면 실패!!
		int result=0;
		try {
			result=userDao.insertUser(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//그래서 여기서 성공 or 실패 구분해서 안드로이드에 json 데이터를 결과로 전달해줄거야
    	if(result==1) {
    		jSONObject.put("result", "1");//성공     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

    @RequestMapping(value = "/user/updateUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String updateUser(
    		HttpServletResponse response,
			HttpSession session,
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
    			@RequestParam(value = "name",required=true) String name,
    			@RequestParam(value = "semester", required=true) final int semester,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "email", required=true) String email) {
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId", stuId);
		param.put("name", name);	
		param.put("semester",semester);		
		param.put("email",email);
		
		System.out.println(param);
		
		int result=0;
		try {
			result=userDao.updateUser(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


		System.out.println("######result : " +result);
		JSONObject jSONObject = new JSONObject();
    	
    	if(result == 1) {
    		System.out.println("controller에서는 성공!");
    		jSONObject.put("result","1");	
    		session.removeAttribute("name");
			session.setAttribute("name", name);
    	}
    	else {
    		jSONObject.put("result","0");
    	}
    	return jSONObject.toString();
	}
	
	
    @RequestMapping(value = "/user/updatePw.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String updatePw(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "pw", required=true) String pw) { 
		HashMap<Object, Object> param=new HashMap<Object, Object>(); 
			
		param.put("stuId",stuId);	
		param.put("pw",pw);	
		
		System.out.println(param);
		
		int result=0;
		try {
			result=userDao.updatePw(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

    	JSONObject jSONObject = new JSONObject();
    	if(result==1) {
    		jSONObject.put("result", "1");	
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	//System.out.println("뀨잉뽀잉 ㄴ"+jSONObject.toString());
    	return jSONObject.toString();
	}


	//이거는 처음 로그인 할때만 쓰는 controller
	@RequestMapping(value = "/user/checkUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.POST)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
	public @ResponseBody String checkUser(//url에 맵핑(연결)된 함수
			HttpServletResponse response,
			HttpSession session,
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value = "pw", required=true) String pw) {
		int result=0;
		if(session.getAttribute("login")!=null) {
			session.removeAttribute("login");
		}
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("pw",pw);
		
		
		System.out.println(param);
		List<UserDto> userDtoList=userDao.selectUser(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(!userDtoList.isEmpty() && userDtoList.size()==1) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
			if(pw.equals(userDtoList.get(0).getPw())) {
				jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
				System.out.println("id : "+stuId);
				
				HashMap<Object, Object> PARAM=new HashMap<Object, Object>();
				PARAM.put("stuId", stuId);
				List<UserDto> user=userDao.selectUserInfo(PARAM);
				String name = user.get(0).getName();
				String email = user.get(0).getEmail();
				System.out.println(name);
				System.out.println(email);
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				session.setAttribute("id",stuId);
				session.setAttribute("pw",pw);
				session.setAttribute("check", user.get(0).getUseCookie());
				
				if(user!=null) {
					session.setAttribute("login", user);
					System.out.println("user.get(0).getUseCookie() = "+user.get(0).getUseCookie());
					if(user.get(0).getUseCookie().equals("1")) {
						Cookie cookie = new Cookie("loginCookie", session.getId());
						cookie.setPath("/");
						int amount = 60*60*24*7;
						cookie.setMaxAge(amount);
						response.addCookie(cookie);
						Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));
						
						JSONObject jSONObject2 = new JSONObject();
						HashMap<Object, Object> PARAM2=new HashMap<Object, Object>();
						PARAM2.put("stuId", user.get(0).getStuId());
						PARAM2.put("sessionId", session.getId());
						PARAM2.put("next", sessionLimit);
						System.out.println(PARAM2);
						result = userDao.keepLogin(PARAM2);
						
					}
				}
				
			}
			else {
				jSONObject.put("result","0");//비번이 다른 경우
			}
		}
		else {//없으면 에러라고 브라우저에 뿌려준다
			jSONObject.put("result", "0"); //id가 존재하지 않는경우
		}
		//System.out.println(jSONObject.toString());
		return jSONObject.toString();//요청한 내용들을 반환해준다.
	}
	
	//이거는 비밀번호 바꿀 때 혹은 비밀번호 확인할 떄 쓰는 controller
	@RequestMapping(value = "/user/checkUserExist.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.POST)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
	public @ResponseBody String checkUserExist(//urRl에 맵핑(연결)된 함수
			HttpServletRequest request,
			HttpSession session,
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value = "pw", required=true) String pw) {
		
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("pw",pw);
		
		
		System.out.println(param);
		List<UserDto> userDtoList=userDao.selectUser(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(!userDtoList.isEmpty() && userDtoList.size()==1) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
			if(pw.equals(userDtoList.get(0).getPw())) {
				System.out.println("checkUserExist 유저 확인됨");
				if(stuId==session.getAttribute("Id")) {
					jSONObject.put("result","0");//본인의 아이디가 아닌경ㅇ
				}
				else {
					jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
				}
				System.out.println("id : "+stuId);
			}
			else {
				jSONObject.put("result","0");//비번이 다른 경우
			}
		}
		else {//없으면 에러라고 브라우저에 뿌려준다
			jSONObject.put("result", "0"); //id가 존재하지 않는경우
		}
		//System.out.println(jSONObject.toString());
		return jSONObject.toString();//요청한 내용들을 반환해준다.
	}
	
	@ResponseBody
    @RequestMapping(value = "/user/getAllUserData.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String getAllUserData(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "select", required=true) final int select) {

    	List<UserDto> userDtoList=userDao.selectAllList();
    	System.out.println(userDtoList);
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!userDtoList.isEmpty()) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
        	for(int i=0;i<userDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("name",userDtoList.get(i).getName());
        		jSONObject.put("stuId", userDtoList.get(i).getStuId());
        		jSONObject.put("semester", userDtoList.get(i).getSemester());
        		jSONObject.put("pw", userDtoList.get(i).getPw());
        		jSONObject.put("email",userDtoList.get(i).getEmail());
        		
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	Collections.sort( jsonList, new Comparator<JSONObject>() {

    		    public int compare(JSONObject a, JSONObject b) {
    		        String valA = new String();
    		        String valB = new String();
    		        int vA,vB;
    		        
    		      
    		        switch(select) {
    		        case 1: valA = (String) a.get("name");valB = (String) b.get("name");break;
    		        case 2: vA = (Integer) a.get("stuId");vB = (Integer) b.get("stuId");break;
    		        case 3: vA = (Integer) a.get("semester");vB = (Integer) b.get("semester");break;
    		        case 4: vA= (Integer)a.get("pw");vB = (Integer) b.get("pw");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        case 5: valA = (String) a.get("email");valB = (String) b.get("email");break;
    		        }

    		        return valA.compareTo(valB);
    		    }
    		});
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<userDtoList.size();i++){
        		jSONArray.add(jsonList.get(i));
        	}
        	
        	JSONObject jsObject=new JSONObject();
        	jsObject.put("result", jSONArray);

            return jsObject.toString();
        } 
        else {//없으면 에러라고 브라우저에 뿌려준다

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}
	
	@ResponseBody
    @RequestMapping(value = "/user/deleteUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String deleteUser(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) { // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
    			
		param.put("stuId",stuId);	
		//
		System.out.println(param);
		
		
		//이 함수(url)은 회원가입이 주 목적이기 때문에
		//결과로 성공 or 실패만 알려 주면 돼
		//int 값으로 반환이 되는데 1이면 성공 나머지 값이면 실패!!
		int result=0,result1=0,result2=0,result3=0,result4=0;
		try {
			result=userDao.deleteUser(param);
			/*
			result1=subjectDao.deleteSubject(param);
			result2=homeworkDao.deleteAllHomework(param);
			result3=completeDao.deleteAllComplete(param);
			result4=timeTableDao.deleteAllTimeTable(param);
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//그래서 여기서 성공 or 실패 구분해서 안드로이드에 json 데이터를 결과로 전달해줄거야
    	if(result==1/*&&result1==1&&result2==1&&result3==1&&result4==1*/) {
    		jSONObject.put("result", "1");//성공     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

	@ResponseBody
    @RequestMapping(value ="/user/insertToken.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String insertToken(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "token", required=true) String token) { // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
    	
		param.put("stuId", stuId);
		param.put("token",token);
		
		System.out.println(param);
		
		int result=0;
		try {
			result=userDao.insertToken(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//그래서 여기서 성공 or 실패 구분해서 안드로이드에 json 데이터를 결과로 전달해줄거야
    	if(result==1) {
    		jSONObject.put("result", "1");//성공     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

	//select
		@ResponseBody
		@RequestMapping(value = "/user/pushUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
		public String pushUser(
				Locale locale, 
				Model model, 
				@RequestParam(value = "stuId", required=true) String stuId) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);			
			
			List<UserDto> userDtoList =userDao.pushUser(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!userDtoList.isEmpty()) {
	        	for(int i=0;i<userDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("stuId",userDtoList.get(i).getStuId());
	        		jSONObject.put("token",userDtoList.get(i).getToken());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<userDtoList.size();i++){
	        		jSONArray.add(jsonList.get(i));
	        	}
	        	
	        	JSONObject jsObject=new JSONObject();
	        	jsObject.put("result", jSONArray);

	            return jsObject.toString();
	        } 
	        else {

	    		JSONObject jSONObject = new JSONObject();
	        	jSONObject.put("result", "no data");
	        	
	        	return jSONObject.toString();
	        }
		}
		
		@RequestMapping(value = "/user/BlackboardUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.POST)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
		public @ResponseBody String BlackboardUser(//url에 맵핑(연결)된 함수
				HttpServletResponse response,
				HttpSession session,
				Locale locale, //안드로이드에서 받을 파라미터
				Model model, //안드로이드에서 받을 파라미터
				@RequestParam(value = "stuId", required=true) String stuId) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);
			
			
			System.out.println(param);
			List<UserDto> userDtoList=userDao.selectUserInfo(param);
		
			
			JSONObject jSONObject = new JSONObject();
			if(!userDtoList.isEmpty() && userDtoList.size()==1) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
				if(userDtoList.get(0).getFlag()==1) {
					jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
				}
				else {
					jSONObject.put("result","0");//비번이 다른 경우
				}
			}
			else {//없으면 에러라고 브라우저에 뿌려준다
				jSONObject.put("result", "0"); //id가 존재하지 않는경우
			}
			//System.out.println(jSONObject.toString());
			return jSONObject.toString();//요청한 내용들을 반환해준다.
		}

	@RequestMapping(value = "/user/preHandle.json", produces = "application/json;text/plain;charset=UTF-8", method = RequestMethod.POST) 
	public @ResponseBody void preHandle(// url에 맵핑(연결)된 함수
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Locale locale, // 안드로이드에서 받을  파라미터
			Model model // 안드로이드에서 받을 파라미터
	) {
		System.out.println("preHandle!!!!!!!!!!!!!!!!!!1");
		JSONObject jSONObject = new JSONObject();
		session = request.getSession();
		Object obj = session.getAttribute("login");
		//login 처리를 담당하는 사용자 정보를 담고 있는 객체를 가져오는 과정
		
		System.out.println("obj = " + obj);
		if (obj == null) { // 로그인 된 세션이 없는 경우
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 만들어놨던 쿠키 꺼내옴
			//System.out.println("loginCookie = " + loginCookie.getComment());
			//System.out.println("loginCookie = " + loginCookie.getValue());
			if (loginCookie != null) { // 쿠키가 존재하는경우, 
				String sessionId = loginCookie.getValue(); // 저장해놨던 sessionId 가져오기!
				System.out.println("sessionId는 null이 아님");
				HashMap<Object, Object> param = new HashMap<Object, Object>();
				param.put("sessionId", sessionId);
				System.out.println("param : "+param);
				if(userDao.checkUserWithSessionKey(param)!=null) {
					List<UserDto> uservo = userDao.checkUserWithSessionKey(param);
					System.out.println("끝");

					if (!uservo.isEmpty() && uservo != null) {
						session.setAttribute("login", uservo);
						session.setAttribute("id", uservo.get(0).getStuId());
						session.setAttribute("pw", uservo.get(0).getPw());
						session.setAttribute("checkbox", uservo.get(0).getUseCookie());
						return;
					}
				}
				return;			
			}
		}
		return;
	}
		
		@ResponseBody
	    @RequestMapping(value = "/user/checkbox.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
	    public String checkbox(
	    			Locale locale, 
	    			Model model,
	    			@RequestParam(value = "select", required=true) final int select,
	    			@RequestParam(value = "stuId", required=true) String stuId) {
			HashMap<Object, Object> param=new HashMap<Object, Object>();	
			param.put("stuId",stuId);
			param.put("check", select);
			
			int result=0;
			try {
				result=userDao.checkbox(param);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

	    	JSONObject jSONObject = new JSONObject();
	    	//그래서 여기서 성공 or 실패 구분해서 안드로이드에 json 데이터를 결과로 전달해줄거야
	    	if(result==1) {
	    		jSONObject.put("result", "1");//성공     		
	    	}
	    	else {
	    		jSONObject.put("result", "0");
	    	}
	    	return jSONObject.toString();
		}
		
		@ResponseBody
	    @RequestMapping(value = "/user/logout.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
	    public void logout(
	    			Locale locale, 
	    			Model model,
	    			HttpSession session,
	    			HttpServletRequest request,
	    			HttpServletResponse response,
	    			@RequestParam(value = "stuId", required=true) String stuId) {
	
	    	Object obj = session.getAttribute("login");
	    	if(obj!=null) {
	    		UserDto vo = (UserDto)obj;
	    		
	    		session.removeAttribute("login");
	    		session.invalidate();
	    		int result =0;
	    		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
	    		System.out.println("loginCookie = "+loginCookie);
	    		if(loginCookie!=null) {
	    			System.out.println("if문 안에 들어옴");
	    			loginCookie.setPath("/");
	    			loginCookie.setMaxAge(0);
	    			response.addCookie(loginCookie);
	    			Date date = new Date(System.currentTimeMillis());
	    			
	    			JSONObject jSONObject2 = new JSONObject();
					HashMap<Object, Object> PARAM2=new HashMap<Object, Object>();
					PARAM2.put("stuId",stuId);
					PARAM2.put("sessionId", session.getId());
					PARAM2.put("next", date);
					result = userDao.keepLogin(PARAM2);
					System.out.println("!!!!!!!!!!!!!!!!!!1Result = "+result);
	    		}
	    	}
			
	
		}
		
}