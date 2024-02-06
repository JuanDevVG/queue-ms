package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.GetUserNoFoundException;
import com.juandev.queuems.Exception.InactiveUserTrue;
import com.juandev.queuems.model.User;
import com.juandev.queuems.service.UserService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody User user){
        Response response = userService.saveUser(user);
        if ( response.getNewObject() != null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        try {
            return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
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

    @PutMapping("/inactivate/{identityCard}")
    public ResponseEntity<?> inactivateUser(@PathVariable String identityCard){

        try {
            userService.inactivateUser(identityCard);
            return new ResponseEntity<>("El usuario se inactivo correctamente", HttpStatus.OK);
        } catch (InactiveUserTrue e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (GetUserNoFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            return new ResponseEntity<>("Usuario modificado correctamente.", HttpStatus.OK);
        } catch (GetUserNoFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
