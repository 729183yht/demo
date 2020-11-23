package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 激活查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql="select * from tab_user where username=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    /**
     * 保存
     * @param user
     */
    @Override
    public void save(User user) {
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),user.getStatus(),user.getCode());
    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @Override
    public User activeByCode(String code) {
        User user = null;
        try {
            String sql="select * from tab_user where code=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {

        }
        return user;
    }

    /**
     * 修改激活状态
     * @param user
     */
    @Override
    public void updateStauts(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        User users = null;
        try {
            String sql="select * from tab_user where username=? and password=?";
            users = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(),user.getPassword());
        } catch (Exception e) {

        }
        return users;

    }


}
