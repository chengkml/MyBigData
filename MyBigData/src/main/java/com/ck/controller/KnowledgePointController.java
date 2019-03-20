package com.ck.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.controller.base.BaseController;
import com.ck.domain.KnowledgePoint;
import com.ck.orm.dao.KnowledgePointDao;
import com.ck.orm.entity.KnowledgePointPo;
import com.ck.repository.KnowledgePointRepository;
import com.ck.service.KnowledgePointService;

@RestController
@RequestMapping(value="/knowledge/")
public class KnowledgePointController extends BaseController<KnowledgePointPo, KnowledgePoint, KnowledgePointDao, KnowledgePointRepository, KnowledgePointService>{

}
