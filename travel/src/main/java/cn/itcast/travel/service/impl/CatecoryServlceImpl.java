package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CatecoryDao;
import cn.itcast.travel.dao.impl.CatecoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CatecoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CatecoryServlceImpl implements CatecoryService {
    private CatecoryDao dao=new CatecoryDaoImpl();

    /**
     *
     * 列表分类
     * @return
     */
    @Override
    public List<Category> findAll() {

        Jedis jedis=JedisUtil.getJedis();
        //查询redis看是否有数据
        //Set<String> cateforys = jedis.zrange("catefory", 0, -1);
        Set<Tuple> cateforys = jedis.zrangeWithScores("catefory", 0, -1);
        List<Category> cs=null;
        if(cateforys==null || cateforys.size()==0){
            System.out.println("数据库");
            cs=dao.findAll();
            for (int i = 0; i <cs.size() ; i++) {
                //这个cid当做分数存储，排序用
                jedis.zadd("catefory",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            System.out.println("redis");
            cs=new ArrayList<Category>();
            for (Tuple tuple : cateforys) {
                Category category=new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                cs.add(category);
            }
        }

        return cs;
    }
}
