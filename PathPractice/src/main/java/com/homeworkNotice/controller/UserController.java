 package com.homeworkNotice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dto.UserDto;


//C:\Users\����\.m2\repository
//���̺귯�� ������
//���̺귯���� �������°�? �ȵǸ� �� ��η� ���� �ȿ��ִ� ���̺귯���� �� ����� �ٽ� �غ��� ��!

//�ܺ�(�ȵ���̵�)���� ���� ������ url�� ��û�ϸ� �̸��� ���� �ȴ�! 
@Controller
public class UserController {
	
	//�̱������� ������ userDao�� ��ƿ�
    @Autowired //���࿡ ���� ������ id �� ������ autowired �ƴ϶� �ٸ��ŷ� ��������;;
    private UserDao userDao;
    
    //������ ���� ��û �� �ϳ��� �Լ�
    //�ȵ���̵忡�� ��й�ȣ�� �޶�� ��û�ϴ� �Լ�.
	@ResponseBody
    @RequestMapping(value = "/user/getUserPwdInfo.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
    public String getUserPwdInfo(//url�� ����(����)�� �Լ�
    			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
    			Model model, //�ȵ���̵忡�� ���� �Ķ����
    			@RequestParam(value = "stuId", required=true) String stuId) {//�ȵ���̵忡�� ���� �Ķ����, ��� ��� �ϳ��� ���� ��
				// ?id=1 �̷������� ġ�� 1�� �ش��ϴ� password�� ������ �Լ��ε� ���⿡�� @RequestParam �κ��� id��� �Ÿ� �޾��� ��? �� ������ٰ� ���� ��
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();// �̺κ��� �� �𸣰ھ��!!!!!!!!!!!!!!!!!!!!!!!!!
		//xml�� sql���� �ʿ�� �ϴ� ������ �ִٸ� ���⿡ ��Ƽ� ��������. �� �Լ����� id����?(�ؿ� ���̴�)
		
		param.put("stuId",stuId);		
		//�Ķ���Ϳ� �ȵ���̵忡�� �ǳ׹��� id�� ����Ѵ�
    	List<UserDto> userDtoList=userDao.selectUser(param);//������ ����� ������ user-mapping.xml ����	
    	//id���� userdto ����Ʈ�� ������
    	//�� �Ķ���͸� sql�� �����ش�!! �׷��� userDtoList ���⿡ ��ȯ.
    	
    	//�ٵ� �� �κп��� UserDao�� selectUser�� ���ݾƾƿ� 
    	//�ٵ� �� UserDao���� return sqlSession.selectList("~~~~~~ �̷��� ���ִµ� �̰� ������???
    	//userDao(ĳ����)�� xml�� sql(����)�� ����ؼ� ����� ������ userDtoList�� �����ϴ°ž�
    	//���⸦ ����ϴ� ������ sqlSession.selectList �̰� �����Ű�°��� ����
    	
    	
    	//Dao4. Dao3���� �����ؼ� ���� sql�� userDao.?????�� ���� ����ϸ� �ǰ�, �Ķ���� �ִ¹��� �ٷ� ���� ������??����
    	
    	JSONObject jSONObject = new JSONObject();
    	if(!userDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
        	jSONObject.put("pw", userDtoList.get(0).getPw()); 
        	jSONObject.put("email", userDtoList.get(0).getEmail());
        	//���⵵ �ñ�!!!! �� userDtoList.get(0)�� �� �ǹ��ϴ°ſ���???
        	//db���� ���� ����Ʈ�� �� ù��° �ε����� �������°ž� (�ٵ� ��� id�� �������� �׻� 1���ۿ� ������;;;����)
    	}
    	else {//������ ������� �������� �ѷ��ش�
    		jSONObject.put("result", "no data");
    	}
    	System.out.println(jSONObject.toString());
    	return jSONObject.toString();//��û�� ������� ��ȯ���ش�.
    }
	

	
	//�ȵ���̵忡�� ����� ȸ������ url�̾�!!
	//���� Ų �Ŀ� ũ��,�ͽ��÷η� ������ �ƹ��ų��� url ġ�� �����ٰ�
	//http://localhost:8080/main/user/insertUser.json?id=test123&password=test123&name=test123&grade=1&email=test123
	//�� url�� �Է��غ���
	//success �ƴٴ°� ���������ž� ����
	//�Ϻη� �� �ؿ��� �ּ� �� �����ϱ� � �������� ȥ�� �����غ��� ���ȳ��� ���� �Լ��� �ö󰡼�
	//�ּ� ���� �����غ� ����
	@ResponseBody
    @RequestMapping(value = "/user/insertUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String insertUser(
    			Model model,
    			@RequestParam(value = "name", required=true) String name,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "semester", required=true) final int semester,
    			@RequestParam(value = "pw", required=true) String pw,
    			@RequestParam(value = "email", required=true) String email) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
		param.put("name",name);		
		param.put("stuId",stuId);		
		param.put("semester",semester);		
		param.put("pw",pw);		
		param.put("email",email);
		//
		System.out.println(param);
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=userDao.insertUser(param);
			
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
    @RequestMapping(value = "/user/updateUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String updateUser(
    			Model model,
    			@RequestParam(value="name",required=true) String name,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "semester", required=true) final int semester,
    			@RequestParam(value = "pw", required=true) String pw,
    			@RequestParam(value = "email", required=true) String email) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
		param.put("name", name);
		param.put("stuId",stuId);		
		param.put("semester",semester);		
		param.put("pw",pw);		
		param.put("email",email);
		//
		System.out.println(param);
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=userDao.updateUser(param);
			
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
	
	
	
	//id�� �ش��ϴ� pw�� �ٲ� �����̰� �Ѻκ�!!!
	@ResponseBody
    @RequestMapping(value = "/user/updatePw.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String updatePW(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "pw", required=true) String pw) { 
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
			
		param.put("stuId",stuId);	
		param.put("pw",pw);	
		
		System.out.println(param);
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=userDao.updatePw(param);
			
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
    	System.out.println(jSONObject.toString());
    	return jSONObject.toString();
	}




	@ResponseBody
	@RequestMapping(value = "/user/checkUser.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String checkUser(//url�� ����(����)�� �Լ�
			HttpSession session,
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value = "pw", required=true) String pw) {
		
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("pw",pw);
		
		
		System.out.println(param);
		List<UserDto> userDtoList=userDao.selectUser(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(!userDtoList.isEmpty() && userDtoList.size()==1) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
			if(pw.equals(userDtoList.get(0).getPw())) {
				jSONObject.put("result","1");//id�� �����ϰ� ����� �´� ���
				System.out.println("id : "+stuId);
				session.setAttribute("id",stuId);
			}
			else {
				jSONObject.put("result","0");//����� �ٸ� ���
			}
		}
		else {//������ ������� �������� �ѷ��ش�
			jSONObject.put("result", "0"); //id�� �������� �ʴ°��
		}
		//System.out.println(jSONObject.toString());
		return jSONObject.toString();//��û�� ������� ��ȯ���ش�.
	}
	
	@ResponseBody
    @RequestMapping(value = "/user/getAllUserData.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
    public String getAllUserData(//url�� ����(����)�� �Լ�
    			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
    			Model model,
    			@RequestParam(value = "select", required=true) final int select) {//�ȵ���̵忡�� ���� �Ķ����, ��� ��� �ϳ��� ���� ��

    	List<UserDto> userDtoList=userDao.selectAllList();//������ ����� ������ user-mapping.xml ����
    	System.out.println(userDtoList);
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!userDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
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
        else {//������ ������� �������� �ѷ��ش�

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}
	
	
	
}