package com.ck.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.DailyPlan;
import com.ck.domain.DailyPlanItem;
import com.ck.domain.User;
import com.ck.enums.FinishTypeEnum;
import com.ck.exception.CommonException;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.base.BaseService;
import com.ck.util.MyDateUtils;
import com.ck.vo.DailyPlanItemVo;
import com.ck.vo.DailyPlanVo;
import com.ck.vo.UserVo;

@Service
public class UserService extends BaseService<UserDao, UserRepository, UserPo, User, UserVo>{

	@Autowired
	private DailyPlanService planServ;
	
	@Autowired
	private DailyPlanItemService planItemServ;
	
	@Autowired
	private SequenceService seqServ;
	
	@Override
	public UserVo modelToVo(User user) {
		UserVo vo = new UserVo();
		if(null!=user) {
			BeanUtils.copyProperties(user, vo);
			return vo;
		}
		return null;
	}

	@Override
	public User voToModel(UserVo vo) {
		User user = new User();
		if(null!=vo) {
			BeanUtils.copyProperties(vo, user);
			return user;
		}
		return null;
	}
	
	public UserVo getUserById(String id) {
		return modelToVo(repo.getById(id));
	}
	
	public UserVo getUserByName(String name) {
		return modelToVo(repo.getUserByName(name));
	}
	
	/**
	 * 按时间分页查询计划
	 * @param userId
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlanVo> getPlansByRange(String userId, Date start, Date end, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlan> plansPage = user.getPlansByRange(start, end, page, size);
		return new PageImpl<>(planServ.modelsToVos(plansPage.getContent()),new PageRequest(page, size),plansPage.getTotalElements());
	}
	
	/**
	 * 按页查询计划
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlanVo> getPlansByPage(String userId, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlan> planItemsPage = user.getPlansByPage(page, size);
		return new PageImpl<>(planServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}
	
	/**
	 * 按时间分页查询计划项
	 * @param userId
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlanItemVo> getPlanItemsByRange(String userId, Date start, Date end, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByRange(start, end, page, size);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}
	
	/**
	 * 按页查询计划项
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<DailyPlanItemVo> getPlanItemsByPage(String userId, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByPage(page, size);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}
	
	/**
	 * 添加日计划
	 * @param userId
	 * @param vo
	 */
	public void addPlan(String userId, DailyPlanVo vo ) {
		User user = repo.getById(userId);
		user.addPlan(planServ.voToModel(vo));
	}
	
	/**
	 * 批量添加日计划
	 * @param userId
	 * @param vos
	 */
	public void addPlans(String userId, List<DailyPlanVo> vos) {
		User user = repo.getById(userId);
		user.addPlans(planServ.vosToModels(vos));
	}
	
	/**
	 * 添加计划项
	 * @param userId
	 * @param vo
	 */
	public void addPlanItem(String userId, DailyPlanItemVo vo) {
		User user = repo.getById(userId);
		if(StringUtils.isBlank(vo.getId())) {
			vo.setId(seqServ.getPlanItemSeq(userId));
		}
		if(StringUtils.isBlank(vo.getPlanId())) {
			String title = MyDateUtils.formatDate(new Date());
			DailyPlan temp = user.getPlanByTitle(title);
			if(null==temp) {
				DailyPlanVo plan = new DailyPlanVo();
				String planId = seqServ.getPlanSeq(userId);
				vo.setPlanId(planId);
				plan.setId(planId);
				plan.setCreateDate(new Date());
				plan.setCreateBy(userId);
				plan.setLastModifiedDate(new Date());
				plan.setLastModifiedBy(userId);
				plan.setTitle(MyDateUtils.formatDate(new Date()));
				user.addPlan(planServ.voToModel(plan));
			}else {
				vo.setPlanId(temp.getId());
			}
		}
		vo.setFinishType(FinishTypeEnum.INITIAL.getValue());
		vo.setCreateDate(new Date());
		vo.setCreateBy(userId);
		vo.setLastModifiedDate(new Date());
		vo.setLastModifiedBy(userId);
		user.addPlanItem(planItemServ.voToModel(vo));
	}
	
	/**
	 * 批量添加计划项
	 * @param userId
	 * @param vos
	 */
	public void addPlanItems(String userId, List<DailyPlanItemVo> vos) {
		User user = repo.getById(userId);
		user.addPlanItems(planItemServ.vosToModels(vos));
	}
	
	@Transactional
	public void copyUnfinishedPlans(String userId, Date srcDate, Date targetDate) throws CommonException{
		User user = repo.getById(userId);
		DailyPlan srcPlan = user.getPlanByDate(srcDate);
		if(null==srcPlan) {
			throw new CommonException("未找到日期"+srcDate.toString()+"对应的计划！");
		}
		DailyPlan targetPlan = user.getPlanByDate(targetDate);
		List<DailyPlanItem> unFini = new ArrayList<>();
		List<DailyPlanItem> tempItems = srcPlan.getPlanItems();
		if(null == targetPlan) {
			for(DailyPlanItem item : tempItems) {
				if(!item.checkFinished()) {
					//TODO 修改计划项信息
					unFini.add(item);
				}
			}
			targetPlan = new DailyPlan();
			//TODO 补全信息
			targetPlan.setPlanItems(unFini);
			user.addPlan(targetPlan);
		}else {
			for(DailyPlanItem item : tempItems) {
				if(!item.checkFinished()) {
					//TODO 修改计划项信息
					unFini.add(item);
				}
			}
			user.addPlanItems(unFini);
		}
	}

	public Object getPlanItemsByConds(String userId, Date start, Date end, int page, int size, Integer state, String key) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByConds(start, end, page, size,state,key);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}

	public Object getPlanItemsByConds(String userId, int page, int size, Integer state, String key) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByConds(page, size,state,key);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements()); 
	}
	
	public Object getPlanItemsByConds(String userId, Date start, Date end, int page, int size, String key) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByConds(start, end, page, size, key);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}

	public Object getPlanItemsByConds(String userId, int page, int size, String key) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByConds(page, size, key);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements()); 
	}

	public Object getPlanByTitle(String userId, String title) {
		User user = repo.getById(userId);
		return planServ.modelToVo(user.getPlanByTitle(title));
	}
	
	public void updateState(String userId, String itemId, Integer state, String descr) {
		repo.getById(userId).updateState(itemId, state, descr);
	}
}
 