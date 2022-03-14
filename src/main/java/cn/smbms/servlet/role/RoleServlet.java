package cn.smbms.servlet.role;


import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.role.RoleServiceImpl;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <pre>
 * 任务：
 * 描述：(这里用一句话描述这个类的作用)
 * 作者：
 * 时间：
 * </pre>
 */

public class RoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (null != method && method.equals("add")){

        }else if(method != null && method.equals("getRoleList")){
            this.getRoleList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    private void getRoleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //查询角色列表

        String queryName = request.getParameter("queryName");
        String pageIndex = request.getParameter("pageIndex");

        //查询用户列表
        int queryUserRole = 0;

        //获取用户列表

        RoleService roleService = new RoleServiceImpl();

        //第一次走页面一定是第一页,页面大小固定的
        List<Role> roles = null;
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;
        /**
         * http://localhost:8090/SMBMS/userlist.do
         * ----queryUserName --NULL
         * http://localhost:8090/SMBMS/userlist.do?queryname=
         * --queryUserName ---""
         */
        if(queryName == null){
            queryName = "";
        }

        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                response.sendRedirect("error.jsp");
            }
        }
        //总数量（表）
        int totalCount	= roleService.getRoleCount(queryName);
        //总页数
        PageSupport pages=new PageSupport();

        pages.setCurrentPageNo(currentPageNo);

        pages.setPageSize(pageSize);

        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }


        roles = roleService.getRoleList(queryName, currentPageNo, pageSize);
        request.setAttribute("roles", roles);

        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("rolelist.jsp").forward(request, response);
    }
}
