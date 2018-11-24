package com.ck.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ck.domain.Subject;
import com.ck.orm.dao.SubjectDao;
import com.ck.orm.entity.SubjectPo;
import com.ck.repository.SubjectRepository;
import com.ck.service.base.BaseService;
import com.ck.vo.SubjectVo;
@Service
public class SubjectService extends BaseService<SubjectDao,SubjectRepository,SubjectPo,Subject,SubjectVo>{

	@Override
	public SubjectVo modelToVo(Subject model) {
		if(null!=model) {
			SubjectVo vo = new SubjectVo();
			BeanUtils.copyProperties(model, vo);
			return vo;
		}
		return null;
	}

	@Override
	public Subject voToModel(SubjectVo vo) {
		if(null!=vo) {
			Subject model = new Subject();
			BeanUtils.copyProperties(vo, model);
			return model;
		}
		return null;
	}
	
	public List<SubjectVo> getSubjectByType(String type) {
		return modelsToVos(repo.getByType(type));
	}

}
