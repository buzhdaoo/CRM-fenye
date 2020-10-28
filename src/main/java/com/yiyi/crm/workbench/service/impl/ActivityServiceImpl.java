package com.yiyi.crm.workbench.service.impl;

import com.yiyi.crm.utils.SqlSessionUtil;
import com.yiyi.crm.vo.PaginationVo;
import com.yiyi.crm.workbench.dao.ActivityDao;
import com.yiyi.crm.workbench.domain.Activity;
import com.yiyi.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao= SqlSessionUtil.getSqlSession ().getMapper (ActivityDao.class);

    @Override
    public boolean save(Activity a) {
        boolean flag=true;
        int count=activityDao.save(a);
        if (count!=1){
            flag=false;

        }
        return flag;
    }

    @Override
    public PaginationVo<Activity> pageList(Map<String, Object> map) {
        //去total
        int total=activityDao.getTotalByCondition(map);
        //去datalist
        List<Activity> datalist=activityDao.getActivityListByCondition(map);
        //创建一个vo对象将total和datalist封装到vo中
        PaginationVo<Activity>vo= new PaginationVo<> ( );
        vo.setTotal (total);
        vo.setDataList (datalist);
        return vo;
    }
}
