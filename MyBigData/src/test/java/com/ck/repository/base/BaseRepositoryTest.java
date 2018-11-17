package com.ck.repository.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ck.base.SpringJunitTestBase;
import com.ck.orm.dao.base.BaseDao;

public abstract class BaseRepositoryTest<R extends BaseRepository<D,P,M>, D extends BaseDao<P>, P, M> extends SpringJunitTestBase{
	
	@Autowired
	public BaseRepository<D,P,M> pRepo;
	
	protected R repo;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDao() {
		repo = (R)pRepo;
	}
	
}
