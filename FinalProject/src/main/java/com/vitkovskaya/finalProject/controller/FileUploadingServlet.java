package com.vitkovskaya.finalProject.controller;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.command.common.UploadImageCommand;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

import java.util.UUID;

@WebServlet(urlPatterns = {"/Upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getParts().stream().forEach(part -> {
            try {
                request.setAttribute(ConstantName.ATTR_PARTS, part);
                RequestContent content = new RequestContent(request);
                Command command = new UploadImageCommand();
                Router router = command.execute(content);
                content.insertValues(request);
                if (router.getPagePath() != null) {
                    if (router.getType().equals(RouteType.FORWARD)) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                        dispatcher.forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + router.getPagePath());
                    }
                } else {
                    response.sendRedirect(request.getContextPath() +
                            ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
                }
            } catch (IOException | ServletException e) {
                logger.log(Level.ERROR, "Image upload failed!", e);
            }
        });
    }
}