package com.hk.ansboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.ansboard.dtos.AnsDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor //생성자를 통해 주입하는 방법
public class AnsDaoImp implements IAnsDao{

//	@Autowired
	private final SqlSessionTemplate sqlSession;
	
	private String namespace="com.hk.ansboard.dao.";
	
	@Override
	public List<AnsDto> getAllList(String pnum) {
		Map<String, String>map=new HashMap<>();
		map.put("pnum", pnum);
		return sqlSession.selectList(namespace+"boardlist",map);
	}

	@Override
	public int getPCount() {
		return sqlSession.selectOne(namespace+"getpcount");
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		int count=sqlSession.insert(namespace+"insertboard", dto);
		return count>0?true:false;
	}

	@Override
	public AnsDto getBoard(int seq) {
		Map<String,Integer> map=new HashMap<>();
		map.put("seq", seq);
		//boardlist 쿼리를 동적쿼리로 작성해서 상세보기도 가능함
		return sqlSession.selectOne(namespace+"boardlist", map);
	}

	@Override
	public boolean readCount(int seq) {
		int count=sqlSession.update(namespace+"readcount",seq);
		return count>0?true:false;
	}

	@Override
	public boolean boardUpdate(String title, String content, int seq) {
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

	@Override
	public boolean replyBoard(AnsDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

}
