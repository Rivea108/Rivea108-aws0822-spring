package com.myaws.myapp.domain;

import java.time.LocalDateTime;

public class ReplyVo {

	    private int ridx;                
	    private int midx;                 
	    private int bidx;                 
	    private String contents;          
	    private LocalDateTime ridx_created_at;  
	    private LocalDateTime ridx_modified;    
	    private String rdelyn;        
	    private String rip;
	    
		public int getRidx() {
			return ridx;
		}
		public void setRidx(int ridx) {
			this.ridx = ridx;
		}
		public int getMidx() {
			return midx;
		}
		public void setMidx(int midx) {
			this.midx = midx;
		}
		public int getBidx() {
			return bidx;
		}
		public void setBidx(int bidx) {
			this.bidx = bidx;
		}
		public String getContents() {
			return contents;
		}
		public void setContents(String contents) {
			this.contents = contents;
		}
		public LocalDateTime getRidx_created_at() {
			return ridx_created_at;
		}
		public void setRidx_created_at(LocalDateTime ridx_created_at) {
			this.ridx_created_at = ridx_created_at;
		}
		public LocalDateTime getRidx_modified() {
			return ridx_modified;
		}
		public void setRidx_modified(LocalDateTime ridx_modified) {
			this.ridx_modified = ridx_modified;
		}
		public String getRidx_delyn() {
			return rdelyn;
		}
		public void setRdelyn(String rdelyn) {
			this.rdelyn = rdelyn;
		}
		public String getRip() {
			return rip;
		}
		public void setRip(String rip) {
			this.rip = rip;
		}        
	    
	    
	
}

 