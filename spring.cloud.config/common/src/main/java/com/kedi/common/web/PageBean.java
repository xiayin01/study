package com.kedi.common.web;

/**
 * 用于分页控制的bean
 *
 * @author xy
 */
public class PageBean {

    /**
     * 每页显示数量
     */
    private int pageSize;
    /**
     * 当前页码
     */
    private int curPage;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 总记录数
     */
    private int total;

    /**
     * 默认无参构造器，初始化各值
     */
    public PageBean() {
        this.pageSize = 10;
        this.curPage = 1;
        this.pages = 0;
        this.total = 0;
    }

    public void calculate(int total) {
        this.setTotal(total);
        this.pages = (total / pageSize) + ((total % pageSize) > 0 ? 1 : 0);
        // 如果当前页码超出总页数，自动更改为最后一页
        this.curPage = Math.min(this.curPage, pages);
    }

    /**
     * 获取分页起始位置和偏移量
     *
     * @return 分页起始位置和偏移量数组
     */
    public int[] paginate() {
        // 数量为零时,直接从0开始
        return new int[]{total > 0 ? (curPage - 1) * pageSize : 0, pageSize};
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 500 : pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage <= 0 ? 1 : curPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
