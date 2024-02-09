package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.GetUserNoFoundException;
import com.juandev.queuems.Exception.InactiveUserTrue;
import com.juandev.queuems.model.Patient;
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
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(new Response("Se actualizo el registro correctamente", updatedUser), HttpStatus.OK);
        } catch (GetUserNoFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConflictIdentityCardException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
