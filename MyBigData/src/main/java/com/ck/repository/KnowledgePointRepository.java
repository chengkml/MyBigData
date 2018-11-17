package com.ck.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ck.domain.KnowledgePoint;
import com.ck.orm.dao.KnowledgePointDao;
import com.ck.orm.entity.KnowledgePointPo;
import com.ck.repository.base.BaseRepository;

@Repository
public class KnowledgePointRepository extends BaseRepository<KnowledgePointDao, KnowledgePointPo, KnowledgePoint>{

	@Override
	public KnowledgePoint poToModel(KnowledgePointPo po) {
		KnowledgePoint m = new KnowledgePoint();
		BeanUtils.copyProperties(po, m);
		return m;
	}

	@Override
	public KnowledgePointPo modelToPo(KnowledgePoint model) {
		KnowledgePointPo po = new KnowledgePointPo();
		BeanUtils.copyProperties(po, model);
		return po;
	}
}
