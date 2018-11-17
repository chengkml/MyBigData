package com.ck.orm.dao;

import static org.junit.Assert.assertNotNull;

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
		Pageable page = new PageRequest(1, 5);
		assertNotNull((dao.getPlanByRange("ck", DateUtils.addDays(new Date(), -1), new Date(), page)));
	}

	@Override
	public void testSave() {
		DailyPlanPo plan = new DailyPlanPo();
		plan.setId("1");
		plan.setCreateBy("ck");
		plan.setCreateDate(new Date());
		dao.saveAndFlush(plan);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}

}
