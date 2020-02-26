package com.base.tag;/*package com.base.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.trt.framework.persistent.ClassInfo;
import com.trt.framework.util.CMyString;

*//**
 * Title: <BR>
 * Description: <BR>
 * TODO <BR>
 *//*
public class CMyDicCheckTag extends TagSupport {

	private static final long serialVersionUID = -8627305302729836771L;
	private ClassInfo classInfo;
	private List<ClassInfo> classInfos;
	private String ids;
	private String codeType;

	@Override
	public int doStartTag() throws JspException {
		try {
			if(CMyString.isEmpty(ids) && null == classInfos){
				return SKIP_BODY;
			}
			if(!CMyString.isEmpty(ids)){
				String[] __aIds = ids.trim().split(",");
				for (String id : __aIds) {
					if(CMyString.isEmpty(codeType)){
						if(classInfo.getClassInfoId() == Integer.parseInt(id)){
							return EVAL_BODY_INCLUDE;
						}
					}else if(codeType.equalsIgnoreCase("code")){
						if(classInfo.getCode().equalsIgnoreCase(id)){
							return EVAL_BODY_INCLUDE;
						}
					}
				}
			}
			if(null != classInfos){
				for (ClassInfo classInfo : classInfos) {
					if(CMyString.isEmpty(codeType)){
						if(classInfo.getClassInfoId() == this.classInfo.getClassInfoId()){
							return EVAL_BODY_INCLUDE;
						}
					}else if(codeType.equalsIgnoreCase("code")){
						if(classInfo.getCode().equalsIgnoreCase(classInfo.getCode())){
							return EVAL_BODY_INCLUDE;
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			return SKIP_BODY;
		}
		return SKIP_BODY;
	}

	@Override
	public void release() {
		super.release();
	}


	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public List<ClassInfo> getClassInfos() {
		return classInfos;
	}

	public void setClassInfos(List<ClassInfo> classInfos) {
		this.classInfos = classInfos;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	
	
}
*/