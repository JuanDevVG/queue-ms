package com.juandev.queuems.controller;

import com.juandev.queuems.model.User;
import com.juandev.queuems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        User saveUser = userService.saveUser(user);

        return ResponseEntity.ok("Usuario "+saveUser.getUsername()+" creado con exito");
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUsers(){
        List<User> listUsers = userService.listUsers();

        if (!listUsers.isEmpty()) {
            return ResponseEntity.ok(listUsers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/get/{identityCard}")
    public ResponseEntity<User> getUserByIdentityCard(@PathVariable String identityCard){
        Optional<User> user = userService.findByIdentityCard(identityCard);

        if (user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/inactivate/{identityCard}")
    public ResponseEntity<String> inactivateUser(@PathVariable String identityCard){
        User userInactivate = userService.inactivateUser(identityCard);
        if (userInactivate != null){
            return new ResponseEntity<>("El usuario "+userInactivate.getUsername()+" fue desactivado",HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
