package com.digimaple.eims.utils;

import java.util.List;

public class PageBean<T> {
    private Integer firstPage  =  1;  //起始页
    private Integer currentpage ; // 当前页
    private Integer prePage; // 上一页
    private Integer nextPage; // 下一页
    private Integer lastPage; // 最后一页
    private Integer pageSize;   //页面大小
    private Integer totalPage;  //总页数
    private int totalCount; //总条数 总数居
    private List<T>    list;// list集合用来装查出的数据

    public PageBean(){
        super();
    }

    public PageBean(Integer currentpage, Integer pageSize, int totalCount) {
        this.currentpage = currentpage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        /**
         * 为页面中的尾页、上一页、下一页、总页数赋值
         */
        // 总页数：总数据/每页数据 。 如果除不尽则向上取整
        this.totalPage = (this.totalCount % this.pageSize == 0) ? (this.totalCount / this.pageSize) : (this.totalCount / this.pageSize + 1);
        //尾页：等于总页数
        this.lastPage = this.totalPage;
        // 上一页：如果传入当前页页码小于等于1则直接赋值为1
        this.prePage = (this.currentpage <= 1) ? (1) : (this.currentpage - 1);
        // 下一页:如果传入当前页页码大于等于总页数则赋值为总页数
        this.nextPage = (this.currentpage >= this.totalPage) ? (this.totalPage) : (this.currentpage + 1);

    }

    @Override
    public String toString() {
        return "PageBean{" +
                "firstPage=" + firstPage +
                ", currentpage=" + currentpage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", lastPage=" + lastPage +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(Integer currentpage) {
        this.currentpage = currentpage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //匹配起始索引0和起始页1
    public Integer getStart()
    {
        return (this.firstPage - 1)*this.pageSize;
    }
}
