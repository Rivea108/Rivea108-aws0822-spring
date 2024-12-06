package com.myaws.myapp.domain;

public class SubscribeVo {

	private int sidx;
	private int midx;
	private int cidx;
	private String subscribeDate;
	private boolean isActive; 
	
	public String getSubscribeDate() {
		return subscribeDate;
	}
	public void setSubscribeDate(String subscribeDate) {
		this.subscribeDate = subscribeDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getSidx() {
		return sidx;
	}
	public void setSidx(int sidx) {
		this.sidx = sidx;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	
}
