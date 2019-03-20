package com.ck.orm.mapper.inf;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.orm.mapper.base.MapperDaoTest;

public class DailyPlanItemMapperTest extends MapperDaoTest<DailyPlanItemMapper, DailyPlanItemPo, DailyPlanItemDao>{
	
	public void initData() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setFinishType(1);
		po.setCreateDate(DateUtils.addDays(new Date(), -1));
		dao.saveAndFlush(po);
		po = new DailyPlanItemPo();
		po.setId("2");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setFinishType(1);
		po.setCreateDate(DateUtils.addDays(new Date(), -2));
		po.setItemContent("asdckfas");
		dao.saveAndFlush(po);
		po = new DailyPlanItemPo();
		po.setId("3");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setCreateDate(DateUtils.addDays(new Date(), -3));
		po.setFinishType(2);
		dao.saveAndFlush(po);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSelectPlanItemsByConds() {
		initData();
		Date now = new Date();
		assertEquals(1, mDao.selectPlanItemsByConds(DateUtils.addDays(now, -3), now, 
				"", 2, "ck").size());
		assertEquals(1, mDao.selectPlanItemsByConds(DateUtils.addDays(now, -1), now, 
				"", 1, "ck").size());
		assertEquals(1, mDao.selectPlanItemsByConds(DateUtils.addDays(now, -3), now, 
				"%sd%", 1, "ck").size());
	}
}
