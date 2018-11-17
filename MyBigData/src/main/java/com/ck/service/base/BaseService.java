package com.ck.service.base;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ck.orm.dao.base.BaseDao;
import com.ck.repository.base.BaseRepository;

public abstract class BaseService<D extends BaseDao<P>,R extends BaseRepository<D,P,M>,P,M,V> {
	
	protected static final Log log = LogFactory.getLog(BaseService.class);
	
	@Autowired
	protected BaseRepository<D,P,M> pRepo;
	
	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	protected R repo;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDaoAndRepo() {
		dao = (D)pDao;
		repo = (R)pRepo;
	}
	
	public abstract V modelToVo(M model);
	
	public abstract M voToModel(V vo);
	
	public List<M> vosToModels(List<V> vos){
		List<M> res = new ArrayList<>();
		if(null!=vos && !vos.isEmpty()) {
			for(V vo : vos) {
				res.add(voToModel(vo));
			}
		}
		return res;
	}
	
	public List<V> modelsToVos(List<M> models){
		List<V> res = new ArrayList<>();
		if(null!=models && !models.isEmpty()) {
			for(M model : models) {
				res.add(modelToVo(model));
			}
		}
		return res;
	}
}
