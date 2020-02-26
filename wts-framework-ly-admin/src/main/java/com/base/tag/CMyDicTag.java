package com.base.tag;//package com.base.tag;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import com.trt.framework.db.jdbc.JdbcTemplate;
//import com.trt.framework.persistent.ClassInfo;
//import com.trt.framework.util.CMyString;
//
///**
// * Title: <BR>
// * Description: <BR>
// * TODO <BR>
// */
//public class CMyDicTag extends TagSupport {
//
//	private static final long serialVersionUID = -8627305302729836771L;
//	private Integer classInfoId = 0;
//	private String field = "CNAME";
//	private Integer num = 10000;
//
//	@Override
//	public int doStartTag() throws JspException {
//		
//		JspWriter w = pageContext.getOut();
//		field = CMyString.isEmpty(field) ? "CNAME" : field;
//		try {
//			JdbcTemplate jdbcTemplate = new JdbcTemplate();
//			ClassInfo classInfo = jdbcTemplate.findById(ClassInfo.class, classInfoId);
//			
//			if(null != classInfo){
//				String fieldValue = classInfo.getPropertyAsString(field, "");
//				if(num > 0){
//					fieldValue = CMyString.truncateStr(fieldValue, num, "...");
//				}
//				w.print(fieldValue);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return SKIP_BODY;
//	}
//
//	@Override
//	public void release() {
//		super.release();
//	}
//
//	/**
//	 * @return the classInfoId
//	 */
//	public Integer getClassInfoId() {
//		return classInfoId;
//	}
//
//	/**
//	 * @param classInfoId the classInfoId to set
//	 */
//	public void setClassInfoId(Integer classInfoId) {
//		this.classInfoId = classInfoId;
//	}
//
//	/**
//	 * @return the field
//	 */
//	public String getField() {
//		return field;
//	}
//
//	/**
//	 * @param field the field to set
//	 */
//	public void setField(String field) {
//		this.field = field;
//	}
//
//	/**
//	 * @return the num
//	 */
//	public Integer getNum() {
//		return num;
//	}
//
//	/**
//	 * @param num the num to set
//	 */
//	public void setNum(Integer num) {
//		this.num = num;
//	}
//
//
//	
//
//	
//}
