package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CatecoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CatecoryDaoImpl implements CatecoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 列表分类
     * @return
     */
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";

        List<Category> category = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return category;
    }
}
