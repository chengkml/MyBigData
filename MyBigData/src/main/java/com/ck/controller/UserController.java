package com.ck.controller;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.controller.base.BaseController;
import com.ck.service.UserService;
import com.ck.util.MyDateUtils;
import com.ck.vo.DailyPlanItemVo;
import com.ck.vo.ResultVo;
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService serv;

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public @ResponseBody ResultVo searchPlans(
			@RequestParam(name="startDate",defaultValue="",required=false) String start,
			@RequestParam(name="endDate",defaultValue="",required=false) String end,
			@RequestParam(name="page",defaultValue="0",required=false) int page,
			@RequestParam(name="size",defaultValue="5",required=false) int size) {
		ResultVo vo = new ResultVo();
		try {
			if(StringUtils.isBlank(start)||StringUtils.isBlank(end)) {
				vo.setValue(serv.getPlanItemsByPage("ck", page, size));
			}else {
				vo.setValue(serv.getPlanItemsByRange("ck", MyDateUtils.parseDate(start),
						MyDateUtils.parseDate(end), page, size));
			}
		}catch(ParseException e) {
			vo.setSuccess(false);
			vo.setMsg("日期格式错误："+e.getMessage());
			log.error("查询日期格式错误：",e);
		}catch(Exception e) {
			vo.setSuccess(false);
			vo.setMsg("未知异常："+e.getMessage());
			log.error("查询计划出现未知异常：",e);
		}
		return vo;
	}
	
	@RequestMapping(value="/searchByConds",method=RequestMethod.GET)
	public @ResponseBody ResultVo searchPlans(
			@RequestParam(name="startDate",defaultValue="",required=false) String start,
			@RequestParam(name="endDate",defaultValue="",required=false) String end,
			@RequestParam(name="page",defaultValue="0",required=false) int page,
			@RequestParam(name="size",defaultValue="5",required=false) int size,
			@RequestParam(name="selectState",defaultValue="-1",required=false) Integer state,
			@RequestParam(name="selectKey",defaultValue="",required=false) String key) {
		ResultVo vo = new ResultVo();
		try {
			if(StringUtils.isBlank(start)||StringUtils.isBlank(end)) {
				if(state<0) {
					vo.setValue(serv.getPlanItemsByConds("ck", page, size, key));
				}else {
					vo.setValue(serv.getPlanItemsByConds("ck", page, size, state, key));
				}
			}else {
				if(state<0) {
					vo.setValue(serv.getPlanItemsByConds("ck", MyDateUtils.parseDate(start),
							MyDateUtils.parseDate(end), page, size, key));
				}else {
					vo.setValue(serv.getPlanItemsByConds("ck", MyDateUtils.parseDate(start),
							MyDateUtils.parseDate(end), page, size, state, key));
				}
			}
		}catch(ParseException e) {
			vo.setSuccess(false);
			vo.setMsg("日期格式错误："+e.getMessage());
			log.error("查询日期格式错误：",e);
		}catch(Exception e) {
			vo.setSuccess(false);
			vo.setMsg("未知异常："+e.getMessage());
			log.error("查询计划出现未知异常：",e);
		}
		return vo;
	}
	
	@RequestMapping(value="/getPlanByDate",method=RequestMethod.GET)
	public @ResponseBody ResultVo searchPlans(
			@RequestParam(name="date",defaultValue="",required=false) String date) {
		ResultVo vo = new ResultVo();
		try {
			vo.setValue(serv.getPlanByTitle("ck",date));
		}catch(Exception e) {
			vo.setSuccess(false);
			vo.setMsg("未知异常："+e.getMessage());
			log.error("查询计划出现未知异常：",e);
		}
		return vo;
	}
	
	@RequestMapping(value="/addPlanItem",method=RequestMethod.POST)
	public @ResponseBody ResultVo addPlanItem(
			@ModelAttribute DailyPlanItemVo item) {
		ResultVo vo = new ResultVo();
		try {
			serv.addPlanItem("ck", item);
		}catch(Exception e) {
			vo.setSuccess(false);
			vo.setMsg("未知异常："+e.getMessage());
			log.error("新增计划项出现未知异常：",e);
		}
		return vo;
	}
	
	@RequestMapping(value="/updateItemState",method=RequestMethod.POST)
	public @ResponseBody ResultVo updateItemState(
			@RequestParam(name="itemId",defaultValue="") String itemId,
			@RequestParam(name="state",defaultValue="") Integer state,
			@RequestParam(name="descr",defaultValue="",required=false) String descr) {
		ResultVo vo = new ResultVo();
		try {
			serv.updateState("ck", itemId, state, descr);
		}catch(Exception e) {
			vo.setSuccess(false);
			vo.setMsg("未知异常："+e.getMessage());
			log.error("更新计划状态出现未知异常：",e);
		}
		return vo;
	}
}
