/**
 * 版   本 ：    1.0
 * 创建日期 ：    2014年11月24日
 * 作   者 ：    张云飞  
 */
package com.maven.util.json;

/**
 * @author zyf
 *
 */
class PaginationJsonResult extends CollectionJsonResult {

	// 返回总记录数
	private long total;

	// 每页数量
	private int pageSize;

	// 当前页数
	private int page;

	// 总页数
	private int totalPage;

	PaginationJsonResult() {

	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 总记录数
	 *
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	

}
