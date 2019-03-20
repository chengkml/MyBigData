package com.ck.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.Sequence;
import com.ck.orm.dao.SequenceDao;
import com.ck.orm.entity.SequencePo;
import com.ck.repository.SequenceRepository;
import com.ck.service.base.BaseServiceTest;

public class SequenceServiceTest extends BaseServiceTest<SequenceService, SequenceRepository, SequenceDao, SequencePo, Sequence>{
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetSeq() {
		assertEquals("ck_test_1", serv.getSeq("ck", "test"));
	}
}
