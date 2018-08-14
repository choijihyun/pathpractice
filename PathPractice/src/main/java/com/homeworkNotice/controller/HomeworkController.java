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
import com.homeworkNotice.dto.UserDto;

@Controller
public class HomeworkController {

	@Autowired
	private HomeworkDao	homeworkDao;
	
	@ResponseBody
    @RequestMapping(value = "/homework/insertHomework.json", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String insertUser(
    			Model model,

    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "success", required=true) final int success) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
		param.put("stuId",stuId);			
		param.put("dueDate",dueDate);		
		param.put("importance",importance);		
		param.put("title",title);
		param.put("contents",contents);
		param.put("subNo",subNo);
		param.put("success",success);
		
		//
		
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=homeworkDao.insertHomework(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


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
	@RequestMapping(value = "/homework/deleteHomework.json", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String deleteHomework(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value="assignNo",required=true) final int assignNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("assignNo",assignNo);
		
		
		int result=homeworkDao.deleteHomework(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
				jSONObject.put("result","1");//id�� �����ϰ� ����� �´� ���
		}
		else {//������ ������� �������� �ѷ��ش�
			jSONObject.put("result", "0"); //id�� �������� �ʴ°��
		}
		return jSONObject.toString();//��û�� ������� ��ȯ���ش�.
	}

	
	//update
	@ResponseBody
    @RequestMapping(value = "/homework/updateHomework.json", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String updateUser(
    			Model model,

    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "success", required=true) final int success) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
		param.put("stuId",stuId);			
		param.put("dueDate",dueDate);		
		param.put("importance",importance);		
		param.put("title",title);
		param.put("contents",contents);
		param.put("subNo",subNo);
		param.put("success",success);
		
		//
		
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=homeworkDao.updateHomework(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


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
