 package com.homeworkNotice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dto.UserDto;


//C:\Users\은숙\.m2\repository
//라이브러리 모음집
//라이브러리들 가져오는거? 안되면 이 경로로 들어가서 안에있는 라이브러리들 다 지우고 다시 해보면 됨!

//외부(안드로이드)에서 유저 정보를 url로 요청하면 이리로 오게 된다! 
@Controller
public class UserController {
	
	//싱글톤으로 떠도는 userDao를 잡아옴
    @Autowired //만약에 따로 정해진 id 가 있으면 autowired 아니라 다른거로 ㅎㅎㅎㅎ;;
    private UserDao userDao;
    
    //앞으로 많을 요청 중 하나의 함수
    //안드로이드에서 비밀번호를 달라고 요청하는 함수.
	@ResponseBody
    @RequestMapping(value = "/user/getUserPwdInfo.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
    public String getUserPwdInfo(//url에 맵핑(연결)된 함수
    			Locale locale, //안드로이드에서 받을 파라미터
    			Model model, //안드로이드에서 받을 파라미터
    			@RequestParam(value = "stuId", required=true) String stuId) {//안드로이드에서 받을 파라미터, 사실 요거 하나만 받음 댐
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
    	if(!userDtoList.isEmpty()) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
        	jSONObject.put("pw", userDtoList.get(0).getPw()); 
        	jSONObject.put("email", userDtoList.get(0).getEmail());
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

	@ResponseBody
    @RequestMapping(value = "/user/updateUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String updateUser(
    			Model model,
    			@RequestParam(value="name",required=true) String name,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "semester", required=true) final int semester,
    			@RequestParam(value = "pw", required=true) String pw,
    			@RequestParam(value = "email", required=true) String email) { // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
		
		param.put("name", name);
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
			result=userDao.updateUser(param);
			
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
	
	
	
	//id에 해당하는 pw를 바꿈 지현이가 한부분!!!
	@ResponseBody
    @RequestMapping(value = "/user/updatePw.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String updatePW(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "pw", required=true) String pw) { 
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
			
		param.put("stuId",stuId);	
		param.put("pw",pw);	
		
		System.out.println(param);
		//이 함수(url)은 회원가입이 주 목적이기 때문에
		//결과로 성공 or 실패만 알려 주면 돼
		//int 값으로 반환이 되는데 1이면 성공 나머지 값이면 실패!!
		int result=0;
		try {
			result=userDao.updatePw(param);
			
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
    	System.out.println(jSONObject.toString());
    	return jSONObject.toString();
	}



	@ResponseBody
	@RequestMapping(value = "/user/checkUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
	public String checkUser(//url에 맵핑(연결)된 함수
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
				jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
			}
			else {
				jSONObject.put("result","0");//비번이 다른 경우
			}
		}
		else {//없으면 에러라고 브라우저에 뿌려준다
			jSONObject.put("result", "0"); //id가 존재하지 않는경우
		}
		return jSONObject.toString();//요청한 내용들을 반환해준다.
	}
	
	@ResponseBody
    @RequestMapping(value = "/user/getAllUserData.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
    public String getAllUserData(//url에 맵핑(연결)된 함수
    			Locale locale, //안드로이드에서 받을 파라미터
    			Model model,
    			@RequestParam(value = "select", required=true) final int select) {//안드로이드에서 받을 파라미터, 사실 요거 하나만 받음 댐

    	List<UserDto> userDtoList=userDao.selectAllList();//쿼리문 만들고 싶으면 user-mapping.xml 참고
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
	
	
	
}