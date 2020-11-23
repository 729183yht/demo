package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CatecoryDao {
    /**
     * 列表分类
     * @return
     */
    public List<Category> findAll();
}
