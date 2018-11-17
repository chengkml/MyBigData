package com.ck.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ck.domain.DailyPlan;
import com.ck.orm.dao.DailyPlanDao;
import com.ck.orm.entity.DailyPlanPo;
import com.ck.repository.DailyPlanRepository;
import com.ck.service.base.BaseService;
import com.ck.vo.DailyPlanVo;
@Service
public class DailyPlanService extends BaseService<DailyPlanDao, DailyPlanRepository, DailyPlanPo, DailyPlan, DailyPlanVo>{

	@Override
	public DailyPlanVo modelToVo(DailyPlan model) {
		DailyPlanVo vo = new DailyPlanVo();
		if(null!=model) {
			BeanUtils.copyProperties(model, vo);
			return vo;
		}
		return null;
	}

	@Override
	public DailyPlan voToModel(DailyPlanVo vo) {
		DailyPlan model = new DailyPlan();
		if(null!=vo) {
			BeanUtils.copyProperties(vo, model);
			return model;
		}
		return null;
	}

}
