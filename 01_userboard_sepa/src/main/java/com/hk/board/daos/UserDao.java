package com.hk.board.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hk.board.dtos.UserDto;

//DAO객체: DataBase에 접근하는 객체
public class UserDao {
	//JDBC 6단계 구현
	//1단계: 드라이버 로딩
	public UserDao() {
		//강제 객체 생성
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("1단계:드라이버로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1단계:드라이버로딩 실패!");
			e.printStackTrace();
		}
	}
	
	//메서드 구현: JDBC 2~6단계 구현
	//회원목록 조회 기능: select문 (반환타입:List[DTO,DTO...])
	public List<UserDto> getAllUser(){
		//회원 정보 목록을 저장할 객체
		List<UserDto>list=new ArrayList<UserDto>();
		
		//Db에 연결하고 작업할 준비를 위한 객체 선언
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행할때 사용할 객체
		ResultSet rs=null;//쿼리 실행결과를 받을 객체
		
		//2단계:DB연결하기(localhost:3306,id,pw)
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="manager";
		
		//실행할 쿼리 준비
		String sql=" SELECT USERID, "
				 + "        NAME, "
				 + "        BIRTHYEAR, "
				 + "        ADDR, "
				 + "        MOBILE1, "
				 + "        MOBILE2, "
				 + "        HEIGHT, "
				 + "        MDATE "
				 + " FROM USERTBL ";
		
		try {
	//   servers에 Context.xml에 정의
//			<Resource name="jdbc/hk"
//		              auth="Container"
//		              type="javax.sql.DataSource"
//		              maxTotal="20"    
//		              maxIdle="10"
//		              maxWaitMillis="10000"
//		              username="root"
//		              password="manager"
//		              driverClassName="org.mariadb.jdbc.Driver"
//		              url="jdbc:mariadb://localhost:3306/hk"/>
			
//          web.xml에 설정
//			resource-ref 
			  // 1. JNDI 초기화(이름과 객체로 맵핑관리)
//            Context initCtx = new InitialContext();//Context에 접근하기 위한 객체생성
            //"java:comp/env" 접근가능한 환경 이름 공간으로 
            // -> context.xml에 등록한 Resource가 "java:com/env" 안에 등록된다.
//            Context envCtx  = (Context) initCtx.lookup("java:comp/env");

            // 2. DataSource 찾기
//            DataSource ds   = (DataSource) envCtx.lookup("jdbc/hk");

            // 3. Connection 얻기
//            conn = ds.getConnection();
			
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);//쿼리를 준비하는 중
			System.out.println("3단계:쿼리준비성공");
			rs=psmt.executeQuery();//쿼리결과를 반환함
			System.out.println("4단계:쿼리실행성공");
			//  DB : 쿼리를 실행해서 결과를 반환--> userId,name...
			//                           타입은  char, varchar..
			//  java:      문자열을 나타내는 타입은  String..
			// --> DB와 java는 타입자체가 다르게 표현되기 때문에 
			//     java에서 사용할 수 있는 타입으로 변환하는 작업이 필요
			while(rs.next()) {//next()는 rs에 값이 있는지 확인: true/false
				UserDto dto=new UserDto();//필통 만들었어요~~
				//select절에서 작성한 컬럼순서에 맞게 작성해야 된다.
				dto.setUserID(rs.getString(1));  
				dto.setName(rs.getString(2));
				dto.setBirthYear(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				dto.setMobile1(rs.getString(5));
				dto.setMobile2(rs.getString(6));
				dto.setHeight(rs.getInt(7));
				dto.setmDate(rs.getDate(8));
				//list는 가방
				list.add(dto);//마지막에 완성된 DTO를 담아야 한다.
				// list[dto(row),dto(row),dto(row)...]
			}
			System.out.println("5단계:쿼리결과받기 성공");
			//6단계:DB닫기 --> try에서 실패가 나더라도 반드시 닫아야 함
		} catch (SQLException e ) {
			System.out.println("2~5단계에서 실패");
			e.printStackTrace();
		} 
//		catch(NamingException e) {
//			e.printStackTrace();
//		}
		finally {
			//6단계는 finally에서 작성하자
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
				System.out.println("6단계:DB닫기성공");
			} catch (SQLException e) {
				System.out.println("6단계:DB닫기실패");
				e.printStackTrace();
			}
		}
		return list;
	}
	//회원등록 기능: insert문(반환타입:boolean)
	//전달받은 파라미터들을 현재 메서드에 전달해야 함
	public boolean insertUser(UserDto dto) {
		int count=0;//쿼리 성공 여부 판단을 위해 사용
		
		//DB에 연결할 정보를 정의
		//Db에 연결하고 작업할 준비를 위한 객체 선언
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행할때 사용할 객체
		
		//2단계:DB연결하기(localhost:3306,id,pw)
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="manager";
		
		String sql=" INSERT INTO USERTBL "
				 + " VALUES(?,?,?,?,?,?,?,SYSDATE()) ";
		
		try {
			conn=DriverManager.getConnection(url, user, password);
			psmt=conn.prepareStatement(sql);//쿼리준비 아직 진행중
			// 쿼리에 ?를 채우자
			psmt.setString(1, dto.getUserID());
			psmt.setString(2, dto.getName());
			psmt.setInt(3, dto.getBirthYear());
			psmt.setString(4, dto.getAddr());
			psmt.setString(5, dto.getMobile1());
			psmt.setString(6, dto.getMobile2());
			psmt.setInt(7, dto.getHeight());//쿼리준비가 끝남
			count=psmt.executeUpdate();//추가,수정,삭제작업은 executeUpdate()
			                     //반환값: 업데이트된 행의 개수(int)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count>0?true:false;
	}
	//회원정보 상세 조회: select문 (반환타입:DTO)
	public UserDto getUser(String userId) {
		UserDto dto=new UserDto();
		
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행할때 사용할 객체
		ResultSet rs=null;//쿼리 실행결과를 받을 객체
		
		//2단계:DB연결하기(localhost:3306,id,pw)
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="manager";
		
		//실행할 쿼리 준비
		String sql=" SELECT USERID, "
				 + "        NAME, "
				 + "        BIRTHYEAR, "
				 + "        ADDR, "
				 + "        MOBILE1, "
				 + "        MOBILE2, "
				 + "        HEIGHT, "
				 + "        MDATE "
				 + " FROM USERTBL "
				 + " WHERE USERID = ? ";
		
		try {
			//DB연결
			conn=DriverManager.getConnection(url, user, password);
			//쿼리 준비
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			//쿼리 실행
			rs=psmt.executeQuery();
			//쿼리 결과 받기
			while(rs.next()) {
			   // dto맴버필드 <==== ResultSet[컬럼]
				dto.setUserID(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirthYear(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				dto.setMobile1(rs.getString(5));
				dto.setMobile2(rs.getString(6));
				dto.setHeight(rs.getInt(7));
				dto.setmDate(rs.getDate(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	//회원정보 수정: update문(반환타입:boolean)
	public boolean updateUser(UserDto dto) {
		int count=0;//쿼리 성공 여부 판단을 위해 사용
		
		//DB에 연결할 정보를 정의
		//Db에 연결하고 작업할 준비를 위한 객체 선언
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행할때 사용할 객체
		
		//2단계:DB연결하기(localhost:3306,id,pw)
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="manager";
		
		// String pool 메모리영역: +연산자로 문자열 더하기
		//               -> 값이 변경될때마다 새롭게 객체가 생성
		String sql=" UPDATE USERTBL SET NAME=?, "
				+ "                   BIRTHYEAR=?, "
				+ "                   ADDR=?, "
				+ "                   MOBILE1=?, "
				+ "                   MOBILE2=?, "
				+ "                   HEIGHT=? "
				+ " WHERE USERID=? ";
		
		try {
			conn=DriverManager.getConnection(url, user, password);
			psmt=conn.prepareStatement(sql);//쿼리준비 아직 진행중
			// 쿼리에 ?를 채우자
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getBirthYear());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getMobile1());
			psmt.setString(5, dto.getMobile2());
			psmt.setInt(6, dto.getHeight());//쿼리준비가 끝남
			psmt.setString(7, dto.getUserID());
			count=psmt.executeUpdate();//추가,수정,삭제작업은 executeUpdate()
			                     //반환값: 업데이트된 행의 개수(int)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count>0?true:false;
	}
	//회원삭제: delete문(반환타입:boolean)
}








