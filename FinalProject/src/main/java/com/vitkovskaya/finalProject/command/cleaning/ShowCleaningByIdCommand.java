package com.vitkovskaya.finalProject.command.cleaning;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ShowCleaningByIdCommand implements Command {
    private final static Logger logger = LogManager.getLogger();


    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();
        CleaningServiceImpl cleaningService = new CleaningServiceImpl();

        List<Cleaning> cleaningList = (List<Cleaning>) content.getRequestAttribute(ConstantName.PARAMETER_CLEANING_LIST);
        long cleaningId = Long.valueOf(content.getRequestParameter(ConstantName.PARAMETER_CLEANING_ID));
        Optional<Cleaning> cleaningOptional;
        try {
            cleaningOptional = cleaningService.findCleaningById(cleaningId);
            if (cleaningOptional.isPresent()) {
                Cleaning cleaning = cleaningOptional.get();
                content.addRequestAttribute(ConstantName.ATTRIBUTE_CLEANING, cleaning);
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_SHOW_SINGLE_CLEANING));
            } else {
// FIXME: 20.02.2020
            }
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
