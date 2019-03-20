package com.ck.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.orm.mapper.inf.DailyPlanItemMapper;
import com.ck.repository.base.BaseRepository;
import com.ck.util.MyDateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Title:计划项Repo
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Repository
public class DailyPlanItemRepository extends BaseRepository<DailyPlanItemDao, DailyPlanItemPo, DailyPlanItem>{
	
	@Autowired
	private SqlSessionFactory factory;
	
	private SqlSession session;
	
	private DailyPlanItemMapper mDao;
	
	@PostConstruct
	private void openSession() {
		session = factory.openSession();
		mDao = session.getMapper(DailyPlanItemMapper.class);
	}
	
	@PreDestroy
	private void closeSession() {
		if(null!=session) {
			session.close();
		}
	}
	
	@Override
	public DailyPlanItem poToModel(DailyPlanItemPo po) {
		DailyPlanItem m = new DailyPlanItem();
		if(null!=po) {
			BeanUtils.copyProperties(po, m);
			return m;
		}
		return null;
	}
	
	@Override
	public DailyPlanItemPo modelToPo(DailyPlanItem model) {
		DailyPlanItemPo po = new DailyPlanItemPo();
		if(null!=model) {
			BeanUtils.copyProperties(model, po);
			return po;
		}
		return null;
	}
	
	/**
	 * 根据planId查询所有计划项
	 * @param planId
	 * @return
	 */
	public List<DailyPlanItem> findByPlanId(String planId) {
		return posToModels(dao.findByPlanId(planId));
	}

	/**
	 * 按条件搜索计划项
	 * @param name
	 * @param start
	 * @param end
	 * @param pageNum
	 * @param size
	 * @param state
	 * @param key
	 * @return
	 */
	public PageInfo<DailyPlanItem> selectPlanItemsByConds(String start, String end, 
			Integer state, String key, String createBy, int pageNum, int size) {
		PageHelper.startPage(pageNum,size);
		if(StringUtils.isNotBlank(key)) {
			key = "%"+key+"%";
		}
		List<DailyPlanItemPo> list = new ArrayList<>();
		try {
			list = mDao.selectPlanItemsByConds(MyDateUtils.parseDate(start), 
					MyDateUtils.parseDate(end), key, state, 
					createBy);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new PageInfo<>(posToModels(list));
	}
	
}
