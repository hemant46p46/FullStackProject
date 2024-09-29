package com.example.fullstack_backend.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(int id){
        super("Could Not Found User with ID of:" + id);
    }
}
