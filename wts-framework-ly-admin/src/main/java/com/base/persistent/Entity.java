package com.base.persistent;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.base.exception.CMyException;
import com.base.util.CMyDateTime;

public abstract class Entity implements Cloneable, Serializable {
	private static final long serialVersionUID = -5197811321283330025L;
	protected Hashtable<String, Object> v = new Hashtable();

	public abstract int getEntityNo();

	public abstract String getTableName();

	public abstract String getPriKeyName();

	public abstract boolean needCache();

	public Serializable getId() {
		return (Serializable) getProperty(getPriKeyName());
	}

	public void setId(Serializable id) {
		setProperty(getPriKeyName(), id);
	}

	public void clearPropertise() {
		if (this.v != null)
			this.v.clear();
	}

	public Hashtable<String, Object> getV() {
		return this.v;
	}

	public void setProperties(Map<String, Object> properties) {
		this.v.putAll(properties);
	}

	public void setPropertiesExt(Map<String, Object> properties, Object o) throws Exception, IllegalAccessException {
		this.v.putAll(properties);
		if (!properties.isEmpty()) {
			for (String k : properties.keySet()) {
				Object v = null;
				if (!k.isEmpty()) {
					v = properties.get(k);
				}
				Field[] fields = null;
				fields = o.getClass().getDeclaredFields();
				String clzName = o.getClass().getSimpleName();
				for (Field field : fields) {
					int mod = field.getModifiers();
					if (field.getName().toUpperCase().equals(k.toUpperCase())) {
						field.setAccessible(true);

						String type = field.getType().toString();
						if (type.endsWith("String")) {
							if (v != null) {
								v = v.toString();
							} else {
								v = "";
							}
						}
						if (type.endsWith("Date")) {
							v = new Date(v.toString());
						}
						if (type.endsWith("Boolean")) {

							v = Boolean.getBoolean(v.toString());
						}
						if (type.endsWith("int")) {

							v = new Integer(v.toString());
						}
						if (type.endsWith("Long")) {

							v = new Long(v.toString());
						}
						field.set(o, v);
					}

				}

			}
		}
					
		
			
	}

	public void reMoveProperty(String _sName) {
		if (_sName != null)
			this.v.remove(_sName.trim().toUpperCase());
	}

	public void reMoveProperty(String[] _sNames) {
		for (String _sName : _sNames)
			if (_sName != null)
				this.v.remove(_sName.trim().toUpperCase());
	}

	public boolean setProperty(String _sName, Object _oValue) {
		if ((_sName == null) || (getV() == null)) {
			return false;
		}
		_sName = _sName.toUpperCase();
		getV().put(_sName, (_oValue == null) ? new NullValue(0) : _oValue);
		return true;
	}

	public Object getProperty(String _sName) {
		if ((_sName == null) || (getV() == null)) {
			return null;
		}
		_sName = _sName.toUpperCase();
		Object objValue = getV().get(_sName);
		if ((objValue == null) || (objValue instanceof NullValue))
			return null;
		try {
			objValue = convertValue(objValue);
		} catch (CMyException localCMyException) {
		}
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
				cMyDateTime.setDate(new Date(p_dDate.getTime()));
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
		if (objValue instanceof String) {
			CMyDateTime objCMyDateTime = new CMyDateTime();
			try {
				objCMyDateTime.setDateTimeWithString(objValue.toString());
				return objCMyDateTime.getDateTime();
			} catch (Exception e) {
				return null;
			}
		}
		return (Date) objValue;
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
		if ((objValue instanceof String) && (((objValue == null) || (((String) objValue).length() == 0)))) {
			return _nDefault;
		}
		if (objValue instanceof Number) {
			return ((Number) objValue).intValue();
		}
		return Integer.parseInt(objValue.toString());
	}

	public boolean getPropertyAsBoolean(String _sName, boolean _bDefault) {
		int nValue = getPropertyAsInt(_sName, (_bDefault) ? 1 : 0);
		return nValue != 0;
	}

	private Object convertValue(Object object) throws CMyException {
		if ((object instanceof Integer) || (object instanceof Long) || (object instanceof String) || (object instanceof Float)
				|| (object instanceof Double) || (object instanceof Short))
			return object;
		if (object instanceof Date)
			return object;
		if (object instanceof java.sql.Date) {
			CMyDateTime objValue = new CMyDateTime();
			try {
				Timestamp dateTime = new Timestamp(((java.sql.Date) object).getTime());
				objValue.setDateTimeWithTimestamp(dateTime);
			} catch (Exception e) {
				try {
					objValue.setDate((Date) object);
				} catch (Exception e1) {
					return null;
				}
			}
			return objValue.getDateTime();
		}
		if (object instanceof CMyDateTime)
			return ((CMyDateTime) object).getDateTime();
		if (object instanceof Timestamp) {
			CMyDateTime objValue = new CMyDateTime();
			Timestamp p_dDate = (Timestamp) object;
			try {
				objValue.setDate(new Date(p_dDate.getTime()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			return objValue.getDateTime();
		}
		return object;
	}

	private boolean isNullValue(Object objValue) {
		return (objValue == null) || (objValue instanceof NullValue);
	}

	public String toString() {
		return getV().toString();
	}
}
