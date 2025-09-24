package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.dtos.HkDto;
import com.hk.datasource.Database;

public class HkDao extends Database{

	public HkDao() {
		super();//생략되어 있음
	}
	
	//글목록 조회 기능: 여러개의 행이 반환 --> 반환타입? List
	public List<HkDto> getAllList(){
		List<HkDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT TSEQ, TID, TTITLE,TCONTENT, TREGDATE, DELFLAG "
				+ " FROM T_BOARD ORDER BY TREGDATE DESC ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				//java  <==  DB : DB에 값들을 java에서 사용할 수 있게 처리
				HkDto dto=new HkDto();
				dto.setTseq(rs.getInt(1));
				dto.setTid(rs.getString(2));
				dto.setTtitle(rs.getString(3));
				dto.setTcontent(rs.getString(4));
				dto.setTregdate(rs.getDate(5));
				dto.setDelflag(rs.getString(6));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	
	//아이디 검색 글목록 조회 기능: 
	public List<HkDto> getSearchList(String id){
		List<HkDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT TSEQ, TID, TTITLE,TCONTENT, TREGDATE, DELFLAG "
				+ " FROM T_BOARD"
				+ " where tid=? "
				+ " ORDER BY TREGDATE DESC ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				//java  <==  DB : DB에 값들을 java에서 사용할 수 있게 처리
				HkDto dto=new HkDto();
				dto.setTseq(rs.getInt(1));
				dto.setTid(rs.getString(2));
				dto.setTtitle(rs.getString(3));
				dto.setTcontent(rs.getString(4));
				dto.setTregdate(rs.getDate(5));
				dto.setDelflag(rs.getString(6));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	
	//글추가하기: insert문실행 , 반환값 boolean
	public boolean insertBoard(HkDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO T_BOARD "
				+ " VALUES(NULL,?,?,?,SYSDATE(),'N') ";
		
		try {
			conn=getConnection();
			
			//3단계:쿼리준비, (1,dto.getId()) 여기서 1은 ?의 순서
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTid());
			psmt.setString(2, dto.getTtitle());
			psmt.setString(3, dto.getTcontent());
			
			count=psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	//글상세보기: select where절  반환값은 HkDto
	public HkDto getBoard(int seq) {
		HkDto dto=new HkDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT TSEQ, TID, TTITLE, TCONTENT, TREGDATE, DELFLAG "
				+ " FROM T_BOARD WHERE TSEQ = ? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);// seq의 타입이 int형-> setInt()사용
			rs=psmt.executeQuery();
			while(rs.next()) {
				//java  <==  DB : DB에 값들을 java에서 사용할 수 있게 처리
				// 인덱스 순서는 select절에 작성한 컬럼순서와 일치
				dto.setTseq(rs.getInt(1));
				dto.setTid(rs.getString(2));
				dto.setTtitle(rs.getString(3));
				dto.setTcontent(rs.getString(4));
				dto.setTregdate(rs.getDate(5));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	
	//글 수정하기: update문 실행 , 반환타입 boolean
	//          전달받는 파라미터: seq, title, content
	public boolean updateBoard(HkDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE T_BOARD SET "
				 + " TTITLE=? , "
				 + " TCONTENT=? "
				 + " WHERE TSEQ=? ";
		
		try {
			conn=getConnection();
			
			//3단계:쿼리준비, (1,dto.getId()) 여기서 1은 ?의 순서
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTtitle());
			psmt.setString(2, dto.getTcontent());
			psmt.setInt(3, dto.getTseq());
			
			count=psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	public boolean deleteBoard(int seq) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql="UPDATE T_BOARD SET DELFLAG = 'Y' WHERE TSEQ=? ";
		
		try {
			conn=getConnection();
			
			//3단계:쿼리준비, (1,dto.getId()) 여기서 1은 ?의 순서
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			count=psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
}







