package com.yiyi.crm.workbench.web.controller;
import com.yiyi.crm.settings.domain.User;
import com.yiyi.crm.settings.service.UserService;
import com.yiyi.crm.settings.service.impl.UserServiceImpl;
import com.yiyi.crm.utils.DateTimeUtil;
import com.yiyi.crm.utils.PrintJson;
import com.yiyi.crm.utils.ServiceFactory;
import com.yiyi.crm.utils.UUIDUtil;
import com.yiyi.crm.vo.PaginationVo;
import com.yiyi.crm.workbench.domain.Activity;
import com.yiyi.crm.workbench.service.ActivityService;
import com.yiyi.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    protected void service( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println ("进入到市场控制器" );
        String path=request.getServletPath ();
        if ("/workbench/activity/getUserList.do".equals (path)){
           getUserList(request,response);
        }else  if ("/workbench/activity/save.do".equals (path)) {
            save(request,response);
        }else  if ("/workbench/activity/pageList.do".equals (path)) {
            pageList(request,response);
        }
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
            System.out.println ("进入到查询市场活动信息列表的操作（结合添加查询和分页查询）" );
            String name=request.getParameter ("name");
            String owner=request.getParameter ("owner");
            String startDate=request.getParameter ("startDate");
            String endDate=request.getParameter ("endDate");

            String pageNoStr=request.getParameter ("pageNo");
            int pageNo=Integer.parseInt (pageNoStr);

            String pageSizeStr=request.getParameter ("pageSize");
            int  pageSize=Integer.parseInt (pageSizeStr);
            //计算出略过的记录数
            int skipCount=(pageNo-1)*pageSize;
            Map<String,Object> map=new HashMap<> ();
            map.put ("name",name);
            map.put ("owner",owner);
            map.put ("startDate",startDate);
            map.put ("endDate",endDate);
            map.put ("skipCount",skipCount);
            map.put ("pageSize",pageSize);
            ActivityService as= (ActivityService) ServiceFactory.getService (new ActivityServiceImpl ());
            PaginationVo<Activity> vo=as.pageList(map);
            PrintJson.printJsonObj (response,vo);
        }
    private void save( HttpServletRequest request, HttpServletResponse response) {
        System.out.println ("执行市场活动的添加操作");

        String id = UUIDUtil.getUUID ( );
        String owner = request.getParameter ("owner");
        String name = request.getParameter ("name");
        String startDate = request.getParameter ("startDate");
        String endDate = request.getParameter ("endDate");
        String cost = request.getParameter ("cost");
        String description = request.getParameter ("description");
        //创建时间：当前系统时间
        String createTime=DateTimeUtil.getSysTime ();
        //当前登录人
        String createBy=((User) request.getSession ().getAttribute ("user")).getName ();

        Activity a=new Activity ();
        a.setCost (cost);
        a.setCreateBy (createBy);
        a.setDescription (description);
        a.setEndDate (endDate);
        a.setId (id);
        a.setName (name);
        a.setOwner (owner);
        a.setStartDate (startDate);
        a.setCreateTime (createTime);

        ActivityService as= (ActivityService) ServiceFactory.getService (new ActivityServiceImpl ());
        boolean flag=as.save(a);

        PrintJson.printJsonFlag (response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println ("取得用户信息列表" );
        UserService us= (UserService) ServiceFactory.getService (new UserServiceImpl ());
        List<User> uList=us.getUserList();
        PrintJson.printJsonObj (response,uList);

    }
}
