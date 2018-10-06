package com.homeworkNotice.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homeworkNotice.dao.SubjectDao;
import com.homeworkNotice.dto.CompleteDto;
import com.homeworkNotice.dto.SubjectDto;
import com.homeworkNotice.dao.UserDao;

@Controller
public class SubjectController {

	@Autowired
	private SubjectDao	subjectDao;
	private UserDao userDao;
	
	

	//����� insert
	@ResponseBody
	@RequestMapping(value = "/subject/insertSubject", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String insertSubject(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "subNo", required=false) String subNo,
			@RequestParam(value = "class", required=false) String clas, //class ���� �������� �̷��� ���� ! ��Ÿ�ƴ�
			@RequestParam(value = "subName", required=true) String subName,
			@RequestParam(value = "day", required=true) String day,
			@RequestParam(value = "classRoom", required=false) String classRoom,
			@RequestParam(value = "profName", required=false) String profName,
			@RequestParam(value = "startHour", required=false) String startHour,
			@RequestParam(value = "endHour", required=false) String endHour,
			@RequestParam(value = "add", required=false) String add
			){
		//add���� �й�
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("subNo",subNo);
		param.put("class",clas);
		param.put("subName",subName);
		param.put("day",day);
		param.put("classRoom",classRoom);
		param.put("profName",profName);
		param.put("startHour",startHour);
		param.put("endHour",endHour);
		param.put("add",add);


		int result=0;
		try {
			result=subjectDao.insertSubject(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//�׷��� ���⼭ ���� or ���� �����ؼ� �ȵ���̵忡 json �����͸� ����� �������ٰž�
    	if(result==1) {
    		jSONObject.put("result", "1");//����    		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

	@ResponseBody
    @RequestMapping(value = "/subject/searchDirSubject", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String searchDirSubject(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "add", required=true) String add) {

		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("add",add);
		
    	List<SubjectDto> subjectDtoList=subjectDao.searchDirSubject(param);
    	
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!subjectDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
        	for(int i=0;i<subjectDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("add",subjectDtoList.get(i).getAdd());
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	
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
	
	
	//search
	@ResponseBody
	@RequestMapping(value = "/subject/searchSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.

	public String searchSubject(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "word", required=true) String word){

		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("word",word);			
		
		System.out.println("TTTT :::: "+word);
		List<SubjectDto> subjectDtoList =subjectDao.searchSubject(param);	

    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
		
    	if(!subjectDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
        	for(int i=0;i<subjectDtoList.size();i++) {

        		JSONObject jSONObject = new JSONObject();
        		if(subjectDtoList.get(i).getAdd().equals("0")) {
        			
        		jSONObject.put("subNo",subjectDtoList.get(i).getSubNo());
        		jSONObject.put("class",subjectDtoList.get(i).getClass());
        		jSONObject.put("subName",subjectDtoList.get(i).getSubName());
        		jSONObject.put("day", subjectDtoList.get(i).getDay());
        		jSONObject.put("classroom", subjectDtoList.get(i).getClassRoom());
        		jSONObject.put("profName", subjectDtoList.get(i).getProfName());
        		jSONObject.put("startHour",subjectDtoList.get(i).getStartHour());
        		jSONObject.put("endHour",subjectDtoList.get(i).getEndHour());
        		jSONObject.put("add",subjectDtoList.get(i).getAdd());
        		jSONObject.put("subjectKey", subjectDtoList.get(i).getSubjectKey());
        		
        		}
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
        else {

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}
	

	@ResponseBody
    @RequestMapping(value = "/subject/deleteSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String deleteSubject(
    			Model model,
    			@RequestParam(value = "add", required=true) String add) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
    			
		param.put("add",add);	
		//
		System.out.println(param);
		
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=subjectDao.deleteSubject(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//�׷��� ���⼭ ���� or ���� �����ؼ� �ȵ���̵忡 json �����͸� ����� �������ٰž�
    	if(result==1) {
    		jSONObject.put("result", "1");//����     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

}
	 