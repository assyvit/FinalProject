package com.vitkovskaya.finalProject.command;

/**
 * The {@code Router} class
 * contains two fields -
 * page and RouteType,
 * that are used with a controller to find out where and how
 * a request and response should be processed after the controller.
 *
 */
public class Router {
    /**
     * The pagePath.
     */
    private String pagePath;
    /**
     * The type.
     */
    private RouteType type;

    public Router() {
        this.type = RouteType.FORWARD;
    }

    public Router(String pagePath, RouteType type) {
        this.pagePath = pagePath;
        this.type = type;
    }

    /**
     * Sets the pagePath.
     *
     * @param pagePath the new pagePath
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(RouteType type) {
        this.type = type;
    }

    /**
     * Gets the pagePath.
     *
     * @return the pagePath
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public RouteType getType() {
        return type;
    }
}