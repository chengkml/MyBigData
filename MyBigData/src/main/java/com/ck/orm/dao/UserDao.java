package com.ck.orm.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.UserPo;
@Repository
public interface UserDao extends BaseDao<UserPo> {
	@Query("from UserPo p where p.name = :name")
	public UserPo getUserByName(@Param("name") String name);
}
