package com.ck.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.base.BaseService;
import com.ck.vo.UserVo;

@Service
public class UserService extends BaseService<UserDao, UserRepository, UserPo, User, UserVo>{
	
	public UserVo getUserById(String id) {
		return modelToVo(repo.getById(id));
	}
	
	public UserVo getUserByName(String name) {
		return modelToVo(repo.getUserByName(name));
	}

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
}
