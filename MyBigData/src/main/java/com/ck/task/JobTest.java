package com.ck.task;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobTest implements Job,Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("ck");
	}
	
}
