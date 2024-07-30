package org.example.spring_mvc_hibernate_crud.repository;


import org.example.spring_mvc_hibernate_crud.entity.User;

import java.util.List;

public interface UserRepository {


    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    List<User> getUserByEmail(String email);

    void deleteUserById(int id);
}
