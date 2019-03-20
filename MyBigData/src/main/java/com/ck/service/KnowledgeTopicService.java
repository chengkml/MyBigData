package com.ck.service;

import org.springframework.stereotype.Service;

import com.ck.domain.KnowledgeTopic;
import com.ck.orm.dao.KnowledgeTopicDao;
import com.ck.orm.entity.KnowledgeTopicPo;
import com.ck.repository.KnowledgeTopicRepository;
import com.ck.service.base.BaseService;
@Service
public class KnowledgeTopicService extends BaseService<KnowledgeTopicDao, KnowledgeTopicRepository, KnowledgeTopicPo, KnowledgeTopic> {

}
