package com.neu.his.bean;

public class LoginUserBean {
    private int userId;
    private String userPsw;

    public LoginUserBean(int userId, String userPsw) {
        this.userId = userId;
        this.userPsw = userPsw;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }
}
