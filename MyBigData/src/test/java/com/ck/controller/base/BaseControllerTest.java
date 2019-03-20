package com.ck.controller.base;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ck.base.SpringJunitTestBase;
import com.ck.orm.dao.base.BaseDao;
import com.ck.repository.base.BaseRepository;
import com.ck.service.base.BaseService;

public class BaseControllerTest<P,M,D extends BaseDao<P>,R extends BaseRepository<D,P,M>,S extends BaseService<D,R,P,M>,C extends BaseController<P,M,D,R,S>> extends SpringJunitTestBase{
	
	protected MockMvc mock;
	
	@Autowired
	protected BaseController<P,M,D,R,S> pController;
	
	protected C controller;
	
	@Autowired
	protected BaseService<D,R,P,M> pServ;
	
	protected S serv;
	
	@Autowired
	protected BaseRepository<D,P,M> pRepo;
	
	protected R repo;

	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	@Before
    public void setup(){
        mock = MockMvcBuilders.standaloneSetup(controller).build();
    }
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convert() {
		dao = (D)pDao;
		repo = (R)pRepo;
		serv = (S)pServ;
		controller = (C)pController;
	}
}
