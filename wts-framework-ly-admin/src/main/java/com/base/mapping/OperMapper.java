package com.base.mapping;

import org.apache.ibatis.annotations.Param;

import com.base.empty.Oper;
import com.base.empty.User;




public interface OperMapper {
	
   public Oper findByOperId(@Param("operId") String operId);
   
}
