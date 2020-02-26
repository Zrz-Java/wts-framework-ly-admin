package com.base.tag;//package com.base.tag;
//
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import com.trt.framework.db.QueryFilter;
//import com.trt.framework.db.jdbc.JdbcTemplate;
//import com.trt.framework.persistent.ClassInfo;
//
//public class CMyDicsTag extends TagSupport {
//
//	private static final long serialVersionUID = 4196617082365160717L;
//	private Integer parentId = 0;
//	private Integer rootId = 0;
//	private Integer num = 100;
//	private Integer startpos = 0;
//	private Integer pageNo = 0;
//	private Integer pageSize = 0;
//	private Iterator<ClassInfo> items;
//	private String var;
//	private Integer index = 1;
//
//	public CMyDicsTag() {
//		super();
//	}
//
//	@Override
//	public int doAfterBody() throws JspException {
//		if (items.hasNext()) {
//			pageContext.setAttribute(var, items.next());
//			pageContext.setAttribute("index", index++);
//			return EVAL_BODY_AGAIN;
//		} else {
//			return SKIP_BODY;
//		}
//	}
//
//	@Override
//	public int doEndTag() throws JspException {
//		pageContext.removeAttribute(var);
//		pageContext.removeAttribute("index");
//		index = 1;
//		return EVAL_PAGE;
//	}
//
//	@Override
//	public int doStartTag() throws JspException {
//		try {
//
//			JdbcTemplate jdbcTemplate = new JdbcTemplate();
//			QueryFilter queryFilter = new QueryFilter("", "ROOTID=? AND PARENTID=?", "CCODE ASC");
//			queryFilter.addSearchValue("ROOTID", this.rootId);
//			queryFilter.addSearchValue("PARENTID", this.parentId);
//
//			if (this.pageSize > 0) {
//				queryFilter.setPageSize(this.pageSize);
//				queryFilter.setPageNo(this.pageNo > 0 ? this.pageNo : 1);
//			}
//
//			List<ClassInfo> classInfos = jdbcTemplate.queryForList(ClassInfo.class, queryFilter);
//			int totalRecord = jdbcTemplate.queryForCount(ClassInfo.class, queryFilter);
//
//			if (pageSize == 0) {
//				startpos = startpos > num ? (num - 1) : startpos;
//				int toIndex = startpos == 0 ? num : (num + startpos - 1);
//				if (toIndex > classInfos.size()) {
//					classInfos = classInfos.subList(startpos, classInfos.size());
//				} else {
//					classInfos = classInfos.subList(startpos, toIndex);
//				}
//			}
//			this.items = classInfos.iterator();
//			int totalPage = pageSize == 0 ? 1 : totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
//			pageContext.setAttribute(parentId + "_totalRecord", totalRecord);
//			pageContext.setAttribute(parentId + "_totalPage", totalPage);
//
//			if (items.hasNext()) {
//				pageContext.setAttribute(var, items.next());
//				return EVAL_BODY_INCLUDE;
//			} else {
//				return SKIP_BODY;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return SKIP_BODY;
//		}
//	}
//
//	@Override
//	public void release() {
//		super.release();
//	}
//
//	public Integer getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(Integer parentId) {
//		this.parentId = parentId;
//	}
//
//	public Integer getNum() {
//		return num;
//	}
//
//	public void setNum(Integer num) {
//		this.num = num;
//	}
//
//	public Integer getStartpos() {
//		return startpos;
//	}
//
//	public void setStartpos(Integer startpos) {
//		this.startpos = startpos;
//	}
//
//	public Integer getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(Integer pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public Integer getPageNo() {
//		return pageNo;
//	}
//
//	public void setPageNo(Integer pageNo) {
//		this.pageNo = pageNo;
//	}
//
//	public String getVar() {
//		return var;
//	}
//
//	public void setVar(String var) {
//		this.var = var;
//	}
//
//	public Integer getRootId() {
//		return rootId;
//	}
//
//	public void setRootId(Integer rootId) {
//		this.rootId = rootId;
//	}
//
//}
