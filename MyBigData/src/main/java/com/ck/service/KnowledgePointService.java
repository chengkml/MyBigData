package com.ck.service;

import org.springframework.stereotype.Service;

import com.ck.domain.KnowledgePoint;
import com.ck.orm.dao.KnowledgePointDao;
import com.ck.orm.entity.KnowledgePointPo;
import com.ck.repository.KnowledgePointRepository;
import com.ck.service.base.BaseService;
@Service
public class KnowledgePointService extends BaseService<KnowledgePointDao, KnowledgePointRepository, KnowledgePointPo, KnowledgePoint> {

}
