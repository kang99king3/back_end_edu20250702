package hk.edu20250717.day11;

public class D1_CustomerMain {

	public static void main(String[] args) {
		D1_Customer customerLee=new D1_Customer();//부모의 타입으로 부모 생성함
		customerLee.customerID=10001;
		customerLee.customerName="이순신";
		customerLee.bonusPoint=1000;
		int price=customerLee.calcPrice(15000);
		System.out.println(customerLee.toString());
		
		//자식의 타입으로 자식을 생성
		D1_VIPCustomer customerKim=new D1_VIPCustomer();
		customerKim.customerID=10002;
		customerKim.customerName="김유신";
		customerKim.bonusPoint=10000;
		int price4=customerKim.calcPrice(15000);
		System.out.println(customerKim);
		
		//부모의 타입으로 부모 생성함
		D1_Customer customerLee2=new D1_Customer(10001,"이순신");
		customerLee2.bonusPoint=1000;
		int price2=customerLee2.calcPrice(15000);
		System.out.println(customerLee2.toString()+price2);
		
		//자식의 타입으로 자식을 생성
		D1_VIPCustomer customerKim2=new D1_VIPCustomer(10002,"김유신",20001);
		customerKim2.bonusPoint=10000;
		int price3=customerKim2.calcPrice(15000);
		System.out.println(customerKim2.toString()+price3);
	}
}





