package com.ck.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.base.BaseRepositoryTest;
import com.ck.util.MyDateUtils;
public class DailyPlanItemRepositoryTest extends BaseRepositoryTest<DailyPlanItemRepository, DailyPlanItemDao, DailyPlanItemPo, DailyPlanItem>{
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPlanId() {
		addPlanItem();
		assertEquals(2, repo.findByPlanId("1").size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteById() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setFinishType(1);
		po.setItemContent("12345");
		po.setCreateDate(new Date());
		dao.saveAndFlush(po);
		repo.deleteById("1");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSelectPlanItemsByConds() {
		addPlanItem();
		Date now = new Date();
		assertEquals(1, repo.selectPlanItemsByConds(MyDateUtils.formatDate(MyDateUtils.
				addDays(now, -1)), MyDateUtils.formatDate(now), 2, "", "ck", 0, 2).getTotal());
		assertEquals(2, repo.selectPlanItemsByConds(MyDateUtils.formatDate(MyDateUtils.
				addDays(now, -10)), MyDateUtils.formatDate(now), 2, "", "ck", 0, 2).getTotal());
		assertEquals(2, repo.selectPlanItemsByConds(MyDateUtils.formatDate(MyDateUtils.
				addDays(now, -10)), MyDateUtils.formatDate(now), 2, "", "ck", 0, 1).getPages());
		assertEquals(1, repo.selectPlanItemsByConds(MyDateUtils.formatDate(MyDateUtils.
				addDays(now, -1)), MyDateUtils.formatDate(now), 1, "", "ck", 0, 2).getTotal());
		assertEquals(0, repo.selectPlanItemsByConds(MyDateUtils.formatDate(MyDateUtils.
				addDays(now, -1)), MyDateUtils.formatDate(now), 2, "", "kk", 0, 2).getTotal());
		assertEquals(1, repo.selectPlanItemsByConds(MyDateUtils.formatDate(MyDateUtils.
				addDays(now, -10)), MyDateUtils.formatDate(now), 1, "123", "ck", 0, 2).getTotal());
	}

	private void addPlanItem() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setFinishType(1);
		po.setItemContent("12345");
		po.setCreateDate(new Date());
		dao.saveAndFlush(po);
		po = new DailyPlanItemPo();
		po.setId("2");
		po.setPlanId("1");
		po.setFinishType(2);
		po.setCreateBy("ck");
		po.setCreateDate(new Date());
		dao.saveAndFlush(po);
		po = new DailyPlanItemPo();
		po.setId("3");
		po.setPlanId("1");
		po.setFinishType(2);
		po.setCreateBy("ck");
		po.setCreateDate(MyDateUtils.addDays(new Date(),-5));
		dao.saveAndFlush(po);
	}
}
