package hk.edu20250721.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D3_GBox<T> {
	
	public static void main(String[] args) {
		D3_GBox<String> strBox=new D3_GBox<>();
		strBox.set("hello Generic");
		System.out.println(strBox.get());
		
		D3_GBox<Integer>intBox=new D3_GBox<>();
		intBox.set(100);
		System.out.println(intBox.get());
		
		//타입매개변수 규칙 T,E,K,V,N
//		List<E> list=new ArrayList<>();
//		Map<K, V> map=new HashMap<>();

		//와일드 카드: <? extends T> T의 하위타입들 : 읽기 전용
		List<? extends Number> numbers=new ArrayList<Integer>();
//		numbers.add(500);//추가,수정작업을 할 수 없다.
		
		//데이터가 저장되어 있는 list객체 생성
		List<Integer> num=new ArrayList<Integer>();
		//데이터 추가
		num.add(100);num.add(101);num.add(102);num.add(103);
		//와일드 카드로 선언된 변수에 참조한다.
		numbers=num;
//		numbers.add(9393);//추가안됨
		System.out.println(numbers.toString());
		
		//<? super T> : T 또는 상위타입( 쓰기도 가능)
		List<? super Integer> ints=new ArrayList<Number>();
		ints.add(222);
	}
	
	private T item;
	
	public void set(T item) {
		this.item=item;
	}
	
	public T get() {
		return item;
	}
}
