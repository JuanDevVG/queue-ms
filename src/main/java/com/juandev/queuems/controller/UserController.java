package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.*;
import com.juandev.queuems.dto.UserDTO;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.User;
import com.juandev.queuems.service.UserService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        try {
            userService.saveUser(userDTO);
            return new ResponseEntity<>("El usuario se creo correctamente", HttpStatus.CREATED);
        } catch (ConflictIdentityCardException | ConflictUsernameException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (GetUserNoFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{identityCard}")
    public ResponseEntity<?> getUserByIdentityCard(@PathVariable String identityCard){
        try {
            return new ResponseEntity<>(userService.findByIdentityCard(identityCard), HttpStatus.OK);
        } catch (GetUserNoFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        try {
            userService.updateUser(userDTO);
            return new ResponseEntity<>("Se actualizo el registro correctamente", HttpStatus.OK);
        } catch (GetUserNoFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConflictIdentityCardException | ConflictUsernameException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
