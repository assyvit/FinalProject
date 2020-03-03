package com.vitkovskaya.finalProject.command;

public class ConstantName {
    private ConstantName() {
    }

    public final static int ZERO_VALUE = 0;

    //Parameters
    public final static String PARAM_NAME_COMMAND = "command";
    public final static String PARAMETER_LOGIN = "login";
    public final static String PARAMETER_PASSWORD = "password";
    public final static String PARAMETER_PASSWORD_CONFIRMATION = "passwordConfirmation";
    public final static String PARAMETER_CURRENT_PASSWORD = "currentPassword";
    public final static String PARAMETER_NEW_PASSWORD = "newPassword";
    public final static String PARAMETER_USER = "user";
    public final static String PARAMETER_ID = "id";
    public final static String PARAMETER_USER_ID = "userId";
    public final static String PARAMETER_ADDRESS = "address";
    public final static String PARAMETER_FIRST_NAME = "firstName";
    public final static String PARAMETER_LAST_NAME = "lastName";
    public final static String PARAMETER_TELEPHONE_NUMBER = "telephoneNumber";
    public final static String PARAMETER_USER_ROLE = "userRole";
    public final static String PARAMETER_CLEANING_NAME = "name";
    public final static String PARAMETER_CLEANING_PRICE = "price";
    public final static String PARAMETER_CLEANING_TYPE = "type";
    public final static String PARAMETER_CLEANING_DESCRIPTION = "description";
    public final static String PARAMETER_CLEANING_QUANTITY = "quantity";
    public final static String PARAMETER_CLEANING_STATUS = "availableStatus";
    public final static String PARAMETER_CLEANER_ID = "cleanerId";
    public final static String PARAMETER_CLEANING_ID = "cleaningId";
    public final static String PARAMETER_CLEANING_LIST = "cleaningList";
    public final static String PARAMETER_MAIL = "mail";
    public final static String PARAMETER_MAIL_SUBJECT = "subject";
    public final static String PARAMETER_MAIL_MESSAGE = "message";
    public final static String PARAMETER_CURRANT_PAGE = "currentPage";
    public final static String EXECUTING_DATE = "executing_date";
    public final static String PARAMETER_PAYMENT_TYPE = "paymentType";
    public final static String PARAMETER_ORDER_COMMENT = "comment";
    public final static String PARAMETER_ORDER_ID = "orderId";
    public final static String PARAMETER_ORDER_STATUS = "orderStatus";
    public final static String PARAMETER_LOCALE = "language";
    public final static String PARAMETER_LOCALE_RU = "en_EN";
    public final static String PARAMETER_PAGE_START = "pageStart";
    public final static String PARAMETER_FILE_NAME = "fileName";
    public final static String PARAMETER_UPLOAD_DIRECTORY = "/uploads/";

    // attributes


    public final static String ATTRIBUTE_EMPTY_VALUE = "";
    public final static String ATTRIBUTE_USER_ROLE = "userRole";
    public final static String ATTRIBUTE_USER = "user";
    public final static String ATTRIBUTE_LOGIN_ERROR = "loginError";
    public final static String ATTRIBUTE_SHOW_CLEANERS_ERROR = "showCleanersError";
    public final static String ATTRIBUTE_SHOW_ORDERS_ERROR = "showOrdersError";
    public final static String ATTRIBUTE_SHOW_CLIENT_ERROR = "showClientsError";
    public final static String ATTRIBUTE_SHOW_CLEANING_ERROR = "showCleaningError";
    public final static String ATTRIBUTE_CLIENT_LIST = "clientList";
    public final static String ATTRIBUTE_CLIENT__BLOCKED_LIST = "clientBlockedList";
    public final static String ATTRIBUTE_CLEANING_LIST = "cleaningList";
    public final static String ATTRIBUTE_CLEANER_LIST = "cleanerList";
    public final static String ATTRIBUTE_CLEANER__BLOCKED_LIST = "cleanerBlockedList";
    public final static String ATTRIBUTE_ORDERS_LIST = "orderList";
    public final static String ATTRIBUTE_CLIENT_ORDER_LIST = "clientOrderList";
    public final static String ATTRIBUTE_REGISTRATION_ERROR = "errorRegistrationMessage";
    public final static String ATTRIBUTE_CURRENT_PASSWORD_ERROR = "currentPasswordError";
    public final static String ATTRIBUTE_PASSWORD_CHANGE_ERROR = "changePasswordError";
    public final static String ATTRIBUTE_LOGIN_NOT_UNIQ = "errorNotUniqLogin";
    public final static String ATTRIBUTE_ADD_CLEANING_ERROR = "errorAddCleaning";
    public final static String ATTRIBUTE_ADD_CLEANING_SUCCESS = "successAddCleaning";
    public final static String ATTRIBUTE_VALIDATE_CLEANING_ERROR = "errorValidateCleaning";
    public final static String ATTRIBUTE_NULL_PAGE = "nullPage";
    public final static String ATTRIBUTE_WRONG_ACTION = "wrongAction";
    public final static String ATTRIBUTE_VALIDATED_MAP = "map";
    public final static String ATTRIBUTE_CLEANING = "cleaning";
    public final static String ATTRIBUTE_ORDER_LIST = "cleaningItemList";
    public final static String ATTRIBUTE_CART_SUM = "totalSum";
    public final static String ATTRIBUTE_CLEANING_ID = "cleaningId";
    public final static String ATTRIBUTE_ITEM_QUANTITY_IN_CART = "quantity";
    public final static String ATTRIBUTE_USER_PROFILE = "userProfile";
    public final static String ATTRIBUTE_ITEM_LIST_IN_CART = "itemList";
    public final static String ATTRIBUTE_EMPTY_CART_ERROR = "emptyCartError";
    public final static String ATTRIBUTE_ADD_TO_CART_ERROR = "addToCartError";
    public final static String ATTRIBUTE_BLOCK_USER = "blockUser";
    public final static String ATTRIBUTE_BLOCK_USER_ERROR = "blockUserError";
    public final static String ATTRIBUTE_PASSWORD_MATCH = "passwordMatch";
    public final static String ATTRIBUTE_LOGIN_INCORRECT = "incorrectLogin";
    public final static String ATTRIBUTE_PASSWORD_INCORRECT = "incorrectPassword";
    public final static String ATTRIBUTE_FIRST_NAME_INCORRECT = "incorrectFirstName";
    public final static String ATTRIBUTE_LAST_NAME_INCORRECT = "incorrectLastName";
    public final static String ATTRIBUTE_ADDRESS_INCORRECT = "incorrectAddress";
    public final static String ATTRIBUTE_TELEPHONE_NUMBER_INCORRECT = "incorrectTelephoneNumber";
    public final static String ATTRIBUTE_CLEANING_NAME_INCORRECT = "incorrectName";
    public final static String ATTRIBUTE_CLEANING_PRICE_INCORRECT = "incorrectPrice";
    public final static String ATTRIBUTE_CLEANING_QUANTITY_INCORRECT = "incorrectQuantity";
    public final static String ATTR_CLEANING_TYPE_INCORRECT = "incorrectType";
    public final static String ATTR_CLEANING_DESCRIPTION_INCORRECT = "incorrectDescription";

