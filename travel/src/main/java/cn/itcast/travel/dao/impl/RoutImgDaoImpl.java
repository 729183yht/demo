package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.routImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoutImgDaoImpl implements routImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findImgs(int rid_img) {
        String sql="select * from tab_route_img where rid=?";
        List<RouteImg> routeImgs = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid_img);
        return routeImgs;
    }
}
