package com.ck.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.controller.base.BaseController;
import com.ck.domain.DailyPlan;
import com.ck.orm.dao.DailyPlanDao;
import com.ck.orm.entity.DailyPlanPo;
import com.ck.repository.DailyPlanRepository;
import com.ck.service.DailyPlanService;
@RestController
@RequestMapping(value="/plan/")
public class DailyPlanController extends BaseController<DailyPlanPo, DailyPlan, DailyPlanDao, DailyPlanRepository, DailyPlanService>{
	
}
