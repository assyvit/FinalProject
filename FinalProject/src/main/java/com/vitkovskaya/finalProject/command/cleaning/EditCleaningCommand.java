package com.vitkovskaya.finalProject.command.cleaning;

import com.vitkovskaya.finalProject.command.*;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.CleaningType;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.serviceImpl.CleaningServiceImpl;
import com.vitkovskaya.finalProject.util.ConfigurationManager;
import com.vitkovskaya.finalProject.util.MessageManager;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class EditCleaningCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * * Gets cleaning name, price, type, quantity description from the request.
     * Validates input values, if input data is not valid,
     * returns router to the same page with message about invalid input data.
     * Otherwise, edits cleaning and forward router to the same page.
     *
     * @param content {@link RequestContent} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateCleaningInputData(Map) 
     * @see CleaningServiceImpl#updateCleaning(Cleaning) 
     */
    @Override
    public Router execute(RequestContent content) {
        logger.log(Level.DEBUG, " IN EditCleaningCommand ");
        Router router = new Router();
        DataValidator validator = new DataValidator();
        String start = (String) content.getSessionAttribute(ConstantName.ATTRIBUTE_START);
        CleaningServiceImpl cleaningService = new CleaningServiceImpl();
        Map<String, String> cleaningParameters = new HashMap<>();
        String name = content.getRequestParameter(ConstantName.PARAMETER_CLEANING_NAME);
        String priceUnparsed = content.getRequestParameter(ConstantName.PARAMETER_CLEANING_PRICE);
        String cleaningTypeUnparsed = content.getRequestParameter(ConstantName.PARAMETER_CLEANING_TYPE);
        String quantityUnparsed = content.getRequestParameter(ConstantName.PARAMETER_CLEANING_QUANTITY);
        String description = content.getRequestParameter(ConstantName.PARAMETER_CLEANING_DESCRIPTION).trim();
        Long cleaningId = (Long) content.getSessionAttribute(ConstantName.ATTRIBUTE_CLEANING_ID);
        cleaningParameters.put(ConstantName.PARAMETER_CLEANING_NAME, name);
        cleaningParameters.put(ConstantName.PARAMETER_CLEANING_PRICE, priceUnparsed);
        cleaningParameters.put(ConstantName.PARAMETER_CLEANING_TYPE, cleaningTypeUnparsed);
        cleaningParameters.put(ConstantName.PARAMETER_CLEANING_QUANTITY, quantityUnparsed);
        cleaningParameters.put(ConstantName.PARAMETER_CLEANING_DESCRIPTION, description.trim());
        validator.validateCleaningInputData(cleaningParameters);
        Cleaning cleaning = new Cleaning(cleaningId, name, new BigDecimal(priceUnparsed),
                CleaningType.valueOf(cleaningTypeUnparsed.toUpperCase()), description, Integer.valueOf(quantityUnparsed));
        try {
            if (!cleaningParameters.containsValue(ConstantName.ATTRIBUTE_EMPTY_VALUE)) {
                if (cleaningService.updateCleaning(cleaning)) {
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANING, cleaning);
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANING_PROFILE));
                } else {
                    content.addRequestAttribute(ConstantName.ATTRIBUTE_CLEANING_EDIT_ERROR,
                            MessageManager.getProperty(ConstantName.MESSAGE_CLEANING_EDIT_ERROR));
                    content.addSessionAttribute(ConstantName.ATTRIBUTE_START, start);
                    router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANING_PROFILE));
                }
            } else {
                content.addSessionAttribute(ConstantName.ATTRIBUTE_CLEANING, cleaning);
                content.addSessionAttribute(ConstantName.ATTRIBUTE_VALIDATED_MAP, cleaningParameters);
                content.addRequestAttribute(ConstantName.ATTRIBUTE_VALIDATE_CLEANING_ERROR,
                        MessageManager.getProperty(ConstantName.MESSAGE_VALIDATE_CLEANING_ERROR));
                router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_CLEANING_PROFILE));
            }
        } catch (ServiceException e) {
            logger.error("Error executing editing cleaning", e);
            router.setPagePath(ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
        }
        return router;
    }
}
