package com.ck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.controller.base.BaseController;
import com.ck.service.SubjectService;
import com.ck.vo.ResultVo;

@Controller
@RequestMapping(value="/subject")
public class SubjectController extends BaseController{
	
	@Autowired
	private SubjectService serv;
	
	@RequestMapping(value="/finishType",method=RequestMethod.GET)
	public @ResponseBody ResultVo getFinishTypes(@RequestParam(name="type",defaultValue="",required=false)String type) {
		ResultVo vo = new ResultVo();
		try {
			vo.setSuccess(true);
			vo.setValue(serv.getSubjectByType(type));
		}catch(Exception e) {
			vo.setSuccess(false);
			vo.setMsg("获取完成类型异常！"+e.getMessage());
			log.error("获取完成类型异常！",e);
		}
		return vo;
	}
}
