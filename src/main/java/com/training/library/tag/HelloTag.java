package com.training.library.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloTag extends TagSupport{
    private String role;
    private String userName;

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder sb = new StringBuilder()
                    .append("Hello, ")
                    .append(userName)
                    .append("! You are logged as a ")
                    .append(role.toLowerCase())
                    .append(".");
            pageContext.getOut().write(sb.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
