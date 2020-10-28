package com.yiyi.crm.workbench.service;

import com.yiyi.crm.vo.PaginationVo;
import com.yiyi.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);

    PaginationVo<Activity> pageList(Map<String, Object> map);
}
