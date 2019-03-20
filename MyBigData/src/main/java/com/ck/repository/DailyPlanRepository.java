package com.ck.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.DailyPlan;
import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanDao;
import com.ck.orm.entity.DailyPlanPo;
import com.ck.repository.base.BaseRepository;
/**
 * @Title:计划Repo
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Repository
public class DailyPlanRepository extends BaseRepository<DailyPlanDao, DailyPlanPo, DailyPlan>{

	@Autowired
	private DailyPlanItemRepository planItemRepo;
	
	@Override
	public DailyPlanPo modelToPo(DailyPlan model) {
		DailyPlanPo po = new DailyPlanPo();
		if(null!=model) {
			BeanUtils.copyProperties(model, po);
			return po;
		}
		return null;
	}
	
	@Override
	public DailyPlan poToModel(DailyPlanPo po) {
		DailyPlan m = new DailyPlan();
		if(null!=po) {
			BeanUtils.copyProperties(po, m);
			m.setPlanItems(planItemRepo.findByPlanId(m.getId()));
			return m;
		}
		return null;
	}
	
	@Override
	@Transactional
	public DailyPlan saveModel(DailyPlan m) {
		super.saveModel(m);
		List<DailyPlanItem> items = m.getPlanItems();
		planItemRepo.saveModels(items);
		return m;
	}

	@Override
	@Transactional
	public void saveModels(List<DailyPlan> ms) {
		super.saveModels(ms);
		List<DailyPlanItem> items = new ArrayList<>();
		for(DailyPlan plan : ms) {
			items.addAll(plan.getPlanItems());
		}
		planItemRepo.saveModels(items);
	}
	
	/**
	 * 根据时间区间按页查询计划
	 * @param start
	 * @param end
	 * @return
	 */
	public Page<DailyPlan> getPlanByRange(String name, Date startDate, Date endDate, int page, int size){
		Page<DailyPlanPo> planPage = dao.getPlanByRange(name, startDate, DateUtils.addDays(endDate, 1), new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}
	
	/**
	 * 按页查询计划
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlan> getPlanByPage(String name, int page, int size){
		Page<DailyPlanPo> planPage = dao.getPlanByPage(name, new PageRequest(page, size));
		return new PageImpl<>(posToModels(planPage.getContent()),new PageRequest(page, size),planPage.getTotalElements());
	}
	
	public DailyPlan getPlanByTitle(String name, String title) {
		return poToModel(dao.getPlanByTitle(name, title));
	}
}
