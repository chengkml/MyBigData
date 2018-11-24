package com.ck.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.DailyPlanItemRepository;
import com.ck.service.base.BaseService;
import com.ck.vo.DailyPlanItemVo;
@Service
public class DailyPlanItemService extends BaseService<DailyPlanItemDao, DailyPlanItemRepository, DailyPlanItemPo, DailyPlanItem,DailyPlanItemVo>{

	@Override
	public DailyPlanItemVo modelToVo(DailyPlanItem model) {
		DailyPlanItemVo vo = new DailyPlanItemVo();
		if(null!=model) {
			BeanUtils.copyProperties(model, vo);
			return vo;
		}
		return null;
	}

	@Override
	public DailyPlanItem voToModel(DailyPlanItemVo vo) {
		DailyPlanItem model = new DailyPlanItem();
		if(null!=vo) {
			BeanUtils.copyProperties(vo, model);
			return model;
		}
		return null;
	}
	
}
