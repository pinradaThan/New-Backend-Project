package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServices service;

    @RequestMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<User> getUsers = service.getUsers();
        return new ResponseEntity(getUsers, HttpStatus.OK);
    }

    @RequestMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        User user = service.getUser(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        // checking if all info is given
        if(user.getName() == null || user.getEmail() == null
                || user.getTelephone() == null) {
            return new ResponseEntity("Unprocessable Entity Error: 402",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        User updatedUser = service.updateUser(id, user);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User listData) {
        // checking if all info is given
        if(listData.getName() == null || listData.getEmail() == null
                || listData.getTelephone() == null) {
            return new ResponseEntity("Unprocessable Entity Error: 402",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        User addedUser = service.addUser(listData);
        return new ResponseEntity(addedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) { service.deleteUser(id); }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?>  patchUser(@PathVariable int id, @RequestBody User user) {
        // does not need to check if all info is given since its a partial update
        // try checks if user exists
        try {
            User patchedUser = service.patchUser(id, user);
            return new ResponseEntity(patchedUser, HttpStatus.OK);
        }
        // if user with this ID does not exist:
        catch(RuntimeException e) {
            return new ResponseEntity("Unprocessable Entity Error: 402",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
