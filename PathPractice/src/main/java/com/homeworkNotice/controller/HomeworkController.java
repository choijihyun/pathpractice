package com.homeworkNotice.controller;

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

import com.homeworkNotice.dao.HomeworkDao;
import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.UserDto;

@Controller
public class HomeworkController {

	@Autowired
	private HomeworkDao	homeworkDao;
	
	
	//insert
	@ResponseBody
    @RequestMapping(value = "/homework/insertHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String insertUser(
    			Model model,

    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "success", required=true) final int success) { // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
		
		param.put("stuId",stuId);			
		param.put("dueDate",dueDate);		
		param.put("importance",importance);		
		param.put("title",title);
		param.put("contents",contents);
		param.put("subNo",subNo);
		param.put("success",success);
		
		//
		
		//이 함수(url)은 회원가입이 주 목적이기 때문에
		//결과로 성공 or 실패만 알려 주면 돼
		//int 값으로 반환이 되는데 1이면 성공 나머지 값이면 실패!!
		int result=0;
		try {
			result=homeworkDao.insertHomework(param);
			
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
	
	//delete
	@ResponseBody
	@RequestMapping(value = "/homework/deleteHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
	public String deleteHomework(//url에 맵핑(연결)된 함수
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value="assignNo",required=true) final int assignNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("assignNo",assignNo);
		
		
		int result=homeworkDao.deleteHomework(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
				jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
		}
		else {//없으면 에러라고 브라우저에 뿌려준다
			jSONObject.put("result", "0"); //id가 존재하지 않는경우
		}
		return jSONObject.toString();//요청한 내용들을 반환해준다.
	}

	
	//update
	@ResponseBody
    @RequestMapping(value = "/homework/updateHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String updateUser(
    			Model model,

    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "assignNo", required=true) final int assignNo) { // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
					
		param.put("dueDate",dueDate);		
		param.put("importance",importance);		
		param.put("title",title);
		param.put("contents",contents);
		param.put("subNo",subNo);
		param.put("stuId",stuId);
		param.put("assignNo", assignNo);
		
		//이 함수(url)은 회원가입이 주 목적이기 때문에
		//결과로 성공 or 실패만 알려 주면 돼
		//int 값으로 반환이 되는데 1이면 성공 나머지 값이면 실패!!
		int result=0;
		try {
			result=homeworkDao.updateHomework(param);
			
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
	

	//select
		@ResponseBody
		@RequestMapping(value = "/homework/selectHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
		public String selectHomework(//url에 맵핑(연결)된 함수
				Locale locale, //안드로이드에서 받을 파라미터
				Model model, //안드로이드에서 받을 파라미터
				@RequestParam(value = "stuId", required=true) String stuId,
				@RequestParam(value = "select", required=true) final int select) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);			
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!homeworkDtoList.isEmpty()) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
	        	for(int i=0;i<homeworkDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("assignNo",homeworkDtoList.get(i).getAssignNo());
	        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
	        		jSONObject.put("registerDate", homeworkDtoList.get(i).getRegisterDate());
	        		jSONObject.put("dueDate", homeworkDtoList.get(i).getDueDate());
	        		jSONObject.put("importance", homeworkDtoList.get(i).getImportance());
	        		jSONObject.put("title",homeworkDtoList.get(i).getTitle());
	        		jSONObject.put("contents",homeworkDtoList.get(i).getContents());
	        		jSONObject.put("subNo",homeworkDtoList.get(i).getSubNo());
	        		jSONObject.put("success",homeworkDtoList.get(i).getSuccess());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	
	        	Collections.sort( jsonList, new Comparator<JSONObject>() {

	    		    public int compare(JSONObject a, JSONObject b) {
	    		        String valA = new String();
	    		        String valB = new String();
	    		        int vA,vB;


	    		        return valA.compareTo(valB);
	    		    }
	    		});
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<homeworkDtoList.size();i++){
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
