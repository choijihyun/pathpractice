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

import com.homeworkNotice.dao.HomeworkDao;
import com.homeworkNotice.dto.HomeworkDto;

@Controller
public class HomeworkController {

	@Autowired
	private HomeworkDao	homeworkDao;
	
	@ResponseBody
    @RequestMapping(value = "/homework/insertHomework.json", method = RequestMethod.GET)// value라는 값에 매핑, get방식 사용
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
}
