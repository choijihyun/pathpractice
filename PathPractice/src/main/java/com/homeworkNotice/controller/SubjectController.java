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

import com.homeworkNotice.dao.SubjectDao;
import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.UserDto;
import com.homeworkNotice.dto.SubjectDto;

@Controller
public class SubjectController {

	@Autowired
	private SubjectDao	subjectDao;
	
	//search
	@ResponseBody
	@RequestMapping(value = "/subject/searchSubject.json", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String searchSubject(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "word", required=true) String word) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("word",word);			
		
		System.out.println(word);
		List<SubjectDto> subjectDtoList =subjectDao.searchSubject(param);	

    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
		
    	if(!subjectDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
        	for(int i=0;i<subjectDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("subNo",subjectDtoList.get(i).getSubNo());
        		jSONObject.put("subName",subjectDtoList.get(i).getSubName());
        		jSONObject.put("day", subjectDtoList.get(i).getDay());
        		jSONObject.put("classroom", subjectDtoList.get(i).getClassRoom());
        		jSONObject.put("profName", subjectDtoList.get(i).getProfName());
        		jSONObject.put("startHour",subjectDtoList.get(i).getStartHour());
        		jSONObject.put("endHour",subjectDtoList.get(i).getEndHour());
        		
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	/*
        	Collections.sort( jsonList, new Comparator<JSONObject>() {

    		    public int compare(JSONObject a, JSONObject b) {
    		        String valA = new String();
    		        String valB = new String();
    		        int vA,vB;
    		        
    		      
    		        switch(select) {
    		        case 1: vA = (Integer) a.get("assignNo");vB = (Integer) b.get("assignNo");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        case 2: valA = (String) a.get("stuId");valB = (String) b.get("stuId");break;
    		        case 3: valA = (String) a.get("registerDate");valB = (String) b.get("registerDate");break;
    		        case 4: valA = (String) a.get("dueDate");valB = (String) b.get("dueDate");break;
    		        case 5: vA = (Integer) a.get("importance");vB = (Integer) b.get("importance");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        case 6: valA = (String) a.get("title");valB = (String) b.get("title");break; 
    		        case 7: valA = (String) a.get("contents");valB = (String) b.get("contents");break;
    		        case 8: valA = (String) a.get("subNo");valB = (String) b.get("subNo");break;
    		        case 9: vA= (Integer)a.get("success");vB = (Integer) b.get("success");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        }

    		        return valA.compareTo(valB);
    		    }
    		});  */
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<subjectDtoList.size();i++){
        		jSONArray.add(jsonList.get(i));
        	}
        	
        	JSONObject jsObject=new JSONObject();
        	jsObject.put("result", jSONArray);

            return jsObject.toString();
        } 
        else {//������ ������� �������� �ѷ��ش�

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}
}
