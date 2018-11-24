package com.ck.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ck.domain.Subject;
import com.ck.orm.dao.SubjectDao;
import com.ck.orm.entity.SubjectPo;
import com.ck.repository.base.BaseRepository;
@Repository
public class SubjectRepository extends BaseRepository<SubjectDao,SubjectPo,Subject>{

	@Override
	public Subject poToModel(SubjectPo po) {
		Subject model = new Subject();
		if(null!=po) {
			BeanUtils.copyProperties(po, model);
		}
		return model;
	}

	@Override
	public SubjectPo modelToPo(Subject model) {
		SubjectPo po = new SubjectPo();
		if(null!=model) {
			BeanUtils.copyProperties(model, po);
		}
		return po;
	}
	
	public List<Subject> getByType(String type) {
		return posToModels(dao.getSubjectByType(type));
	}

}
