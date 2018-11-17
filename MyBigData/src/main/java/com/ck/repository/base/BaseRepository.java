package com.ck.repository.base;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
/**
 * @Title:Repo抽象父类
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Repository
public abstract class BaseRepository<D extends BaseDao<P>, P, M> {
	
	@Autowired
	public BaseDao<P> pDao;
	
	protected D dao; 
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDao() {
		dao = (D)pDao;
	}
	
	public abstract M poToModel(P po);
	
	public abstract P modelToPo(M model);
	
	public List<M> posToModels(List<P> pos) {
		List<M> models = new ArrayList<>();
		if(null!=pos && !pos.isEmpty()) {
			for(P po : pos) {
				models.add(poToModel(po));
			}
		}
		return models;
	}
	
	/**
	 * 获取所有模型
	 * @return
	 */
	public List<M> getAll(){
		List<P> all = dao.findAll();
		List<M> res = new ArrayList<>();
		if(!all.isEmpty()) {
			for(P po : all) {
				res.add(poToModel(po));
			}
		}
		return res;
	}
	
	/**
	 * 根据Id获取模型
	 * @param id
	 * @return
	 */
	public M getById(String id) {
		P po = dao.findOne(id);
		if(null!=po) {
			return poToModel(po);
		}
		return null;
	}
	
	/**
	 * 保存模型
	 * @param m
	 */
	public void saveModel(M m) {
		if(m!=null) {
			dao.saveAndFlush(modelToPo(m));
		}
	}
	
}
