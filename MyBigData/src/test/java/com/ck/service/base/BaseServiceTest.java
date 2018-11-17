package com.ck.service.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ck.base.SpringJunitTestBase;
import com.ck.orm.dao.base.BaseDao;
import com.ck.repository.base.BaseRepository;

public abstract class BaseServiceTest<S extends BaseService<D,R,P,M,V>,R extends BaseRepository<D,P,M>,D extends BaseDao<P>,P,M,V> extends SpringJunitTestBase{
	
	@Autowired
	protected BaseService<D,R,P,M,V> pServ;
	
	protected S serv;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDaoAndRepo() {
		serv = (S)pServ;
	}
}
