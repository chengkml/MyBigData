package com.ck.orm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.SubjectPo;
@Repository
public interface SubjectDao extends BaseDao<SubjectPo>{
	
	@Query("from SubjectPo p where p.type=:type order by p.order")
	public List<SubjectPo> getSubjectByType(@Param("type") String type);
}
