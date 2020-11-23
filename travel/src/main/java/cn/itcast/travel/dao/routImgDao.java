package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface routImgDao {
    public List<RouteImg> findImgs(int rid_img);
}
