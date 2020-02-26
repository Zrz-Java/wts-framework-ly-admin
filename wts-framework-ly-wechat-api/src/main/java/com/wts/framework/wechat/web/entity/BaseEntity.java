package com.wts.framework.wechat.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.wts.framework.wechat.util.CMyDateTime;

/**  
 * @Title: ItpEntity.java
 * @Package com.trt.framework.itp.web.entity
 * @Description: TODO
 * @author percyLee
 * @date 2018年3月23日 上午11:49:28
 */
public class BaseEntity implements Serializable {
	
	protected Map<String, Object> v = new HashMap<String, Object>();

	private static final long serialVersionUID = 1L;
	
	public  Map<String, Object> getV(){
		return this.v;
	}
	
	public void setProperties(Map<String, Object> properties) {
		this.v.putAll(properties);
	}
	
	public boolean setProperty(String _sName, Object _oValue) {
		if ((_sName == null) || (getV() == null)) {
			return false;
		}
		_sName = _sName.toUpperCase();
		getV().put(_sName, _oValue == null ? "" : _oValue);
		return true;
	}

	public Object getProperty(String _sName) {
		if ((_sName == null) || (getV() == null)) {
			return null;
		}
		Object objValue = getV().get(_sName);
		if (objValue == null)
			return null;
		
		return objValue;
	}

	public String getPropertyAsString(String _sName) {
		Object objValue = getProperty(_sName);
		if (objValue == null)
			return null;
		return objValue.toString();
	}

	public String getPropertyAsString(String _sName, String _sDefaultValue) {
		Object objValue = getProperty(_sName);
		if (objValue == null) {
			return _sDefaultValue;
		}
		if ((objValue instanceof CMyDateTime) && (((CMyDateTime) objValue).isNull())) {
			return _sDefaultValue;
		}
		if (objValue instanceof Date) {
			CMyDateTime cMyDateTime = new CMyDateTime();
			Date p_dDate = (Date) objValue;
			try {
				cMyDateTime.setDate(new java.sql.Date(p_dDate.getTime()));
				return cMyDateTime.toString();
			} catch (Exception e) {
				return _sDefaultValue;
			}
		}
		return objValue.toString();
	}
	
	public Date getPropertyAsDate(String _sName) {
		Object objValue = getProperty(_sName);
		if (objValue == null)
			return null;
		if(objValue instanceof String){
			CMyDateTime objCMyDateTime = new CMyDateTime();
			try {
				objCMyDateTime.setDateTimeWithString(objValue.toString());
				return objCMyDateTime.getDateTime();
			} catch (Exception e) {
				return null;
			}
		}
		return (Date)objValue;
	}

	public long getPropertyAsLong(String _sName, long _lDefault) {
		Object objValue = getProperty(_sName);
		if (isNullValue(objValue))
			return _lDefault;
		if (objValue instanceof Number) {
			return ((Number) objValue).longValue();
		}
		return Long.parseLong(objValue.toString());
	}

	public int getPropertyAsInt(String _sName, int _nDefault) {
		Object objValue = getProperty(_sName);
		if (isNullValue(objValue))
			return _nDefault;
		if (objValue instanceof String) {
			if (null == objValue || ((String) objValue).length() == 0)
				return _nDefault;
		}
		if (objValue instanceof Number) {
			return ((Number) objValue).intValue();
		}
		return Integer.parseInt(objValue.toString());
	}

	public boolean getPropertyAsBoolean(String _sName, boolean _bDefault) {
		int nValue = getPropertyAsInt(_sName, (_bDefault) ? 1 : 0);
		return (nValue != 0);
	}


	private boolean isNullValue(Object objValue) {
		return (objValue == null);
	}
	
}