    public final static String ATTRIBUTE_USER_IS_BLOCKED_ERROR = "blockedUserError";
    public final static String ATTRIBUTE_EDIT_PROFILE_ERROR = "editProfileError";
    public final static String ATTRIBUTE_ORDER_ERROR = "orderError";
    public final static String ATTRIBUTE_PAYMENT_TYPE_ERROR = "paymentTypeError";
    public final static String ATTRIBUTE_DATE_ERROR = "dateError";
    public final static String ATTRIBUTE_TEXT_ERROR = "textError";
    public final static String ATTRIBUTE_PASSWORD_CHANGE_SUCCESS = "changeSuccess";
    public final static String ATTRIBUTE_PASSWORD_RECOVER_SUCCESS = "recoverSuccess";
    public final static String ATTRIBUTE_PASSWORD_RECOVER_NO_LOGIN = "recoverNoLogin";
    public final static String ATTRIBUTE_PASSWORD_RECOVER_ERROR = "recoverError";
    public final static String ATTRIBUTE_ORDER_CANCEL_ERROR = "orderCancelError";
    public final static String ATTRIBUTE_CLEANING_STATUS_ERROR = "cleaningStatusError";
    public final static String ATTRIBUTE_CLEANING_EDIT_ERROR = "cleaningEditError";
    public final static String ATTRIBUTE_START = "start";
    public final static String ATTRIBUTE_PAGE_PATH = "pagePath";

    // jsp path
    public final static String JSP_MAIN = "path.page.main";
    public final static String JSP_LOGIN = "path.page.login";
    public final static String JSP_ADMIN_CABINET = "path.page.adminCabinet";
    public final static String JSP_CLEANER_CABINET = "path.page.cleanerCabinet";
    public final static String JSP_CLEANER_PROFILE = "path.page.cleanerProfile";
    public final static String JSP_CLIENT_CABINET = "path.page.clientCabinet";
    public final static String JSP_REGISTRATION = "path.page.registration";
    public final static String JSP_ERROR = "path.page.error";
    public final static String JSP_SHOW_CLIENT = "path.page.showClients";
    public final static String JSP_SHOW_CLEANING = "path.page.showCleaning";
    public final static String JSP_ADD_CLEANING = "path.page.addCleaning";
    public final static String JSP_ADD_CLEANER = "path.page.addCleaner";
    public final static String JSP_SHOW_CLEANERS = "path.page.showCleaners";
    public final static String JSP_SHOW_SINGLE_CLEANING = "path.page.showSingleCleaning";
    public final static String JSP_GO_TO_CLEANING_LIST = "path.page.goToOrderPreview";
    public final static String JSP_SHOW_BLOCKED_CLIENTS = "path.page.showBlockedClients";
    public final static String JSP_SHOW_BLOCKED_CLEANERS = "path.page.showBlockedCleaners";
    public final static String JSP_SHOW_ORDERS = "path.page.showOrders";
    public final static String JSP_SHOW_CLEANER_ORDERS = "path.page.cleanerOrders";
    public final static String JSP_CLIENT_PROFILE = "path.page.clientProfile";
    public final static String JSP_CHECK_OUT = "path.page.order";
    public final static String JSP_PASSWORD_CHANGE = "path.page.changePassword";
    public final static String JSP_PASSWORD_RECOVER = "path.page.recoverPassword";
    public final static String JSP_CLIENT_ORDERS = "path.page.clientOrders";
    public final static String JSP_CLEANER_CLEANINGS = "path.page.CleanerCleaning";
    //  public final static String JSP_FILE_ = "path.page.CleanerCleaning";
    public final static String JSP_CLEANING_PROFILE = "path.page.CleaningProfile";

