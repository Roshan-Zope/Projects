package com.expensetracker.models.services;

import com.expensetracker.models.Repository.UserRepository;
import com.expensetracker.models.entities.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public ObjectId createUser(User user) {
        return userRepository.save(user);
    }

    public Document getUser(String username, String passkey) {
        return userRepository.find(username, passkey);
    }

    public void update(String newPassword) {
        userRepository.update(newPassword);
    }

    public void delete(ObjectId id) {
        userRepository.delete(id);
    }
}
