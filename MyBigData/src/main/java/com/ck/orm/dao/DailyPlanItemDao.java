package com.ck.orm.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.DailyPlanItemPo;
@Repository
public interface DailyPlanItemDao extends BaseDao<DailyPlanItemPo>{
	
	@Query("from DailyPlanItemPo p where p.planId=:planId")
	public List<DailyPlanItemPo> findByPlanId(@Param("planId") String planId);
	
	@Query("from DailyPlanItemPo p where p.createBy=:name and p.createDate>:startDate and p.createDate<=:endDate")
	public Page<DailyPlanItemPo> getPlanItemByRange(@Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate,Pageable page);

	@Query("from DailyPlanItemPo p where p.createBy=:name order by p.createDate")
	public Page<DailyPlanItemPo> getPlanItemByPage(@Param("name") String name, Pageable page);
}
