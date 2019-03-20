package com.ck.orm.mapper.inf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.orm.entity.DailyPlanItemPo;

public interface DailyPlanItemMapper {
	
	/**
	 * 按条件查询计划项
	 * @param beginTime
	 * @param endTime
	 * @param keyWord
	 * @param state
	 * @param createBy
	 * @return
	 */
	List<DailyPlanItemPo> selectPlanItemsByConds(
			@Param(value = "beginTime") Date beginTime, 
			@Param(value = "endTime") Date endTime, 
			@Param(value = "keyWord") String keyWord,
			@Param(value = "state") Integer state,
			@Param(value = "createBy") String createBy);
}
