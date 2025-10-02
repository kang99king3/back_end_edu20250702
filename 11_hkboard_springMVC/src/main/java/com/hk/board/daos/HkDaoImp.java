package com.hk.board.daos;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.HkDto;

//Dao 클래스: 쿼리를 실행하는 클래스
@Repository
public class HkDaoImp implements IHkDao{

	private String namespace="com.hk.board.dao.";
	
	//스프링이 객체의 타입을 확인해서 주입해줌
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<HkDto> getAllList() {
		return sqlSession.selectList(namespace+"boardlist");
	}

	@Override
	public boolean insertBoard(HkDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HkDto getBoard(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBoard(HkDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBoard(int seq) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		// TODO Auto-generated method stub
		return false;
	}

}
