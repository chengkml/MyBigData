package com.ck.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ck.domain.KnowledgeTopic;
import com.ck.orm.dao.KnowledgeTopicDao;
import com.ck.orm.entity.KnowledgeTopicPo;
import com.ck.repository.base.BaseRepository;

@Repository
public class KnowledgeTopicRepository extends BaseRepository<KnowledgeTopicDao, KnowledgeTopicPo, KnowledgeTopic>{

	@Override
	public KnowledgeTopic poToModel(KnowledgeTopicPo po) {
		KnowledgeTopic m = new KnowledgeTopic();
		BeanUtils.copyProperties(po, m);
		return m;
	}

	@Override
	public KnowledgeTopicPo modelToPo(KnowledgeTopic model) {
		KnowledgeTopicPo po = new KnowledgeTopicPo();
		BeanUtils.copyProperties(po, model);
		return po;
	}
}
