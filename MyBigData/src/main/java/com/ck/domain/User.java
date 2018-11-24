package com.ck.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.data.domain.Page;

import com.ck.domain.base.BaseModel;
import com.ck.enums.FinishTypeEnum;
import com.ck.repository.DailyPlanItemRepository;
import com.ck.repository.DailyPlanRepository;
import com.ck.util.BeanFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseModel{
	
	public static final int ACTIVE_PLANS = 7;
	
	private String name;
	
	private String type;
	
	private Integer age;
	
	private String job;
	
	private List<DailyPlan> plans;
	
	private List<KnowledgePoint> points;
	
	public DailyPlan getPlanByDate(Date date) {
		Page<DailyPlan> tempPlans = getPlansByRange(date, DateUtils.addDays(date, 1),0,5);
		if(null!=tempPlans && !tempPlans.getContent().isEmpty()) {
			return tempPlans.getContent().get(0);
		}
		return null;
	}
	
	public Page<DailyPlan> getPlansByRange(Date startDate, Date endDate, int page, int size) {
		return BeanFactory.getBean(DailyPlanRepository.class).getPlanByRange(name, startDate, endDate, page, size);
	}
	
	public Page<DailyPlan> getPlansByPage(int page, int size) {
		return BeanFactory.getBean(DailyPlanRepository.class).getPlanByPage(name, page, size);
	}
	
	public Page<DailyPlanItem> getPlanItemsByRange(Date startDate, Date endDate, int page, int size) {
		return BeanFactory.getBean(DailyPlanItemRepository.class).getPlanItemByRange(name, startDate, endDate, page, size);
	}
	
	public Page<DailyPlanItem> getPlanItemsByPage(int page, int size) {
		return BeanFactory.getBean(DailyPlanItemRepository.class).getPlanItemByPage(name, page, size);
	}
	
	public void addPlan(DailyPlan plan) {
		BeanFactory.getBean(DailyPlanRepository.class).saveModel(plan);
	}
	
	public void addPlans(List<DailyPlan> plans) {
		BeanFactory.getBean(DailyPlanRepository.class).saveModels(plans);
	}
	
	public void addPlanItem(DailyPlanItem item) {
		BeanFactory.getBean(DailyPlanItemRepository.class).saveModel(item);
	}
	
	public void addPlanItems(List<DailyPlanItem> items) {
		BeanFactory.getBean(DailyPlanItemRepository.class).saveModels(items);
	}
	
	public void deletePlan(DailyPlan plan) {
		BeanFactory.getBean(DailyPlanRepository.class).deleteModel(plan);
	}
	
	public void deletePlans(List<DailyPlan> plans) {
		BeanFactory.getBean(DailyPlanRepository.class).deleteModels(plans);
	}
	
	public void deletePlanItem(DailyPlanItem item) {
		BeanFactory.getBean(DailyPlanItemRepository.class).deleteModel(item);
	}
	
	public void deletePlanItems(List<DailyPlanItem> items) {
		BeanFactory.getBean(DailyPlanItemRepository.class).deleteModels(items);
	}
	
	public void updateState(String itemId, Integer state, String descr) {
		DailyPlanItemRepository repo = BeanFactory.getBean(DailyPlanItemRepository.class);
		DailyPlanItem item = repo.getById(itemId);
		item.setFinishType(state);
		item.setLastModifiedBy(name);
		item.setLastModifiedDate(new Date());
		if(FinishTypeEnum.FINISH.getValue()==state) {
			item.setFinishTime(new Date());
		}
		item.setFinishDescr(descr);
		repo.saveModel(item);
	}

	public Page<DailyPlanItem> getPlanItemsByConds(Date start, Date end, int page, int size, Integer state, String key) {
		return BeanFactory.getBean(DailyPlanItemRepository.class).getPlanItemByRange(name, start, end, page, size, state, key);
	}

	public Page<DailyPlanItem> getPlanItemsByConds(int page, int size, Integer state, String key) {
		return BeanFactory.getBean(DailyPlanItemRepository.class).getPlanItemByPage(name, page, size, state, key);
	}
	
	public Page<DailyPlanItem> getPlanItemsByConds(Date start, Date end, int page, int size, String key) {
		return BeanFactory.getBean(DailyPlanItemRepository.class).getPlanItemByRange(name, start, end, page, size, key);
	}

	public Page<DailyPlanItem> getPlanItemsByConds(int page, int size, String key) {
		return BeanFactory.getBean(DailyPlanItemRepository.class).getPlanItemByPage(name, page, size, key);
	}

	public DailyPlan getPlanByTitle(String title) {
		return BeanFactory.getBean(DailyPlanRepository.class).getPlanByTitle(name, title);
	}
}
