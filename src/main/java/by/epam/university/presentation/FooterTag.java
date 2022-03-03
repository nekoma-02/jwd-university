package by.epam.university.presentation;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterTag extends TagSupport {

	private static final long serialVersionUID = 5324494786617317065L;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write("<footer class=\"page-footer font-small black\">");
			out.write("<div class=\"footer-copyright text-center py-3\">");
			out.write("© 2020 Copyright: <a href=\"/\"> MDBootstrap.com</a>");
			out.write("</div></footer>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		
		return SKIP_BODY;
	}

	
	
}
