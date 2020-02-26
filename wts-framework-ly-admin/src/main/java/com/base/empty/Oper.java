package com.base.empty;

import java.util.Date;

import com.base.quartz.IWorker;

public class Oper {

	private String operId;
	private String opName;
	private String opDesc;
	private String param;
	private String OperBean;
	private String crUser;
	private Date crTime;

		public String getOperId() {
			return operId;
		}

		public void setOperId(String operId) {
			this.operId = operId;
		}

		public String getOpName() {
			return opName;
		}

		public void setOpName(String opName) {
			this.opName = opName;
		}

		public String getOpDesc() {
			return opDesc;
		}

		public void setOpDesc(String opDesc) {
			this.opDesc = opDesc;
		}

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		public String getOperBean() {
			return OperBean;
		}

		public String getCrUser() {
			return crUser;
		}

		public void setCrUser(String crUser) {
			this.crUser = crUser;
		}

		public Date getCrTime() {
			return crTime;
		}

		public void setCrTime(Date crTime) {
			this.crTime = crTime;
		}

		public IWorker getWorker() throws Exception{
			Class workClass = null;
			try {
				workClass = Class.forName(this.getOperBean());
			} catch (ClassNotFoundException ex) {
				throw new Exception("指定Class[" + this.getOperBean() + "]无效！", ex);
			}
			return (IWorker)workClass.newInstance();
		}

			public boolean setOperBean(String _sOperName) throws Exception {
				Class workClass = null;
				try {
					workClass = Class.forName(_sOperName);
				} catch (ClassNotFoundException ex) {
					throw new Exception("指定Class[" + _sOperName + "]无效！", ex);
				}
				return setOperClass(workClass);
			}

			private boolean setOperClass(Class _sOperName) throws Exception {
				if (_sOperName == null)
					return false;
				try {
					if (!(_sOperName.newInstance() instanceof IWorker))
						throw new Exception("指定Class[" + _sOperName + "]没有实现com.trt.framework.exchange.quartz.IScheduleJob!");
				} catch (Exception ex) {
					throw new Exception( "指定Class[" + _sOperName + "]不能实例化！", ex);
				}
				return setOperBean(_sOperName.getName());
			}

			
			


	

}
