package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hk.datasource.Database;
import com.hk.dtos.RoleStatus;
import com.hk.dtos.UserDto;

// 싱글톤 패턴 : 객체를 한번만 생성해서 사용하자
public class UserDao extends Database{

	private static UserDao userDao;
	//new를 사용못하게 생성자에 private을 선언한다.
	private UserDao() {}
	//객체를 한번만 생성해서 사용하는 기능을 구현
	public static UserDao getUserDao() {
		if(userDao==null) {
			userDao=new UserDao();
		}
		return userDao;
	}
	
	//사용자 기능
	
	//1. 회원가입 기능(enabled:"Y", role:"USER",regDate:SYSDATE())
	// insert문
	public boolean insertUser(UserDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO T_USER "
				 + " VALUES(?,?,?,?,?,?,'Y',?,SYSDATE()) ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTid());
			psmt.setString(2, dto.getTpassword());
			psmt.setString(3, dto.getTname());
			psmt.setString(4, dto.getTaddress());
			psmt.setString(5, dto.getTphone());
			psmt.setString(6, dto.getTemail());
			psmt.setString(7, String.valueOf(RoleStatus.USER));
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//2.ID 중복체크하기
	public String idCheck(String id) {
		String resultId=null;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT tid FROM T_USER WHERE tid=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				resultId=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return resultId;
	}
	
	//3.로그인 기능 : 파리미터 ID, PASSWORD
	public UserDto getLogin(String id,String password) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT tid,tname,trole FROM T_USER "
				 + " WHERE tid=? and tpassword=? and tenabled='Y' ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setTid(rs.getString(1));
				dto.setTpassword(rs.getString(2));
				dto.setTrole(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e); // 예외를 던져서 톰캣까지 올라가게 함
		}finally {
			close(rs, psmt, conn);
		}

		return dto;
	}
	
	//4. 나의 정보 조회
	public UserDto getUser(String id) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT tid,tname,taddress,tphone,temail,trole,regdate"
				 + " FROM T_USER "
				 + " WHERE tid=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
	
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setTid(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setTaddress(rs.getString(3));
				dto.setTphone(rs.getString(4));
				dto.setTemail(rs.getString(5));
				dto.setTrole(rs.getString(6));
				dto.setRegDate(rs.getDate(7));
			}
			System.out.println(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e); // 예외를 던져서 톰캣까지 올라가게 함
		}finally {
			close(rs, psmt, conn);
		}

		return dto;
	}
	
	//5.나의 정보 수정하기
	public boolean updateUser(UserDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE T_USER SET tADDRESS=? ,tphone=?, tEMAIL=? "
				 + " WHERE tID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTaddress());
			psmt.setString(2, dto.getTphone());
			psmt.setString(3, dto.getTemail());
			psmt.setString(4, dto.getTid());

			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//6.탈퇴하기
	public boolean delUser(String id) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE T_USER SET tENABLED='N' "
				 + " WHERE tID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);

			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	//관리자 기능
}






