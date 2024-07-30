package org.example.spring_mvc_hibernate_crud.service;

import org.example.spring_mvc_hibernate_crud.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    void updateUser(User user, int id);

    void deleteUserById(int id);

    User getById(int id);

    List<User> getUserByEmail(String email);
}
