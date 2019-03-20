package com.ck.controller.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ck.orm.dao.base.BaseDao;
import com.ck.repository.base.BaseRepository;
import com.ck.service.base.BaseService;
/**
 * 抽象控制器类
 * @author Chengk
 * @param <P>
 * @param <M>
 * @param <D>
 * @param <R>
 * @param <S>
 */
public abstract class BaseController<P, M, D extends BaseDao<P>, R extends BaseRepository<D,P,M>, S extends BaseService<D,R,P,M>> {
	
	@Autowired
	protected BaseService<D,R,P,M> pServ;
	
	protected S serv;
	
	@Autowired
	protected BaseRepository<D,P,M> pRepo;
	
	protected R repo;
	
	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convert() {
		dao = (D)pDao;
		repo = (R)pRepo;
		serv = (S)pServ;
	}
	
}
