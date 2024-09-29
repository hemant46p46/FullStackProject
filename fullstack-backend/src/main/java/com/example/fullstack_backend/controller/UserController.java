package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.exception.UserNotFoundException;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fullstack2")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @RequestMapping("/hello")
    String Hello(){
        return "Hello FUllStack";
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        System.out.println("New User Created");
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(user->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());

                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Integer id) throws UserNotFoundException {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with ID:" + id + " has been deleted";
    }
}
