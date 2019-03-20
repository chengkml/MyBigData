package com.ck.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.controller.base.BaseController;
import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.DailyPlanItemRepository;
import com.ck.service.DailyPlanItemService;
import com.google.gson.Gson;
/**
 * 按条件搜索计划项
 * @author Chengk
 */
@RestController
@RequestMapping(value="/planItem/")
public class DailyPlanItemController extends BaseController<DailyPlanItemPo, DailyPlanItem, DailyPlanItemDao, DailyPlanItemRepository, DailyPlanItemService>{

	/**
	 * 按条件搜索计划项
	 * @param startDate
	 * @param endDate
	 * @param state
	 * @param keyWord
	 * @param createBy
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value="selectByConds")
	public Object selectByConds(
			@RequestParam(name="startDate",required=false) String startDate, 
			@RequestParam(name="endDate",required=false) String endDate, 
			@RequestParam(name="state",required=false) Integer state, 
			@RequestParam(name="keyWord",required=false) String keyWord, 
			@RequestParam(name="createBy",required=false) String createBy, 
			@RequestParam(name="pageNum") int pageNum, 
			@RequestParam(name="pageSize") int pageSize) {
		return repo.selectPlanItemsByConds(startDate, endDate, state, keyWord, createBy, 
				pageNum, pageSize);
	}
	
	/**
	 * 添加计划项
	 * @param body
	 * @return
	 */
	@PostMapping(value="add")
	public Object add(@RequestBody String body) {
		Gson gson =new Gson();
		return repo.saveModel(gson.fromJson(body, DailyPlanItem.class));
	}
	
	@GetMapping(value="test")
	public Object test() {
		return "test";
	}
	
	/**
	 * 删除计划项
	 * @param id
	 * @return
	 */
	@PostMapping(value="delete/{id:.+}")
	public Object delete(@PathVariable String id) {
		return repo.deleteById(id);
	}
	
	/**
	 * 查询指定计划项
	 * @param id
	 * @return
	 */
	@GetMapping(value="get/{id:.+}")
	public Object get(@PathVariable String id) {
		return repo.getById(id);
	}
}
