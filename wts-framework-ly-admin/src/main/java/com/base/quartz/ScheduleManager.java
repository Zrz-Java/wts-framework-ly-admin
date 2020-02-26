package com.base.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.base.empty.ScheduleJob;
import com.base.mapping.ScheduleJobMapper;
import com.base.util.context.PageUtil;

/**
 * 
 * @Description: 计划任务管理
 */

public class ScheduleManager {

	private static final Logger logger = Logger.getLogger(ScheduleManager.class);
	@Resource
	private ScheduleJobMapper scheduleJobMapper;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	public void initSchedule() throws Exception{
		
//		jt.execute("delete from qrtz_blob_triggers");
//		jt.execute("delete from qrtz_calendars");
//		jt.execute("delete from qrtz_cron_triggers");
//		jt.execute("delete from qrtz_fired_triggers");
//		jt.execute("delete from qrtz_locks");
//		jt.execute("delete from qrtz_paused_trigger_grps");
//		jt.execute("delete from qrtz_scheduler_state");
//		jt.execute("delete from qrtz_simple_triggers");
//		jt.execute("delete from qrtz_simprop_triggers");
//		jt.execute("delete from qrtz_triggers");
//		jt.execute("delete from qrtz_job_details");
		
		Integer totalRecord = scheduleJobMapper.countScheduleJob();
		
		
		
		Long startTime =  System.currentTimeMillis();
		logger.info("开始导入[" + totalRecord +"]条计划任务");
		PageUtil pageTool = new PageUtil(1, 200);
		pageTool.setTotalRecord(totalRecord);
		for (int i = 1; i <= pageTool.getPageSize(); i++) {
			List<ScheduleJob> scheduleJobs = scheduleJobMapper.queryScheduleJob((i-1)*pageTool.getPageSize(), pageTool.getPageSize());
			for (ScheduleJob scheduleJob : scheduleJobs) {
				try{
					addJob(scheduleJob);
				}catch (Exception e) {
					logger.error("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(), e);
				}
			}
		}
		Long endTime =  System.currentTimeMillis();
		logger.info("完成[" + totalRecord +"]条计划任务导入 - 用时" + (endTime - startTime) / 1000 + "秒");
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<Schedule> getAllSchedule() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<Schedule> jobList = new ArrayList<Schedule>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				Schedule schedule = new Schedule();
				schedule.setJobName(jobKey.getName());
				schedule.setJobGroup(jobKey.getGroup());
				schedule.setDescription("触发器:" + trigger.getKey());
				schedule.setBeanClass(jobKey.getClass().getName());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				schedule.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					schedule.setCronExpression(cronExpression);
				}
				jobList.add(schedule);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<Schedule> getRunningSchedule() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<Schedule> jobList = new ArrayList<Schedule>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			Schedule schedule = new Schedule();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			schedule.setJobName(jobKey.getName());
			schedule.setJobGroup(jobKey.getGroup());
			schedule.setBeanClass(jobKey.getClass().getName());
			schedule.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			schedule.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				schedule.setCronExpression(cronExpression);
			}
			jobList.add(schedule);
		}
		return jobList;
	}
	
	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addJob(ScheduleJob scheduleJob) throws SchedulerException {
		if (scheduleJob == null || ScheduleJob.STATUS_NORMAL != scheduleJob.getSchStatus()) {
			return;
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),
				scheduleJob.getOperId());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class<? extends Job> clazz = ScheduleJob.STATUS_NORMAL != scheduleJob.getSchStatus() ? QuartzJobFactory.class
					: QuartzJobFactoryDisallowConcurrentExecution.class;

			JobDetail jobDetail = JobBuilder
					.newJob(clazz)
					.withIdentity("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),
							scheduleJob.getOperId()).build();

			jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getExeCron());

			trigger = TriggerBuilder.newTrigger()
					.withIdentity("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),
							scheduleJob.getOperId()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getExeCron());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),scheduleJob.getOperId());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),scheduleJob.getOperId());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),scheduleJob.getOperId());
		scheduler.deleteJob(jobKey);

	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(),scheduleJob.getOperId());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey("[" + scheduleJob.getSchId() + "]" + scheduleJob.getSchName(), scheduleJob.getOperId());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getExeCron());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}

}
