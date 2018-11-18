package com.ck.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ck.domain.base.BaseModel;
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
}
