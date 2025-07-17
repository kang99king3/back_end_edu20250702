package hk.edu20250717.day11;

public class D1_Customer {

	private int customerID;//고객ID
	private String customerName;//고객이름
	private String customerGrade;//고객등급
	private int bonusPoint;//보너스 포인트
	private double bonusRatio;//보너스 적립률
	
	//default생성자: 나중에 ID,Name 값을 따로 추가해야 된다.
	public D1_Customer() {
		customerGrade="SILVER";
		bonusRatio=0.01;
	}

	//ID와 Name을 바로 추가할 수 있다.
	public D1_Customer(int customerID, String customerName) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		customerGrade="SILVER";
		bonusRatio=0.01;
	}
	
	//보너스 적립률 계산해서 보너스 포인트 추가
	public int calcPrice(int price) {
		bonusPoint+=price*bonusRatio;
		return price;
	}

//	@Override
//	public String toString() {
//		return "D1_Customer [customerID=" + customerID + ", customerName=" + customerName + ", customerGrade="
//				+ customerGrade + ", bonusPoint=" + bonusPoint + ", bonusRatio=" + bonusRatio + "]";
//	}
	
	@Override
	public String toString() {
		return customerName+"님의 등급은 "+customerGrade
				           +"이며, 보너스 포인트는 "+bonusPoint+"입니다.";
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}

	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public double getBonusRatio() {
		return bonusRatio;
	}

	public void setBonusRatio(double bonusRatio) {
		this.bonusRatio = bonusRatio;
	}
	
}












