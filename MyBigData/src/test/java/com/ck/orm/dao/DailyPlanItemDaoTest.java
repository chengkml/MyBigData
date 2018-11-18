package com.ck.orm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		assertNotNull(dao.findByPlanId("1"));
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testGetPlanItemByRange() {
		testSave();
		Pageable page = new PageRequest(0, 5);
		assertTrue(1==(dao.getPlanItemByRange("ck", DateUtils.addDays(new Date(), -2), new Date(), page)).getTotalElements());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testGetPlanByPage() {
		testSave();
		Pageable page = new PageRequest(0, 5);
		assertTrue((dao.getPlanItemByPage("ck", page)).getTotalElements()==1);
	}
	
}
