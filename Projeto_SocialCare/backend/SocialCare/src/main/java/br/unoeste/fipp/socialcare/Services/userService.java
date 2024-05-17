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

    public User updateUser(User user) {
        User existingUser = userRepo.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (existingUser.hasFullAccess() && !user.isActive() && userRepo.countByFullAccessAndActive() <= 1) {
            throw new IllegalArgumentException("Cannot deactivate the last user with full access");
        }
        return userRepo.save(user);
    }

    public boolean deleteById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        if (user.hasFullAccess() && userRepo.countByFullAccessAndActive() <= 1) {
            throw new IllegalArgumentException("Cannot delete the last user with full access");
        }
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

    public User getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }
}