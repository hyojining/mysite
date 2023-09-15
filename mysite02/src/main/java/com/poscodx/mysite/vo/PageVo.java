package com.poscodx.mysite.vo;

public class PageVo {
	private int postSize; // 페이지 당 게시물 개수
	private int pageSize; // 아래에 보여지는 페이지 개수
	private int totalPost; // 전체 게시물 개수
	private int totalPage; // 페이지 총 개수
	private int startPage;
	private int endPage;
	private int currentPage;
	private int nextPage;
	private int prevPage;
	
	public PageVo() {}
	
	public PageVo(int postSize, int pageSize, int totalPost, int totalPage, int startPage, int endPage, int currentPage, int nextPage, int prevPage) {
		this.postSize = postSize;
		this.pageSize = pageSize;
		this.totalPost = totalPost;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.currentPage = currentPage;
		this.nextPage = nextPage;
		this.prevPage = prevPage;
	}
	
	public int getPostSize() {
		return postSize;
	}
	public void setPostSize(int postSize) {
		this.postSize = postSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	@Override
	public String toString() {
		return "PageVo [postSize=" + postSize + ", pageSize=" + pageSize + ", totalPost=" + totalPost + ", totalPage="
				+ totalPage + ", startPage=" + startPage + ", endPage=" + endPage + ", currentPage=" + currentPage
				+ ", nextPage=" + nextPage + ", prevPage=" + prevPage + "]";
	}
}
