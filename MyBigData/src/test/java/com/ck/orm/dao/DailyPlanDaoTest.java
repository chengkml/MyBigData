package com.ck.orm.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.DailyPlanPo;

public class DailyPlanDaoTest extends BaseDaoTest<DailyPlanPo, DailyPlanDao>{
	
	@Test
	@Rollback(true)
	@Transactional
	public void testGetPlanByRange() {
		testSave();
		Pageable page = new PageRequest(0, 5);
		assertTrue(1==(dao.getPlanByRange("ck", DateUtils.addDays(new Date(), -2), new Date(), page)).getTotalElements());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testGetPlanByPage() {
		testSave();
		Pageable page = new PageRequest(0, 5);
		assertTrue(1==(dao.getPlanByPage("ck", page)).getTotalElements());
	}

	@Override
	public void testSave() {
		DailyPlanPo plan = new DailyPlanPo();
		plan.setId("1");
		plan.setCreateBy("ck");
		plan.setCreateDate(DateUtils.addDays(new Date(), -1));
		dao.saveAndFlush(plan);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}

}
