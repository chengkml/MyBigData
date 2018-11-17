package com.ck.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.base.BaseRepositoryTest;

public class DailyPlanItemRepositoryTest extends BaseRepositoryTest<DailyPlanItemRepository, DailyPlanItemDao, DailyPlanItemPo, DailyPlanItem>{
	
	@Autowired
	private DailyPlanItemDao dao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByPlanId() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		dao.save(po);
		po = new DailyPlanItemPo();
		po.setId("2");
		po.setPlanId("1");
		dao.save(po);
		assertEquals(repo.findByPlanId("1").size(), 2);
	}
}
