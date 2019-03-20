package com.ck.repository.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ck.base.SpringJunitTestBase;
import com.ck.orm.dao.base.BaseDao;

public abstract class BaseRepositoryTest<R extends BaseRepository<D,P,M>, D extends BaseDao<P>, P, M> extends SpringJunitTestBase{
	
	@Autowired
	public BaseRepository<D,P,M> pRepo;
	
	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	protected R repo;
	
	@PostConstruct
	@SuppressWarnings("unchecked")
	private void convert() {
		repo = (R)pRepo;
		dao = (D)pDao;
	}
	
}
