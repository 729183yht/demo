package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BeanServlet {

    private RouteService service=new RouteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname=request.getParameter("rname");
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");
        int cid=0;
        if(cidStr!=null && cidStr.length()>0 && !"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        int currentPage=0;
        if(currentPageStr!=null && currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }

        int pageSize=0;
        if(pageSizeStr!=null && pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }
        if(rname==null || "null".equals(rname)){
            rname="";
        }
        pageBean<Route> pageBean = service.pageQuery(cid, currentPage, pageSize,rname);

        writerValue(pageBean,response);


    }

    /**
     *
     * 详情页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        int rid=0;
        if(ridStr!=null && ridStr.length()>0){
            rid=Integer.parseInt(ridStr);
        }
        Route route = service.findOne(rid);
        writerValue(route,response);

    }

    /**
     * 判断是否收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void ifFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid=0;
        }else {
            uid=user.getUid();
        }
        boolean flg=service.ifFavorite(rid,uid);

        System.out.println(flg);
            writerValue(flg,response);


    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        boolean flg=false;
        if(user==null){
            //没登录
            writerValue(flg,response);
        }else {
            service.addFavorite(rid,user.getUid());
            flg=true;
            writerValue(flg,response);
        }



    }


}
