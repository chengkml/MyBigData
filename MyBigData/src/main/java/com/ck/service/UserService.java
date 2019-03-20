package com.ck.service;

import org.springframework.stereotype.Service;

import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.base.BaseService;

@Service
public class UserService extends BaseService<UserDao, UserRepository, UserPo, User> {

}
 