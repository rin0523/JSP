package handler;

import domain.PagingVO;

public class PagingHandler {

	
	private int startPage;
	private int endPage;
	private int realdEndPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;

	
	public PagingHandler(PagingVO pgvo,int totalCount) {
		this.pgvo=pgvo;
		this.totalCount=totalCount;
		
		this.endPage=(int)Math.ceil(pgvo.getPageNo()/(double)pgvo.getQty())*pgvo.getQty();
		this.startPage=this.endPage-9;
		
		this.realdEndPage=(int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		
		if(this.realdEndPage<this.endPage) {
			this.endPage=this.realEndPage;
		}
		
		this.prev=this.startPage>1;
		this.next=this.endPage<this.realdEndPage;
		
		
	}



	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealdEndPage() {
		return realdEndPage;
	}

	public void setRealdEndPage(int realdEndPage) {
		this.realdEndPage = realdEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}

	@Override
	public String toString() {
		return "PagingHandler [startPage=" + startPage + ", endPage=" + endPage + ", realdEndPage=" + realdEndPage
				+ ", prev=" + prev + ", next=" + next + ", totalCount=" + totalCount + ", pgvo=" + pgvo + "]";
	}
	
	
	
	
	
	
}
