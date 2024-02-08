package com.example.projetjee.controllers;

import com.example.projetjee.models.User;
import com.example.projetjee.utils.DataStorage;

public class AdminController {
    DataStorage dataStorage = DataStorage.getInstance();

    public User login(String username, String password) {
        User user = dataStorage.loginUser(username, password);

        return user;
    }
}
