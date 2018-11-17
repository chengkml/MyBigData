package com.ck.orm.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.SequencePo;

public class SequenceDaoTest extends BaseDaoTest<SequencePo, SequenceDao>{

	@Override
	public void testSave() {
		SequencePo po = new SequencePo();
		po.setId("1");
		po.setCount(1);
		po.setType("plan");
		dao.saveAndFlush(po);
		po = new SequencePo();
		po.setId("2");
		po.setCount(2);
		po.setType("plan");
		dao.saveAndFlush(po);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetLatestSeqByType() {
		testSave();
		assertTrue(dao.getLatestSeqByType("plan").getCount()==2);
	}

}
