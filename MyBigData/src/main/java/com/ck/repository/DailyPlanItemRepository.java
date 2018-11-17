package com.ck.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.base.BaseRepository;
/**
 * @Title:计划项Repo
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Repository
public class DailyPlanItemRepository extends BaseRepository<DailyPlanItemDao, DailyPlanItemPo, DailyPlanItem>{
	
	@Override
	public DailyPlanItem poToModel(DailyPlanItemPo po) {
		DailyPlanItem m = new DailyPlanItem();
		if(null!=po) {
			BeanUtils.copyProperties(po, m);
			return m;
		}
		return null;
	}
	
	@Override
	public DailyPlanItemPo modelToPo(DailyPlanItem model) {
		DailyPlanItemPo po = new DailyPlanItemPo();
		if(null!=model) {
			BeanUtils.copyProperties(po, model);
			return po;
		}
		return null;
	}
	
	/**
	 * 根据planId查询所有计划项
	 * @param planId
	 * @return
	 */
	public List<DailyPlanItem> findByPlanId(String planId) {
		return posToModels(dao.findByPlanId(planId));
	}
}
