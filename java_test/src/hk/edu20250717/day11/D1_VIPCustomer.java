package hk.edu20250717.day11;

public class D1_VIPCustomer extends D1_Customer{

	//다시 동일한 내용을 작성할 필요 없이 부모의 멤버필드를 사용하면 된다.
//	private int customerID;//고객ID
//	private String customerName;//고객이름
//	private String customerGrade;//고객등급
//	private int bonusPoint;//보너스 포인트
//	private double bonusRatio;//보너스 적립률
	
	private int agentID;//담당 상담원 ID
	private double saleRatio;//할인율
	
	public D1_VIPCustomer() {
		super();
	}
	public D1_VIPCustomer(int customerID, String customerName,int agentID) {
		super(customerID, customerName);
	}
	
	
}







