package com.vitkovskaya.finalProject.command;

public interface Command {

    /**
     * Execute.
     *
     * @param content the request
     * @return the Router
     */
    Router execute(RequestContent content);
}
