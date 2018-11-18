package com.ck.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.controller.base.ControllerBase;
import com.ck.service.UserService;
import com.ck.util.MyDateUtils;
import com.ck.vo.ResultVo;
@Controller
@RequestMapping(value="/user")
public class UserController extends ControllerBase{
	
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
			vo.setValue(serv.getPlanItemsByRange("ck", MyDateUtils.parseDate(start),
					MyDateUtils.parseDate(end), page, size));
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
}
