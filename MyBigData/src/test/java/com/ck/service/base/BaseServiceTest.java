package com.ck.service.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ck.base.SpringJunitTestBase;
import com.ck.orm.dao.base.BaseDao;
import com.ck.repository.base.BaseRepository;

public abstract class BaseServiceTest<S extends BaseService<D,R,P,M>,R extends BaseRepository<D,P,M>,D extends BaseDao<P>,P,M> extends SpringJunitTestBase{
	
	@Autowired
	protected BaseService<D,R,P,M> pServ;
	
	protected S serv;
	
	@Autowired
	private BaseRepository<D,P,M> pRepo;
	
	protected R repo;
	
	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDaoAndRepo() {
		serv = (S)pServ;
		repo = (R)pRepo;
		dao = (D)pDao;
	}
}
