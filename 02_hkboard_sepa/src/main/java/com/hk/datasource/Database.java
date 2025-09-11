package com.hk.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	//1단계:드라이버로딩
	public Database() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//2단계:DB연결
	
	//6단계:DB닫기
	public void close(ResultSet rs, PreparedStatement psmt,
					  Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(psmt!=null) {
				psmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			System.out.println("6단계: DB닫기성공");
		} catch (SQLException e) {
			System.out.println("6단계: DB닫기실패");
			e.printStackTrace();
		}
	}
}






