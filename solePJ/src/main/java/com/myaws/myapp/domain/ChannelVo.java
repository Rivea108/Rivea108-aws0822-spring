package com.myaws.myapp.domain;

import java.sql.Timestamp;

public class ChannelVo {

	private int cidx;
	private String cname;
	private String cdesc;
	private Timestamp c_created_at;
	
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	public Timestamp getC_created_at() {
		return c_created_at;
	}
	public void setC_created_at(Timestamp c_created_at) {
		this.c_created_at = c_created_at;
	}
}
