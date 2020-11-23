package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

import java.util.List;

public interface RouteDao {

    /**
     * 查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount (int cid,String rname);
    /**
     * 列表页分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid, int currentPage, int pageSize,String rname);

    /**
     * 查询详情route实体类
     * @param id
     * @return
     */
    public Route findOne(int id);

    public Favorite ifFavorite(int rid, int uid);

    public int findCount(int rid);

    public void addFavorite(String rid,String date, int uid);
}
