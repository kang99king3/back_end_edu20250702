package com.hk.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.CalDto;

@Mapper // myBatis에서 제공하는 어노테이션--> 스프링에서 사용
public interface CalMapper {
	// 메서드명과 calMapper.xml에 쿼리 ID를 일치 시킨다.
	public int insertCalBoard(CalDto dto);
	public List<CalDto> calBoardList(Map<String, String>map);
	public boolean calMulDel(Map<String, String[]>map);
}





