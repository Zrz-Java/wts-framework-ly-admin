package com.base.quartz;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.base.empty.Oper;
import com.base.empty.ScheduleJob;
import com.base.mapping.OperMapper;
import com.base.mapping.ScheduleJobMapper;
import com.base.util.CMyString;

/**
 * @Description: 计划任务执行处 无状态
 */
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());
	@Resource
	private ScheduleJobMapper scheduleJobMapper;
	@Resource
	private OperMapper operMapper;
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		try {
			scheduleJob = scheduleJobMapper.findBySchId(scheduleJob.getSchId()) ;
			String clz =operMapper.findByOperId(scheduleJob.getOperId()).getOperBean();
			if (CMyString.isEmpty(clz)){
				throw new JobExecutionException(scheduleJob.getSchName() + "的BeanClass为空");
			}
			IWorker _worker = (IWorker)Class.forName(clz).newInstance();
			_worker.execute(scheduleJob);
		}catch (Exception e) {
			throw new JobExecutionException(scheduleJob.getSchName() + "的实例化失败为空", e);
		}
	}
}