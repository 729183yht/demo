package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserServlce;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServlceImpl implements UserServlce {
    private UserDao userdao=new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        User u = userdao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userdao.save(user);

        String content="请点击激活文件<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>激活邮件</a>进行激活";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 用户激活
     * @return
     * @param code
     */
    @Override
    public boolean activeUser(String code) {
        User user = userdao.activeByCode(code);
        if(user!=null){
            userdao.updateStauts(user);
            return true;
        }
        return false;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User users = userdao.login(user);

        return users;
    }
}
