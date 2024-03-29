package com.example.projetjee.utils;

import com.example.projetjee.models.Admin;
import com.example.projetjee.models.User;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {

    private static DataStorage instance;
    private Map<String, User> users;


    private DataStorage() {
        users = new HashMap<>();


        // Create a default user
        User admin = new Admin("admin", "admin");
        users.put(admin.getUsername(), admin);
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void updateUser(User user) {
        users.put(user.getUsername(), user);
    }
    public User loginUser(String username, String password) {
        User user = getUser(username);

        if(user == null)
            return null;

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }



    public void changePassword(String username, String newPassword) {
        getUser(username).setPassword(newPassword);
    }
}
