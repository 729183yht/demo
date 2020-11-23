package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查询总记录数
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid,String rname) {
        /*String sql="select count(*) from tab_route where cid=?";*/
        String sql="select count(*) from tab_route where 1=1 ";

        StringBuilder sb=new StringBuilder(sql);
        List parms=new ArrayList();
        if(cid!=0){
            sb.append(" and cid = ? ");
            parms.add(cid);
        }
        if(rname!=null && rname.length()>0){
            sb.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }
        sql=sb.toString();
        return template.queryForObject(sql,Integer.class,parms.toArray());
    }

    /**
     * 分页查询详情数据
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int currentPage, int pageSize,String rname) {
       /* String sql="select * from tab_route where cid=? limit ? , ?";*/
        String sql="select * from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List parms=new ArrayList();
        if(cid!=0){
            sb.append(" and cid = ? ");
            parms.add(cid);
        }
        if(rname!=null && rname.length()>0){
            sb.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");

        parms.add(currentPage);
        parms.add(pageSize);
        sql=sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),parms.toArray());
    }
    /**
     * 查询详情route实体类
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql="select * from tab_route where rid=?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);

        return route;

    }
    @Override
    public Favorite ifFavorite(int rid, int uid) {
        Favorite favorite=null;
        try {
            String sql="select * from tab_favorite where rid=? and uid=?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);

        } catch (DataAccessException e) {

        }
        return favorite;
    }

    @Override
    public int findCount(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        int count = template.queryForObject(sql, Integer.class, rid);
        return count;
    }

    @Override
    public void addFavorite(String rid,String date, int uid) {

        String sql="insert into tab_favorite(rid,date,uid) values(?,?,?)";
        template.update(sql,rid,date,uid);
    }
}