    // Messages
    public final static String MESSAGE_LOGIN_ERROR = "message.loginError";
    public final static String MESSAGE_NULL_PAGE = "message.nullPage";
    public final static String MESSAGE_WRONG_ACTION = "message.wrongAction";
    public final static String MESSAGE_REGISTRATION_ERROR = "message.registrationError";
    public final static String MESSAGE_ORDER_ERROR = "message.orderError";
    public final static String MESSAGE_EDIT_PROFILE_ERROR = "message.editProfileError";
    public final static String MESSAGE_INCORRECT_LOGIN = "message.incorrectLogin";
    public final static String MESSAGE_INCORRECT_PASSWORD = "message.incorrectPassword";
    public final static String MESSAGE_PASSWORDS_NOT_EQUAL = "message.passwordNotEqual";
    public final static String MESSAGE_INCORRECT_STRING = "message.incorrectString";
    public final static String MESSAGE_INCORRECT_PAYMENT_TYPE = "message.incorrectPaymentType";
    public final static String MESSAGE_INCORRECT_TEXT = "message.incorrectText";
    public final static String MESSAGE_INCORRECT_PRICE = "message.incorrectCleaningPrice";
    public final static String MESSAGE_INCORRECT_CLEANING_QUANTITY = "message.incorrectCleaningQuantity";
    public final static String MESSAGE_ADD_CLEANING_ERROR = "message.addingCleaningError";
    public final static String MESSAGE_VALIDATE_CLEANING_ERROR = "message.validateCleaningError";
    public final static String MESSAGE_SHOW_CLEANER_ERROR = "message.showCleanerError";
    public final static String MESSAGE_SHOW_ORDER_ERROR = "message.showOrderError";
    public final static String MESSAGE_SHOW_CLIENT_ERROR = "message.showClientError";
    public final static String MESSAGE_SHOW_CLEANING_ERROR = "message.showCleaningError";
    public final static String MESSAGE_NOT_UNIQ_LOGIN_ERROR = "message.notUniqLogin";
    public final static String MESSAGE_EMPTY_CART = "message.emptyCart";
    public final static String MESSAGE_ADD_TO_ORDER_LIST_ERROR = "message.addToCartError";
    public final static String MESSAGE_BLOCK_USER = "message.blockUser";
    public final static String MESSAGE_BLOCKING_ERROR = "message.blockingError";
    public final static String MESSAGE_INCORRECT_INPUT_DATA = "message.incorrectInputData";
    public final static String MESSAGE_BLOCKED_USER_ERROR = "message.blockedUserError";
    public final static String MESSAGE_DATE_ERROR = "message.dateError";
    public final static String MESSAGE_TEXT_ERROR = "message.textError";
    public final static String MESSAGE_CURRENT_PASSWORD_ERROR = "message.currentPasswordError";
    public final static String MESSAGE_PASSWORD_CHANGE_ERROR = "message.changePasswordError";
    public final static String MESSAGE_PASSWORD_CHANGE_SUCCESS = "message.changePasswordSuccess";
    public final static String MESSAGE_PASSWORD_RECOVER_SUCCESS = "message.recoverPasswordSuccess";
    public final static String MESSAGE_PASSWORD_RECOVER_NO_LOGIN = "message.recoverPasswordNoLogin";
    public final static String MESSAGE_PASSWORD_RECOVER_ERROR = "message.recoverPasswordError";
    public final static String MESSAGE_ORDER_CANCEL_ERROR = "message.orderCancelError";
    public final static String MESSAGE_CLEANING_STATUS_ERROR = "message.cleaningStatusError";
    public final static String MESSAGE_CLEANING_EDIT_ERROR = "message.cleaningEditError";
    public final static String MESSAGE_PROFILE_SHOW_ERROR = "message.profileShowError";
    public final static String MESSAGE_CLEANING_SHOW_ERROR = "message.cleaningShowError";
    //email constants
    public final static String SUBJECT_PASSWORD_RECOVER = "message.changePasswordSuccess";
    public final static String SUBJECT_NEW_ORDER = "message.newOrderSubject";
    public final static String SUBJECT_SUCCESSFUL_REGISTRATION = "message.changePasswordSuccess";
    public final static String EMAIL_PASSWORD_RECOVER = "your temporary password = 2016-Om-7";
    public final static String EMAIL_TEMPORARY_PASSWORD = "2016-Om-7";
    public final static String EMAIL_NEW_ORDER = "message.newOrderText";
}


