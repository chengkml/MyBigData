package com.ck.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
			BeanUtils.copyProperties(model, po);
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
	
	/**
	 * 按日期区间分页查询计划项
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlanItem> getPlanItemByRange(String name, Date startDate, Date endDate, int page, int size){
		Page<DailyPlanItemPo> planPage = dao.getPlanItemByRange(name, startDate, DateUtils.addDays(endDate, 1), new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}
	
	/**
	 * 按页查询计划项
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlanItem> getPlanItemByPage(String name, int page, int size){
		Page<DailyPlanItemPo> planPage = dao.getPlanItemByPage(name, new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}

	public Page<DailyPlanItem> getPlanItemByRange(String name, Date start, Date end, int page, int size, Integer state,
			String key) {
		Page<DailyPlanItemPo> planPage = dao.getPlanItemByConds(name, start, DateUtils.addDays(end, 1), state,
				"%"+key+"%", new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}

	public Page<DailyPlanItem> getPlanItemByPage(String name, int page, int size, Integer state, String key) {
		Page<DailyPlanItemPo> planPage = dao.getPlanItemByConds(name, state, "%"+key+"%", 
				new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}
	
	public Page<DailyPlanItem> getPlanItemByRange(String name, Date start, Date end, int page, int size, String key) {
		Page<DailyPlanItemPo> planPage = dao.getPlanItemByConds(name, start, DateUtils.addDays(end, 1),
				"%"+key+"%", new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}

	public Page<DailyPlanItem> getPlanItemByPage(String name, int page, int size, String key) {
		Page<DailyPlanItemPo> planPage = dao.getPlanItemByConds(name, "%"+key+"%", new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}
	
}
