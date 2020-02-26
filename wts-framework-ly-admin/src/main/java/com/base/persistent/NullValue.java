package com.base.persistent;

import java.io.Serializable;

public class NullValue implements Serializable
{
private static final long serialVersionUID = -2065918689680886981L;
private int m_nDataType;

public NullValue(int _nDataType)
{
  this.m_nDataType = _nDataType;
}

public int getDataType() {
  return this.m_nDataType;
}

public void setDataType(int dataType) {
  this.m_nDataType = dataType;
}

public String toString()
{
  return "";
}
}
