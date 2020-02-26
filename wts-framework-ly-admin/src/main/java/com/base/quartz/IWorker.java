/**
 * Power By percyLee
 * Copyright: Copyright (c) 2016 percyLee<BR>
 * 
 * @author percyLee (Email:shininglxj@163.com)
 * @version 1.0  2016-9-18
 */
package com.base.quartz;

import com.base.empty.ScheduleJob;

/**
 * Title: <BR>
 * Description: <BR>
 * TODO <BR>
 */
public interface IWorker {

	public void execute(ScheduleJob scheduleJob) throws Exception;

}
