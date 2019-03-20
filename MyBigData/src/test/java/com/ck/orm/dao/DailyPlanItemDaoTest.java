package com.ck.orm.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.DailyPlanItemPo;
public class DailyPlanItemDaoTest extends BaseDaoTest<DailyPlanItemPo, DailyPlanItemDao>{

	@Override
	public void testSave() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setCreateDate(DateUtils.addDays(new Date(), -1));
		dao.save(po);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPlanId() {
		testSave();
		assertEquals(1,dao.findByPlanId("1").size());
	}
	
}
