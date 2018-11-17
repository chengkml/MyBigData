package com.ck.domain;

import java.util.Date;
import java.util.List;

import com.ck.domain.base.BaseModel;
import com.ck.repository.DailyPlanRepository;
import com.ck.service.DailyPlanService;
import com.ck.util.BeanFactory;
import com.ck.vo.DailyPlanVo;

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
	
	public List<DailyPlanVo> getPlansByRange(Date startDate, Date endDate, int page, int size) {
		return BeanFactory.getBean(DailyPlanService.class).modelsToVos(BeanFactory.getBean(
				DailyPlanRepository.class).getPlanByRange(name, startDate, endDate, page, size));
	}
	
	public List<DailyPlanVo> getPlansByPage(int page, int size) {
		return BeanFactory.getBean(DailyPlanService.class).modelsToVos(BeanFactory.getBean(
				DailyPlanRepository.class).getPlanByPage(name, page, size));
	}
}
