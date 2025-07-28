package hk.edu20250728.day18;

public class D2_RunableTest implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println("나는 Runable을 구현한 스레드야");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void test() {
		
	}

}
