package com.ck.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.DailyPlan;
import com.ck.orm.dao.DailyPlanDao;
import com.ck.orm.entity.DailyPlanPo;
import com.ck.repository.base.BaseRepositoryTest;

public class DailyPlanRepositoryTest extends BaseRepositoryTest<DailyPlanRepository, DailyPlanDao, DailyPlanPo, DailyPlan>{
	@Autowired
	private DailyPlanDao dao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlanByRange() {
		addDailyPlan();
		Date start = DateUtils.addDays(new Date(), -3);
		Date end = new Date();
		assertEquals(2, repo.getPlanByRange("ck", start, end, 0,5).getTotalElements());
	}

	private void addDailyPlan() {
		DailyPlanPo po = new DailyPlanPo();
		po.setId("1");
		po.setCreateBy("ck");
		po.setCreateDate(DateUtils.addDays(new Date(), -1));
		dao.save(po);
		po = new DailyPlanPo();
		po.setCreateBy("ck");
		po.setId("2");
		po.setCreateDate(DateUtils.addDays(new Date(), -2));
		dao.save(po);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlanByPage() {
		addDailyPlan();
		assertEquals(2, repo.getPlanByPage("ck", 0,5).getTotalElements());
	}
}
