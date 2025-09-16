package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		String sql=" INSERT INTO USERINFO "
				 + " VALUES(NULL,?,?,?,?,?,'Y',?,SYSDATE()) ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getPassword());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, String.valueOf(RoleStatus.USER));
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






