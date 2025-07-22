package hk.edu20250722.day14;

import java.util.Arrays;

import hk.edu20250717.day11.D3_OddMagicSquare;

public class D1_SixMagicSquare {

	public static void main(String[] args) {
		D1_SixMagicSquare magic=new D1_SixMagicSquare(10);
		magic.makeA();
		magic.makeB();
		magic.makeCD();
		magic.multi();
		magic.makeAdd();
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
		makeA();
		makeB();
		makeCD();
		multi();
		makeAdd();
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
		int n=magic.length;
		int m=(n/2)*(n/2);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
//				magic[i][j]=magic[i][j]*m;
				magic[i][j]*=m;
			}
		}
	}
	
	//각각의 영역별로 홀수 마방진 값을 더하자
	public void makeAdd() {
		int n=magic.length;
		//홀수마방진--->구현한 적이 있네... 재활용하자..
		D3_OddMagicSquare odd=new D3_OddMagicSquare(n/2);
		odd.make();//홀수마방진 만들어짐
		int[][]oddMagic=odd.magic;//생성된 배열 가져오기
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				magic[i][j]+=oddMagic[i][j];
				magic[i][j+n/2]+=oddMagic[i][j];
				magic[i+n/2][j]+=oddMagic[i][j];
				magic[i+n/2][j+n/2]+=oddMagic[i][j];
			}
		}
		
	}
	
	//---마방진 증명 확인---
		//가로의 합,  1차원배열내용:[6  1  8] , 2차원배열이기때문에 [0] : [0]->[1]->[2]  
		public int sumCol(int i) {
			int tot=0;
			//중간위치 코드 구현 : tot에 값을 더해가는 코드
			for (int j = 0; j < magic.length; j++) {
				tot+=magic[i][j];
			}
			return tot;
		}
		//세로의 합 : [0]->[1]->[2] : [0] 
		public int sumRow(int j) {
			int tot=0;
			for (int i = 0; i < magic.length; i++) {
				tot+=magic[i][j];
			}
			return tot;
		}
		//대각선의 합(왼쪽에서 내려가는)
		public int sumDia() {
			int tot=0;
			for (int i = 0; i < magic.length; i++) {
				tot+=magic[i][i];
			}
			return tot;
		}
		//대각선의 합(오른쪽에서 내려가는)
		public int sumReverseDia() {
			int tot=0;
			for (int i = 0; i < magic.length; i++) {
				tot+=magic[i][magic.length-1-i];//[i]:[2]->[1]->[0]
			}
			return tot;
		}
		//각각의 합을 구해서 같은지 확인하는 메서드
		//sum*메서드들을 활용해서 각각의 합이 같은지 확인
		public boolean isCheck() {
			boolean isC=true;
			
			int n=magic.length;
			int [] ma=new int[n*2+2];// 3*2+2=8  n*2+2=12
			
			//각각의 결과를 배열ma에 저장하자
			for (int i = 0; i < n; i++) {
				ma[i]=sumCol(i);//ma[0],[1],[2] 에 sumCol결과값을 저장
				ma[i+n]=sumRow(i);//ma[3],[4],[5] 에 sumRow결과값을 저장
			}
			ma[n*2]=sumDia();//ma[6]에 저장
			ma[n*2+1]=sumReverseDia();//ma[7]에 저장
			
			System.out.println(Arrays.toString(ma));
			
			//다른 값이 있는지 확인하자
			for (int i = 1; i < ma.length; i++) {
				if(ma[0]!=ma[i]) {//0번째 값과 다음 값들을 비교해서 다른 값이 있는 지 확인
					isC=false;
					break;
				}
			}
			return isC;
		}
		
		public void magicPrint() {
			int n=magic.length;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(magic[i][j]+"\t");
				}
				System.out.println(sumCol(i));//가로의 합
				System.out.println();
			}
			for (int i = 0; i < n; i++) {
				System.out.print(sumRow(i)+"\t");
			}
			System.out.println("\n대각선의 합:"+sumDia()+","+sumReverseDia());
			System.out.println("마방진 확인:"+isCheck());
		}
}












