package com.ck.orm.mapper.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.ck.base.SpringJunitTestBase;
import com.ck.orm.dao.base.BaseDao;
public class MapperDaoTest<M,P,D> extends SpringJunitTestBase{

	@Autowired
	private SqlSessionFactory factory;
	
	@Autowired
	protected BaseDao<P> pDao;
	
	private Class<M> clazz;
	
	protected D dao;
	
	protected M mDao;
	
	private SqlSession session;

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDao() {
		dao = (D)pDao;
	}
	
	@Before
	public void before() {
		session = factory.openSession();
		getGenericClass();
		mDao = session.getMapper(this.clazz);
	}

	@SuppressWarnings("unchecked")
	private void getGenericClass() {
		Class<?> clazz = getClass();
		while(clazz!=Object.class) {
			Type t = clazz.getGenericSuperclass();
			if(t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType)t).getActualTypeArguments();
				if(args[0] instanceof Class) {
					this.clazz = (Class<M>)args[0];
					break;
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
	
	@After
	public void after() {
		if(null!=session) {
			session.close();
		}
	}
}
