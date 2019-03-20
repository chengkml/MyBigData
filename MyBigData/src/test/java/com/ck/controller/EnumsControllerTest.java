package com.ck.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ck.base.SpringJunitTestBase;

public class EnumsControllerTest extends SpringJunitTestBase{
	
	@Autowired
	private EnumsController ct;
	
	@Test
	public void testEnumsMap() {
		System.out.println(ct.enumMap);
	}
	
}
