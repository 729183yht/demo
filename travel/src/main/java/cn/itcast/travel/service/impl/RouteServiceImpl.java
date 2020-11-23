package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RoutImgDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.dao.routImgDao;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    private routImgDao imgDao=new RoutImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    /**
     * 列表页分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public pageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname) {

        pageBean<Route> pageBean=new pageBean<Route>();
        //总记录数
        int totalCount=dao.findTotalCount(cid,rname);
        //分页查询开始，查询详情记录数
        int start=(currentPage-1)*pageSize;
        List<Route> list=dao.findByPage(cid,start,pageSize,rname);
        //当前页
        pageBean.setCurrenPage(currentPage);
        //每页显示条数
        pageBean.setRows(pageSize);
        pageBean.setList(list);
        //总记录数
        pageBean.setTotalCount(totalCount);
        //总页码
        int totalPage=totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pageBean.setTotalPage(totalPage);


        return pageBean;
    }

    /**
     *
     * 详情页数据
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        Route route = dao.findOne(rid);
        int rid_img = route.getRid();
        List<RouteImg> imgs = imgDao.findImgs(rid_img);
        route.setRouteImgList(imgs);
        int sid = route.getSid();
        Seller seller = sellerDao.findByid(sid);
        route.setSeller(seller);
        int count = dao.findCount(rid);
        route.setCount(count);

        return route;
    }

    @Override
    public boolean ifFavorite(String rid, int uid) {
        Favorite favorite = dao.ifFavorite(Integer.parseInt(rid), uid);

        return favorite!=null;
    }

    /**
     * 用户添加收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public void addFavorite(String rid, int uid) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss SSS");

        Date date=new Date();
        String dateStr=sdf.format(date);
        dao.addFavorite(rid,dateStr,uid);

    }


}
