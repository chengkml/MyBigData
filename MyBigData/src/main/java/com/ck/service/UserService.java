package com.ck.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ck.domain.DailyPlan;
import com.ck.domain.DailyPlanItem;
import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.base.BaseService;
import com.ck.vo.DailyPlanItemVo;
import com.ck.vo.DailyPlanVo;
import com.ck.vo.UserVo;

@Service
public class UserService extends BaseService<UserDao, UserRepository, UserPo, User, UserVo>{

	@Autowired
	private DailyPlanService planServ;
	
	@Autowired
	private DailyPlanItemService planItemServ;
	
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
	
	public Page<DailyPlanVo> getPlansByRange(String userId, Date start, Date end, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlan> plansPage = user.getPlansByRange(start, end, page, size);
		return new PageImpl<>(planServ.modelsToVos(plansPage.getContent()),new PageRequest(page, size),plansPage.getTotalElements());
	}
	
	public Page<DailyPlanVo> getPlansByPage(String userId, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlan> planItemsPage = user.getPlansByPage(page, size);
		return new PageImpl<>(planServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}
	
	public Page<DailyPlanItemVo> getPlanItemsByRange(String userId, Date start, Date end, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByRange(start, end, page, size);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}
	
	public Page<DailyPlanItemVo> getPlanItemsByPage(String userId, int page, int size) {
		User user = repo.getById(userId);
		Page<DailyPlanItem> planItemsPage = user.getPlanItemsByPage(page, size);
		return new PageImpl<>(planItemServ.modelsToVos(planItemsPage.getContent()),new PageRequest(page, size),planItemsPage.getTotalElements());
	}
	

}
