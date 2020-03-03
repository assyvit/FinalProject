package com.vitkovskaya.finalProject.tag;

import com.vitkovskaya.finalProject.entity.UserRole;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class RoleTag extends TagSupport {
    private String role;
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            String to = null;

            if ("ADMIN".equalsIgnoreCase(role)) {
                to = "Role: " + role;
            }
            else if ("CLIENT".equalsIgnoreCase(role)) {
                to = "Role: " + role;
            } else if ("CLEANER".equalsIgnoreCase(role)) {
                to = "Role: " + role;
            } else {
                to = "Role: GUEST ";
            }
            pageContext.getOut().write("<h5>" + to + "</h5>");
          //  pageContext.getOut().write("<hr/>" + to + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}

