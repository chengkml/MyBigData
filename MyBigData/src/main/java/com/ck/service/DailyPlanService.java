package com.ck.service;

import org.springframework.stereotype.Service;

import com.ck.domain.DailyPlan;
import com.ck.orm.dao.DailyPlanDao;
import com.ck.orm.entity.DailyPlanPo;
import com.ck.repository.DailyPlanRepository;
import com.ck.service.base.BaseService;
@Service
public class DailyPlanService extends BaseService<DailyPlanDao, DailyPlanRepository, DailyPlanPo, DailyPlan> {

}
