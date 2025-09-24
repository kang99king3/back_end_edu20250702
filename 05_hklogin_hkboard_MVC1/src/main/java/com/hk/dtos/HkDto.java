package com.hk.dtos;

import java.io.Serializable;
import java.util.Date;

// DTO: 데이터를 저장해서 전달할때 사용하는 객체
public class HkDto implements Serializable{

	private static final long serialVersionUtid = 5066693606872502219L;
	
	//은닉화: 중요한 데이터는 맴버필드에 바로 접근 못하게 처리
	private int tseq;
	private String tid;
	private String ttitle;
	private String tcontent;
	private Date tregdate;
	private String delflag;
	
	//default 생성자: 단독으로 사용할 경우는 생략가능
	//              -> 생성자 오버로딩을 하면 생략X
	public HkDto() {

	}
	
	//생성자 오버로딩: 객체 생성할때 초기화도 동시에 하고 싶을 경우
	public HkDto(int tseq, String tid, String ttitle, String tcontent, Date tregdate) {
		super();
		this.tseq = tseq;
		this.tid = tid;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tregdate = tregdate;
	}

	public HkDto(String tid, String ttitle, String tcontent) {
		super();
		this.tid = tid;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
	}
	
	public HkDto(int tseq, String ttitle, String tcontent) {
		super();
		this.tseq = tseq;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
	}

	public int getTseq() {
		return tseq;
	}

	public void setTseq(int tseq) {
		this.tseq = tseq;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTtitle() {
		return ttitle;
	}

	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}

	public String getTcontent() {
		return tcontent;
	}

	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}

	public Date getTregdate() {
		return tregdate;
	}

	public void setTregdate(Date tregdate) {
		this.tregdate = tregdate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	//부모의 메서드를 자식이 재정의한다.
	// parentObj.method() --> 자식메서드가 호출된다. 
	@Override
	public String toString() {
		return "HkDto [tseq=" + tseq + ", tid=" + tid + ", ttitle=" + ttitle + ", tcontent=" + tcontent + ", tregdate="
				+ tregdate + ", delflag=" + delflag + "]";
	}

	
	
	
}






