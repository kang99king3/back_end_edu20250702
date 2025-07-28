package hk.edu20250725.day17;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class D3_IOTest {

	public static void main(String[] args) {
//		test01();
		test02();
	}
	
	//파일을 읽고 출력하기
	public static void test01() {
		InputStream in=null;//입력파이프 선언
		OutputStream out=null;//출력파이프 선언
		
		try {
			in=new FileInputStream("C:\\Users\\user\\HelloJava.java");
			out=new FileOutputStream("C:\\Users\\user\\output_Hello.java");
			
			int i=0;//byte단위로 읽어서 데이터(실제데이터)를 저장할 변수
			while((i=in.read())!=-1) {// 읽어들일 데이터가 없으면 -1을 리턴
				System.out.print((char)i);
				out.write(i);//파일 출력하는 코드
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//마지막에 실행됐던 스트림부터 닫는다.
				if(out!=null) {
					out.close();					
				}
				if(in!=null) {
					in.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//filter: 보조스트림을 이용해서 출력하기
	public static void test02() {
		String s="파일을 기록합니다.";
		String ss="파일을 문자단위로 기록합니다.";
		OutputStream out=null;//출력 파이프 준비
		OutputStreamWriter ow=null;//filter이며, 문자 인코딩 처리 기능
		BufferedWriter bw=null;//filter이며, 버퍼를 이용한 성능향상
		
		try {
			out=new FileOutputStream("C:\\Users\\user\\output_data.java");
//			out.write(s.getBytes("utf-8"));
			
			//File바이트출력스트림 --> 문자기반출력필터 끼우고 있음
			// ==============ㅁㅁㅁㅁ
//			ow=new OutputStreamWriter(out,"utf-8");
//			ow.write(ss);
			
			//버퍼를 활용
			ow=new OutputStreamWriter(out, "utf-8");
			//버퍼를 이용해서 성능향상(많은 양이 문자를 모아서 한번에 처리)
			bw=new BufferedWriter(ow);
			bw.write(ss);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) {
					bw.close();
				}
				if(ow!=null) {
					ow.close();
				}
				if(out!=null) {
					out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}












