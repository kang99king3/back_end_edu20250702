package com.hk.board.main;

import com.hk.board.dao.AnsDao;
import com.hk.board.dto.AnsDto;

public class TestMain {
	
	public static void main(String[] args) {
		AnsDao dao=new AnsDao();
		
		dao.insertBoard(new AnsDto(0, "hk", "제목테스트", "내용테스트", 
								   null, 0, 0, 0, null, null));
		
		
	}
}
