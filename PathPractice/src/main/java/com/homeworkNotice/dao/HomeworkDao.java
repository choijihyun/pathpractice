package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.HomeworkDto;

@Repository
public class HomeworkDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	public int insertHomework(HashMap<Object,Object> param) {
		return sqlSession.insert("HomeworkDao.insertHomework",param);
	}
	
	
}
