package com.vitkovskaya.finalProject.entity;

public class User extends Entity {
    private UserRole userRole;
    private long userId;
    private String login;
    private String password;
    private boolean isActive;
    private String avatarPath;
    public User() {
    }
    public User(UserRole userRole, String login, String password, boolean isActive) {
        this.userRole = userRole;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
    }
    public User(UserRole userRole, long id, String login, String password, boolean isActive) {
        this.userRole = userRole;
        this.userId = id;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
    }
    public User(UserRole userRole, long id, String login, String password, boolean isActive, String avatarPath) {
        this.userRole = userRole;
        this.userId = id;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
        this.avatarPath = avatarPath;
    }
    public User(UserRole userRole, String login, String password, boolean isActive, String avatarPath) {
        this.userRole = userRole;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
        this.avatarPath = avatarPath;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean active) {
        isActive = active;
    }
    public String getAvatarPath() {
        return avatarPath;
    }
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (userId != user.userId) {
            return false;
        }
        if (isActive != user.isActive) {
            return false;
        }
        if (userRole != user.userRole) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        return avatarPath != null ? avatarPath.equals(user.avatarPath) : user.avatarPath == null;
    }
    @Override
    public int hashCode() {
        int result = userRole != null ? userRole.hashCode() : 0;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (avatarPath != null ? avatarPath.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User: ");
        builder.append("userRole: ").append(userRole);
        builder.append(", userId: ").append(userId);
        builder.append(", login: ").append(login);
        builder.append(", password: ").append(password);
        builder.append(", isActive: ").append(isActive);
        builder.append(", avatarPath: ").append(avatarPath);
        return builder.toString();
    }
}
