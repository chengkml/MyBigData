package com.ck.orm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.DailyPlanItemPo;
@Repository
public interface DailyPlanItemDao extends BaseDao<DailyPlanItemPo>{
	
	@Query("from DailyPlanItemPo p where p.planId=:planId")
	public List<DailyPlanItemPo> findByPlanId(@Param("planId") String planId);
}
