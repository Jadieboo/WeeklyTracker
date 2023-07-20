package com.sparta.traineetracker.web.dtos;

public class AccountDTO {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "AccountDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
