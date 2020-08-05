package com.paico.paico_tour.object_classes;

public class UserHolder {
    private static UserHolder instance;

    private User user;

    public static UserHolder getInstance() {
        if (instance==null)
            instance=new UserHolder();
        return instance;
    }

    public static void setInstance(UserHolder instance) {
        UserHolder.instance = instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private UserHolder(){
    }
}
