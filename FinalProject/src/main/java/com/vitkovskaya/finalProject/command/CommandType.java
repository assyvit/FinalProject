package com.vitkovskaya.finalProject.command;

import com.vitkovskaya.finalProject.command.admin.*;
import com.vitkovskaya.finalProject.command.cleaner.*;
import com.vitkovskaya.finalProject.command.cleaning.EditCleaningCommand;
import com.vitkovskaya.finalProject.command.cleaning.GoCleaningProfileCommand;
import com.vitkovskaya.finalProject.command.cleaning.ShowCatalogCommand;
import com.vitkovskaya.finalProject.command.client.*;
import com.vitkovskaya.finalProject.command.common.*;
import com.vitkovskaya.finalProject.command.order.*;

public enum CommandType {

    // Common commands
    CHANGE_LANGUAGE {{
        this.command = new LanguageCommand();
    }},
    REGISTRATION {{
        this.command = new RegistrationCommand();
    }},
    LOGIN {{
        this.command = new LoginCommand();
    }},
    RECOVER_PASSWORD {{
        this.command = new RecoverPasswordCommand();
    }},
    ADD_CLEANER {{
        this.command = new AddCleanerCommand();
    }},
    GO_TO_MAIN {{
        this.command = new MainCommand();
    }},
    SHOW_CATALOG {{
        this.command = new ShowCatalogCommand();
    }},


    //for users
    UPLOAD_IMAGE {{
        this.command = new UploadImageCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    CHANGE_PASSWORD {{
        this.command = new ChangePasswordCommand();
    }},
    GO_TO_CABINET {{
        this.command = new GoToCabinetCommand();
    }},
    GO_TO_CHANGE_PASSWORD {{
        this.command = new GoToChangePasswordCommand();
    }},

    //Admin commands
    SHOW_CLIENTS {{
        this.command = new ShowAllClientsCommand();
    }},
    SHOW_CLEANERS {{
        this.command = new ShowAllCleanersCommand();
    }},
    SHOW_ORDERS {{
        this.command = new ShowOrdersCommand();
    }},
    SEND_EMAIL {{
        this.command = new SendEmailCommand();
    }},
    CHANGE_USER_STATUS {{
        this.command = new ChangeUserStatusCommand();
    }},
    SHOW_BLOCKED_CLEANERS {{
        this.command = new ShowBlockedCleanersCommand();
    }},
    SHOW_BLOCKED_CLIENTS {{
        this.command = new ShowBlockedClientsCommand();
    }},


    //Client commands
    CONFIRM_ORDER {{
        this.command = new OrderCommand();
    }},
    ADD_TO_ORDER_LIST {{
        this.command = new AddToOrderListCommand();
    }},
    REMOVE_FROM_ORDER_LIST {{
        this.command = new RemoveFromOrderListCommand();
    }},
    GO_TO_ORDER_PREVIEW {{
        this.command = new GoToOrderPreviewCommand();
    }},
    CLEAR_ORDER_LIST {{
        this.command = new ClearOrderListCommand();
    }},
    GO_CLIENT_PROFILE {{
        this.command = new GoClientProfileCommand();
    }},
    EDIT_CLIENT_PROFILE {{
        this.command = new EditClientProfileCommand();
    }},
    SHOW_CLIENT_ORDERS {{
        this.command = new ShowClientOrdersCommand();
    }},
    CANCEL_ORDER {{
        this.command = new CancelOrderCommand();
    }},
    GO_TO_ORDER {{
        this.command = new GoToOrderCommand();
    }},

    //Cleaner commands
    ADD_CLEANING {{
        this.command = new AddCleaningCommand();
    }},
    SHOW_CLEANER_ORDERS {{
        this.command = new ShowCleanerOrdersCommand();
    }},
    EDIT_CLEANER_PROFILE {{
        this.command = new EditCleanerProfileCommand();
    }},
    GO_CLEANER_PROFILE {{
        this.command = new GoCleanerProfileCommand();
    }},
    EDIT_CLEANING {{
        this.command = new EditCleaningCommand();
    }},
    GO_CLEANING_PROFILE {{
        this.command = new GoCleaningProfileCommand();
    }},
    SHOW_CLEANER_CLEANINGS {{
        this.command = new ShowCleanerCleaningCommand();
    }},
    CHANGE_ORDER_STATUS {{
        this.command = new ChangeOrderStatusCommand();
    }},
    CHANGE_CLEANING_STATUS {{
        this.command = new ChangeCleaningStatusCommand();
    }},
    CONFIRM_PAYMENT {{
        this.command = new ConfirmPaymentCommand();
    }},
    GO_TO_ADD_CLEANING {{
        this.command = new GoToAddCleaning();
    }};


    Command command;
    public Command getCurrentCommand() {
        return command;
    }

}
