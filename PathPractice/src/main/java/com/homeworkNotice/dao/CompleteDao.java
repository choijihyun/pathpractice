package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.CompleteDto;

@Repository
public class CompleteDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	public int insertComplete(HashMap<Object,Object> param) {
		return sqlSession.insert("CompleteDao.insertComplete",param);
	}
	
	public List<CompleteDto> searchComplete(HashMap<Object,Object> param) {
		return sqlSession.selectList("CompleteDao.searchComplete",param);//�̰ŵ� ������???????
	}
	
	public int deleteComplete(HashMap<Object, Object> param) {
		return sqlSession.delete("CompleteDao.deleteComplete",param);
	}
	
	public int deleteAllComplete(HashMap<Object, Object> param) {
		return sqlSession.delete("CompleteDao.deleteAllComplete",param);
	}

}
