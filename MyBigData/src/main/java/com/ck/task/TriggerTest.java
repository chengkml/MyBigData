package com.ck.task;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.scheduling.TriggerContext;

public class TriggerTest implements Trigger{

	@Override
	public TriggerKey getKey() {
		TriggerKey key = new TriggerKey("ck");
		return key;
	}

	@Override
	public JobKey getJobKey() {
		JobKey key = new JobKey("ck");
		return key;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCalendarName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobDataMap getJobDataMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean mayFireAgain() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getNextFireTime() {
		// TODO Auto-generated method stub
		return new Date();
	}

	@Override
	public Date getPreviousFireTime() {
		// TODO Auto-generated method stub
		return new Date();
	}

	@Override
	public Date getFireTimeAfter(Date afterTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getFinalFireTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMisfireInstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TriggerBuilder<? extends Trigger> getTriggerBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Trigger other) {
		// TODO Auto-generated method stub
		return 0;
	}

}
