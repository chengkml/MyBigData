package com.ck.orm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.DailyPlanItemPo;
public interface DailyPlanItemDao extends BaseDao<DailyPlanItemPo>{
	
	/**
	 * 根据日计划获取计划项
	 * @param planId
	 * @return
	 */
	@Query("from DailyPlanItemPo p where p.planId=:planId order by p.createDate desc")
	public List<DailyPlanItemPo> findByPlanId(@Param("planId") String planId);
	
}
