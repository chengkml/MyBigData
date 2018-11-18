package com.ck.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
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
	public void testFindByPlanId() {
		addPlanItem();
		assertEquals(repo.findByPlanId("1").size(), 2);
	}

	private void addPlanItem() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setCreateDate(new Date());
		dao.save(po);
		po = new DailyPlanItemPo();
		po.setId("2");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setCreateDate(new Date());
		dao.save(po);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlanItemByRange() {
		addPlanItem();
		Date start = DateUtils.addDays(new Date(), -2);
		Date end = new Date();
		assertEquals(repo.getPlanItemByRange("ck", start, end, 0, 5).getTotalElements(), 2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlanItemByPage() {
		addPlanItem();
		assertEquals(repo.getPlanItemByPage("ck", 0, 5).getTotalElements(), 2);
	}
}
