package com.ck.util;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ck.base.SpringJunitTestBase;
import com.ck.task.JobTest;
@Component
public class SchedulerUtilTest extends SpringJunitTestBase{

	@Test
	public void testGetSchedulerInst() {
		System.out.println(SchedulerUtil.getScheduler());
	}
	
	@Test
	public void addCronJob() {
		SchedulerUtil.addCronJob("test", JobTest.class, "0/1 * * * * ? ", new JobTest());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
	}
	
}
