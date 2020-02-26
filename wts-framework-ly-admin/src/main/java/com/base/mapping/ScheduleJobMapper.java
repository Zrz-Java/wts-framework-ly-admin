package com.base.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.empty.ScheduleJob;




public interface ScheduleJobMapper {
	
   public ScheduleJob findBySchId(@Param("schId") String schId);
   
   public ScheduleJob findByOperId(@Param("operId") String operId);
   
   public List<ScheduleJob> queryScheduleJob(@Param("firstIndex") Integer firstIndex, @Param("pageSize") Integer pageSize);
   
   public int countScheduleJob();
   
   
}
