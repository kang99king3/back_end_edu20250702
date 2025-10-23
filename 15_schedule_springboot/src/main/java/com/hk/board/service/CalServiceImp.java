package com.hk.board.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.dtos.CalDto;
import com.hk.board.mapper.CalMapper;
import com.hk.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CalServiceImp {

	@Autowired
	private CalMapper calMapper;
	
	@Autowired
	private Util util;
	
	//달력폼만드는 기능
	// 파리미터로 request객체를 전달받음---> 요청정보를 처리할 수 있는 환경
	public Map<String, Integer> makeCalendar(HttpServletRequest request) {
		
		String paramYear=request.getParameter("year");
		String paramMonth=request.getParameter("month");
		
		//달력에 필요한 값을 구하기 위해 calendar객체가 필요함
		Calendar cal=Calendar.getInstance();
		
		//paramYear등의 값이 null이 아니면 사용자가 원하는 달을 요청
		// 이외에 요청은 현재날짜로 처리
		int year=(paramYear==null)?
		cal.get(Calendar.YEAR):Integer.parseInt(paramYear);
		
		int month=(paramMonth==null)?
		cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth);
	
		//문제점: 월을 이동할때 11, 12, 13, 14... 또는 2, 1, 0, -1, -2...
		if(month>12) {
			month=1;
			year++;
		}
		
		if(month<1) {
			month=12;
			year--;
		}
		
		//1. 해당 월의 1일에 대한 요일을 구하기
		//  - 1~7숫자를 반환: 1은 일요일 ~ 7은 토요일
		cal.set(year, month-1,1);//월:0~11월 
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
	
		//2. 월의 마지막 날 구하기
		int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//달력 구현에 필요한 값을 map 저장
		Map<String, Integer>map=new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);
		
		return map;
	}
	
	//일정 추가하기
	public boolean insertCalBoard(InsertCalCommand insertCalCommand) {
		//command에는 id,title,content,y,m,d,h,m <-DB와 일치X
		//ymdhm --> mdate로 변환 작업
		//command --> dto 로 데이터 옮기기
		
		//"202510230939"--> mdate 12자리로 저장
		String mdate=insertCalCommand.getYear()
				    +util.isTwo(insertCalCommand.getMonth()+"")
				    +util.isTwo(insertCalCommand.getDate()+"")
				    +util.isTwo(insertCalCommand.getHour()+"")
				    +util.isTwo(insertCalCommand.getMin()+"");
		
		//command -> dto로 값을 복사해서 넣는 작업
		CalDto dto=new CalDto();
		dto.setId(insertCalCommand.getId());
		dto.setTitle(insertCalCommand.getTitle());
		dto.setContent(insertCalCommand.getContent());
		dto.setMdate(mdate);
		
		int count=calMapper.insertCalBoard(dto);
		
		return count>0?true:false;
	}

	
}













