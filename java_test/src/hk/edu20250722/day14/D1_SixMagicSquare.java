package hk.edu20250722.day14;

import java.util.Arrays;

public class D1_SixMagicSquare {

	public static void main(String[] args) {
		D1_SixMagicSquare magic=new D1_SixMagicSquare(10);
		magic.makeA();
		magic.makeB();
		magic.makeCD();
		for (int i = 0; i < magic.magic.length; i++) {
			System.out.println(Arrays.toString(magic.magic[i]));			
		}
	}
	
	public int [][] magic;
	
	public D1_SixMagicSquare() {
		this(6);
	}
	
	public D1_SixMagicSquare(int n) {
		this.magic=new int[n][n];
	}
	
	public void make() {
		
	}
	
	//A영역에 n/4이 되는 영역을 3으로 채우는 기능    33
	//i인덱스가 n/4과 같아지는 지점에서는 j+1을 하자 ->33
	public void makeA() {
		int n=magic.length;
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/4; j++) {
				if(i==n/4) {//i가 중간지점에 위치하면..
					magic[i][j+1]=3;//j+1하자
				}else {
					magic[i][j]=3;
				}
			}
		}
	}
	
	//B영역에 2로 채우는데 마지막열만 1로 채우자
	//먼저 1로 다 채우고, 2로 채우자로 진행
	public void makeB() {
		int n=magic.length;
		//1을 먼저 B영역에 모두 채운다
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				magic[i][j+n/2]=1;
			}
		}
		
		//2를 채우자
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2-(n/4-1); j++) {
				magic[i][j+n/2]=2;
			}
		}
	}
	
	//A영역의 값을  --> C영역에 반대로 값을 넣기: 3->0, 0->3 변환
	public void makeCD() {
		int n=magic.length;
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				//A영역에 접근해서 값을 출력
//				System.out.println(magic[i][j]);
				if(magic[i][j]==3) {
					magic[i+n/2][j]=0;//c영역
				}else {
					magic[i+n/2][j]=3;//c영역
				}
				//B영역에 값 출력
//				System.out.println(magic[i][j+n/2]);
				if(magic[i][j+n/2]==1) {
//					System.out.print(i+","+(j+n/2));
					magic[i+n/2][j+n/2]=2;//D영역
				}else {
					magic[i+n/2][j+n/2]=1;//D영역
				}
			}
		}
	}
	
	// 각 영역에 값에, (n/2)*(n/2)계산된 결과값을 각각 곱한다.
	public void multi() {
		
	}
}












