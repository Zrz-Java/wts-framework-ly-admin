package com.base.util.context;

/**
 * 分页类
 * @author percyLee
 *
 */
public class PageUtil {

	/**
	 * 当前页
	 */
	private int currPage;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 总记录数
	 */
	private int totalRecord;

	/**
	 * 分页大小
	 */
	private int pageSize;

	/**
	 * 分页偏移量
	 */
	private int pageOfset;

	/**
	 * 是否第一页
	 */
	private boolean isFirstPage;

	/**
	 * 是否最后一页
	 */
	private boolean isLastPage;
	
	/**
	 * 分页请求转发页
	 */
	private String forward;
	
	/**
	 * 是否跳转
	 */
	private boolean redirect;

	public PageUtil() {
		this.pageSize = 20;
	}

	public PageUtil(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public PageUtil(int currPage , int pageSize) {
		this.pageSize = pageSize;
		this.currPage = currPage;
	}

	public int getCurrPage() {
		if (currPage != 0) {
			return currPage;
		}
		currPage = 1;
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageOfset() {
		pageOfset = (this.currPage - 1) * this.pageSize;
		return pageOfset;
	}

	public void setPageOfset(int pageOfset) {
		this.pageOfset = pageOfset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		if (pageSize != 0) {
			totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
			return totalPage;
		}
		totalPage = 0;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isFirstPage() {
		if (currPage == 1) {
			isFirstPage = true;
			return isFirstPage;
		}
		isFirstPage = false;
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		if (currPage == getTotalPage() || getTotalPage() == 0) {
			isLastPage = true;
			return isLastPage;
		}
		isLastPage = false;
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

}
