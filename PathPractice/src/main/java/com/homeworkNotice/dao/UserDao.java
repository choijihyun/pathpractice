package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.UserDto;

/**
 * @writer      : JungHyun 
 * @createDate  : 2018. 1. 21.
 * @description : user테이블의 정보 가져옴~
 */

@Repository
public class UserDao {
	@Autowired
    private SqlSession sqlSession;    
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<UserDto> selectUser(HashMap<Object, Object> param) { 
		return sqlSession.selectList("UserDao.selectUser", param);//이건뭐에영??????? 
		//user-mapping.xml에 만들어놓은 sql(무기)을 userDao(캐릭터)가 사용하는 부분이야
	}
	public List<UserDto> selectAllList() {
		return sqlSession.selectList("UserDao.selectAllUser");//이거두 뭐에여???????
	}
	public int insertUser(HashMap<Object, Object> param) {
		return sqlSession.insert("UserDao.insertUser", param);		
	}
}
