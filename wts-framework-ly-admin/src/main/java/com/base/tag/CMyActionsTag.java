package com.base.tag;

import com.base.empty.Action;
import com.base.mapping.ActionMapper;
import com.base.util.context.PageUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Iterator;
import java.util.List;

public class CMyActionsTag extends TagSupport {

	
	
	private static final long serialVersionUID = 4196617082365160717L;
	private static ApplicationContext context  = new ClassPathXmlApplicationContext(new String[]{"spring/spring-bean.xml","spring/spring-dao.xml"});
	private Integer parentId = 0;
	private Integer num = 100;
	private Integer startpos = 0;
	private Integer pageNo = 0;
	private Integer pageSize = 0;
	private Iterator<Action> items;
	private String var;
	private Integer index = 1;

	public CMyActionsTag() {
		super();
	}
	
	@Override
	public int doAfterBody() throws JspException {
		if (items.hasNext()) {
			pageContext.setAttribute(var, items.next());
			pageContext.setAttribute("index", index++);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(var);
		pageContext.removeAttribute("index");
		index = 1;
		return EVAL_PAGE;
	}
	@Override
	public int doStartTag() throws JspException {
		try {
			if (this.pageSize > 0) {
				pageNo = this.pageNo == 0 ? 1 : this.pageNo;
			}
			PageUtil page = new PageUtil(pageNo, pageSize);
			ActionMapper actionMapper = (ActionMapper)context.getBean("actionMapper");
			List<Action> actions = actionMapper.queryByParentId(parentId, page.getPageOfset(), page.getPageSize());
			int totalRecord = actionMapper.countByParentId(parentId);
			if (pageSize == 0) {
				startpos = startpos > num ? (num - 1) : startpos;
				int toIndex = startpos == 0 ? num : (num + startpos - 1);
				if (toIndex > actions.size()) {
					actions = actions.subList(startpos, actions.size());
				} else {
					actions = actions.subList(startpos, toIndex);
				}
			}
			this.items = actions.iterator();
			int totalPage = pageSize == 0 ? 1 : totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
			pageContext.setAttribute(parentId + "_totalRecord", totalRecord);
			pageContext.setAttribute(parentId + "_totalPage", totalPage);

			if (items.hasNext()) {
				pageContext.setAttribute(var, items.next());
				return EVAL_BODY_INCLUDE;
			} else {
				return SKIP_BODY;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}
	}

	@Override
	public void release() {
		super.release();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStartpos() {
		return startpos;
	}

	public void setStartpos(Integer startpos) {
		this.startpos = startpos;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
