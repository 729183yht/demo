package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     */
    public void save(User user);

    /**
     * 激活验证码
      * @param code
     * @return
     */
    public User activeByCode(String code);

    /**
     * 更新激活状态
     * @param user
     */
    public void updateStauts(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);
}
