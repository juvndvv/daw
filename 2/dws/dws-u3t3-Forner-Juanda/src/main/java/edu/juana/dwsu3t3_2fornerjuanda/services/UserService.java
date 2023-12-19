package edu.juana.dwsu3t3_2fornerjuanda.services;

import edu.juana.dwsu3t3_2fornerjuanda.entities.User;
import edu.juana.dwsu3t3_2fornerjuanda.models.LoginForm;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private ArrayList<User> users = new ArrayList<User>();

    @PostConstruct
    public void init() {
        users.add(User.build("juanda", "jdanielforga@gmail.com", "1234", "23/11/1999"));
        users.add(User.build("jorge", "jorge@email.com", "1234", "09/08/2002"));
    }

    public User findByLoginForm(LoginForm loginUser) {
        for (User user : users) {
            if (user.equals(loginUser))
                return user;
        }

        return null;
    }

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getNombre().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
