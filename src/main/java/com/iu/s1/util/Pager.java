package com.iu.s1.util;

public class Pager {

	// 현재 페이지번호
	private Long curPage;

	// 한 페이지당 출력 갯수
	private Integer perPage;

	// 시작 번호
	private long startRow;

	// 마지막 번호
	private long lastRow;

	// 전체 페이지
	private long totalPage;

	// 전체 블럭 (1-5, 6-10 의 총 갯수)
	private long totalBlock;

	// 현재 블럭
	private long curBlock;

	// 시작 블럭 번호
	private long startNum;

	// 마지막 블럭 번호
	private long lastNum;

	// 검색을 위한 변수 선언
	private String kind;
	private String search;

	public void makeRow() {
		// curpage로 row 계산하기
		this.startRow = this.getCurPage() - 1;
		this.lastRow = (this.getCurPage() * this.getPerPage());
	}

	public void makePage(long totalCount) {

		this.totalPage = totalCount / this.getPerPage();
		if (totalCount % this.getPerPage() != 0) {
			this.totalPage++;
		}

		long perBlock = 5L;
		this.totalBlock = totalPage / perBlock;
		if (totalPage % perBlock != 0) {
			this.curBlock++;
		}

		this.curBlock = this.curPage / perBlock;
		if (this.curPage % perBlock != 0) {
			curBlock++;
		}

		this.startNum = (this.curBlock - 1) * perBlock + 1;
		this.lastNum = curBlock * perBlock;

		if (this.curBlock == this.totalBlock) {
			this.lastNum = this.totalPage;
		}

	}

	public Long getCurPage() {
		if (this.curPage == null || this.curPage == 0) {
			this.curPage = 1L;
		}
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public Integer getPerPage() {

		if (this.perPage == null || this.perPage == 0) {
			this.perPage = 10;
		}

		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getLastRow() {
		return lastRow;
	}

	public void setLastRow(long lastRow) {
		this.lastRow = lastRow;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(long totalBlock) {
		this.totalBlock = totalBlock;
	}

	public long getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(long curBlock) {
		this.curBlock = curBlock;
	}

	public long getStartNum() {
		return startNum;
	}

	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}

	public long getLastNum() {
		return lastNum;
	}

	public void setLastNum(long lastNum) {
		this.lastNum = lastNum;
	}

	public String getKind() {
		if (this.kind == null) {
			this.kind = "";
		}
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		if (this.search == null) {
			this.search = "";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
