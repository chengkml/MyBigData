package com.ck.orm.mapper.inf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.orm.entity.KnowledgePointPo;
public interface KnowledgePointMapper {
	
	List<KnowledgePointPo> findKnowledgePointByConds(
			@Param(value = "beginTime") Date beginTime, 
			@Param(value = "endTime") Date endTime, 
			@Param(value = "keyWord") String keyWord,
			@Param(value = "name") String name);
}
