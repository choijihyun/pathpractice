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
import com.homeworkNotice.dao.CompleteDao;
import com.homeworkNotice.dto.CompleteDto;


@Controller
public class CompleteController {
	
	@Autowired
	private CompleteDao completeDao;
	

	//insert
	@ResponseBody
    @RequestMapping(value = "/complete/insertComplete.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
    public String insertUser(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subNo", required=true) String subNo){ // 이렇게 5개의 파라미터를 받아오고 내용 안쓰면 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
		
		param.put("stuId",stuId);
		param.put("subNo",subNo);
		
		//
		
		//이 함수(url)은 회원가입이 주 목적이기 때문에
		//결과로 성공 or 실패만 알려 주면 돼
		//int 값으로 반환이 되는데 1이면 성공 나머지 값이면 실패!!
		int result=0;
		try {
			result=completeDao.insertComplete(param);
			
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
    @RequestMapping(value = "/complete/searchComplete", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String searchComplete(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) {

		HashMap<Object, Object> param=new HashMap<Object, Object>(); //각각의 id마다 hashmap 만들어주니까 생성을 해줌
		
		param.put("stuId",stuId);
		
		
    	List<CompleteDto> completeDtoList=completeDao.searchComplete(param);
    	
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!completeDtoList.isEmpty()) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
        	for(int i=0;i<completeDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("stuId",completeDtoList.get(i).getStuId());
        		jSONObject.put("subNo", completeDtoList.get(i).getSubNo());
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<completeDtoList.size();i++){
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


	//delete
	@ResponseBody
	@RequestMapping(value = "/complete/deleteAllComplete.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
	public String deleteAllComplete(//url에 맵핑(연결)된 함수
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
			@RequestParam(value = "stuId", required=true) String stuId) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		
		
		int result=completeDao.deleteAllComplete(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
				jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
		}
		else {//없으면 에러라고 브라우저에 뿌려준다
			jSONObject.put("result", "0"); //id가 존재하지 않는경우
		}
		return jSONObject.toString();//요청한 내용들을 반환해준다.
	}
	
	
	
	
	//delete
	@ResponseBody
	@RequestMapping(value = "/complete/deleteComplete.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//요 부분이 url //get방식으로 저 /user/getUserPwdInfo.json이라는 url로 들어와서 값을 확인 할 수 있다.
	public String deleteComplete(//url에 맵핑(연결)된 함수
			Locale locale, //안드로이드에서 받을 파라미터
			Model model, //안드로이드에서 받을 파라미터
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value="subNo",required=true) String subNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("subNo",subNo);
		
		
		int result=completeDao.deleteComplete(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
				jSONObject.put("result","1");//id도 존재하고 비번도 맞는 경우
		}
		else {//없으면 에러라고 브라우저에 뿌려준다
			jSONObject.put("result", "0"); //id가 존재하지 않는경우
		}
		return jSONObject.toString();//요청한 내용들을 반환해준다.
	}


}
