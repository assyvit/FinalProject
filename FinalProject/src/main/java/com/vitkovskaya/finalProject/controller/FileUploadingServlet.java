package com.vitkovskaya.finalProject.controller;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.command.common.UploadImageCommand;
import com.vitkovskaya.finalProject.service.serviceImpl.UserServiceImpl;
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
    private final static String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String applicationDir = request.getServletContext().getRealPath(ConstantName.ATTRIBUTE_EMPTY_VALUE);
        logger.log(Level.DEBUG, applicationDir);
        String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
        logger.log(Level.DEBUG, uploadFileDir);
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        logger.log(Level.DEBUG, "Upload File Directory =" + fileSaveDir.getAbsolutePath());

        request.getParts().stream().forEach(part -> {
            try {
                // part.write(uploadFilePath + File.separator + part.getSubmittedFileName());//.substring(2)
                String path = part.getSubmittedFileName();
                String randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
                logger.log(Level.DEBUG, randFilename + " FILE NAME");
                part.write(uploadFileDir + randFilename);
              request.setAttribute(ConstantName.PARAMETER_FILE_NAME, randFilename);
                RequestContent content = new RequestContent(request);

                Command command = new UploadImageCommand();
                Router router = command.execute(content);
                content.insertValues(request);
                logger.log(Level.DEBUG, router.getPagePath() != null + "FROM ROUTER");
                if (router.getPagePath() != null) {
                    if (router.getType().equals(RouteType.FORWARD)) {
                        logger.log(Level.DEBUG, router.getPagePath() + "WHERE TO GO");
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
//                request.setAttribute("upload_result", " upload failed ");
            }
        });
    }
}