package com.ck.orm.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.DailyPlanPo;
@Repository
public interface DailyPlanDao extends BaseDao<DailyPlanPo>{
	
	@Query("from DailyPlanPo p where p.createBy=:name and p.createDate>:startDate and p.createDate<=:endDate")
	public Page<DailyPlanPo> getPlanByRange(@Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate,Pageable page);

	@Query("from DailyPlanPo p where p.createBy=:name order by p.createDate")
	public Page<DailyPlanPo> getPlanByPage(@Param("name") String name, Pageable page);
}
