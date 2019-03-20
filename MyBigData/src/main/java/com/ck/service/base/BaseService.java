package com.ck.service.base;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ck.orm.dao.base.BaseDao;
import com.ck.repository.base.BaseRepository;
/**
 * 抽象服务类
 * @author Chengk
 * @param <D>
 * @param <R>
 * @param <P>
 * @param <M>
 */
public abstract class BaseService<D extends BaseDao<P>,R extends BaseRepository<D,P,M>,P,M> {
	
	protected static final Log log = LogFactory.getLog(BaseService.class);
	
	@Autowired
	protected BaseRepository<D,P,M> pRepo;
	
	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	protected R repo;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convert() {
		dao = (D)pDao;
		repo = (R)pRepo;
	}
	
}
