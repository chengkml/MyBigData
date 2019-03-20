package com.ck.service;

import org.springframework.stereotype.Service;

import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.DailyPlanItemRepository;
import com.ck.service.base.BaseService;
@Service
public class DailyPlanItemService extends BaseService<DailyPlanItemDao, DailyPlanItemRepository, DailyPlanItemPo, DailyPlanItem> {
	
}
