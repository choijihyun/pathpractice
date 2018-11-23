package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.BlackboardDto;

@Repository
public class BlackboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	public List<BlackboardDto> getAnnounce(HashMap<Object,Object> param) {
		return sqlSession.selectList("blackboardDao.getAnnounce",param);//�̰ŵ� ������???????
	}
	
	public List<BlackboardDto> getSubject(HashMap<Object,Object> param) {
		return sqlSession.selectList("blackboardDao.getSubject",param);//�̰ŵ� ������???????
	}

}
