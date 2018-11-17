package com.ck.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.Sequence;
import com.ck.orm.dao.SequenceDao;
import com.ck.orm.entity.SequencePo;
import com.ck.repository.base.BaseRepositoryTest;

public class SequenceRepositoryTest extends BaseRepositoryTest<SequenceRepository, SequenceDao, SequencePo, Sequence>{
	@Autowired
	private SequenceDao dao;
	
	@Test
	@Transactional
	@Rollback
	public void testGetLastestSeq() {
		SequencePo po = new SequencePo();
		po.setId("1");
		po.setType("plan");
		po.setCount(1);
		dao.save(po);
		po.setId("2");
		po.setType("plan");
		po.setCount(2);
		dao.save(po);
		assertTrue(repo.getLastestSeq("plan").getCount() == 2);
	}
}
