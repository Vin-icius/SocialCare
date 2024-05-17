package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.User;
import br.unoeste.fipp.socialcare.DataBase.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    private userRepository userRepo;

    public User addUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Login already exists");
        }
        return userRepo.save(user);
    }

    public boolean deleteById(Long id) {
        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }
}