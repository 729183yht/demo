package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserServlce {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 用户激活
     * @param code
     * @return
     */
    public boolean activeUser(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);
}
