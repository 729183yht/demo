package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

public interface RouteService {
    /**
     * 列表页分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public pageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rame);

    public Route findOne(int rid);

    public boolean ifFavorite(String rid, int uid);

    public void addFavorite(String rid, int uid);
}
