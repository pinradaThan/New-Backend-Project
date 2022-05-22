package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;

    // requesting all user's info
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        for(User user: repo.findAll()) {
            list.add(user);
        }
        return list;
    }

    // requesting user info by ID
    public User getUser(int id) { return repo.findById(id).get(); }

    // adding user
    public User addUser(User listData) {
        return repo.save(listData);
    }

    // updating user (PUT)
    // if ID not found insert (with next-in sequence ID)
    public User updateUser(int id, User user) {
        User updatedUser = new User();
        updatedUser.setUser_id(id);
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setTelephone(user.getTelephone());
        return repo.save(updatedUser);
    }
    // updating user (PATCH)
    public User patchUser(int id, User user) throws RuntimeException {

      User patchedUser = repo.findById(id).orElseThrow(RuntimeException::new);
        boolean needUpdate = false;
        // checking new info
        try {
            if ((user.getName()).length() > 0) {
                patchedUser.setName(user.getName());
                needUpdate = true;
            }
            if ((user.getEmail()).length() > 0) {
                patchedUser.setEmail(user.getEmail());
                needUpdate = true;
            }
            if ((user.getTelephone()).length() > 0){
                patchedUser.setTelephone(user.getTelephone());
                needUpdate = true;
            }
        }
        catch (Exception e) {
            try {
                if ((user.getEmail()).length() > 0) {
                    patchedUser.setEmail(user.getEmail());
                    needUpdate = true;
                }
                if ((user.getTelephone()).length() > 0){
                    patchedUser.setTelephone(user.getTelephone());
                    needUpdate = true;
                }
            }
            catch (Exception e2) {
                try {
                    if ((user.getTelephone()).length() > 0){
                        patchedUser.setTelephone(user.getTelephone());
                        needUpdate = true;
                    }
                }
               catch (Exception e3){
                    needUpdate = false;
                }
            }
        }

        // check if user needs update
        if (needUpdate) {
            return repo.save(patchedUser);
        }
        else {
            return patchedUser;
        }
    }

    // deleting user by id
    public void deleteUser(int id) { repo.deleteById(id); }
}


