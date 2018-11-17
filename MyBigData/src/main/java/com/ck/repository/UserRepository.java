package com.ck.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.base.BaseRepository;

/**
 * @Title:用户Repo
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Repository
public class UserRepository extends BaseRepository<UserDao, UserPo, User>{

	@Override
	public User poToModel(UserPo po) {
		User m = new User();
		if(null!=po) {
			BeanUtils.copyProperties(po, m);
			return m;
		}
		return null;
	}

	@Override
	public UserPo modelToPo(User model) {
		UserPo po = new UserPo();
		if(null!=model) {
			BeanUtils.copyProperties(model, po);
			return po;
		}
		return null;
	}
	
	public User getUserByName(String name) {
		return poToModel(dao.getUserByName(name));
	}

}
