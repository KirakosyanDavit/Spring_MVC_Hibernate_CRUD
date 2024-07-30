package org.example.spring_mvc_hibernate_crud.service;

import lombok.RequiredArgsConstructor;
import org.example.spring_mvc_hibernate_crud.entity.User;
import org.example.spring_mvc_hibernate_crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        List<User> usersByEmail = getUserByEmail(user.getEmail());
        int age = user.getAge();
        if (usersByEmail == null || usersByEmail.isEmpty()) {
            if (age >= 1 && age <= 150) {
                user.setAge(age);
                userRepository.saveUser(user);
            }
        } else {
            getUserByEmailAndAge(user, usersByEmail, age);
        }
    }


    @Override
    public void updateUser(User user, int id) {
        User userById = userRepository.getUserById(id);
        List<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
        if (userById != null) {
            userById.setName(user.getName());
            userById.setSurname(user.getSurname());
            String email = user.getEmail();
            int age = user.getAge();

            if (userByEmail == null || userByEmail.isEmpty()) {
                user.setEmail(email);
                if (age >= 1 && age <= 150) {
                    user.setAge(age);
                    userRepository.saveUser(user);
                }
            } else {
                getUserByEmailAndAge(user, userByEmail, age);
            }

        } else {
            System.out.println("such ID does not exist");
        }
    }


    @Override
    public List<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public User getById(int id) {
        User userById = userRepository.getUserById(id);
        if (userById == null) {
            return null;
        }
        return userById;
    }

    private void getUserByEmailAndAge(User user, List<User> userByEmail, int age) {
        User existingUser = userByEmail.get(0);
        if (!existingUser.getEmail().equals(user.getEmail())) {
            user.setEmail(existingUser.getEmail());
            if (age >= 1 && age <= 150) {
                user.setAge(age);
                userRepository.saveUser(user);
            }
        }
    }

}
