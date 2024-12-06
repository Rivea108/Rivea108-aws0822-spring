package com.myaws.myapp.domain; 
 
public class MemberVo {   
	
	private int midx;
	private String memberid;
	private String memberpwd;
	private String nickname;
	private String profile_image;
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMemberpwd() {
		return memberpwd;
	}
	public void setMemberpwd(String memberpwd) {
		this.memberpwd = memberpwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String Nickname) {
		this.nickname = Nickname;
	}
	public String getMemberprofileimage() {
		return profile_image;
	}
	public void setMemberprofileimage(String memberprofile_image) {
		this.profile_image = memberprofile_image;
	}
}