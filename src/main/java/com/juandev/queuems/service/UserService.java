package com.juandev.queuems.service;

import com.juandev.queuems.Exception.GetUserNoFoundException;
import com.juandev.queuems.model.User;
import com.juandev.queuems.repository.UserRepository;
import com.juandev.queuems.util.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Response saveUser(User user) {
        //Validar si ya existe la identityCard en la tabla Users.
        if (userRepository.findByIdentityCard(user.getIdentityCard()).isPresent()){
            return new Response("El usuario con numero de identificacion "+user.getIdentityCard()+" ya se encuentra registrado.", null);
        } else {
            //Validar si ya existe el username en la tabla Users.
            if (userRepository.findByUsername(user.getUsername()) != null){
                return new Response("El nombre de usuario "+user.getUsername()+" ya esta en uso.", null);
            } else {
                //Crear usuario tabla Users
                User newUser = userRepository.save(user);
                return new Response("El usuario se creo correctamente.", newUser);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = userRepository.findAll();
        if (!listUsers.isEmpty()){
            return listUsers;
        } else {
            throw new GetUserNoFoundException("Aun no existen usuarios en la base de datos");
        }
    }


    public Optional<User> findByIdentityCard(String identityCard) {
        Optional<User> user = userRepository.findByIdentityCard(identityCard);
        if (user.isPresent()){
            return user;
        } else {
            throw new GetUserNoFoundException("El usuario con numero de identificacion "+identityCard+" no esta en la base de datos");
        }
    }

    @Transactional
    public void inactivateUser(String identityCard) {
        Optional<User> user = userRepository.findByIdentityCard(identityCard);
        user.ifPresent(value -> value.setActive(false));
        userRepository.save(user.get());
    }

    @Transactional
    public User updateUser(User user) {
        if (userRepository.findById(user.getUserId()).isPresent()){
            return userRepository.save(user);
        } else {
            throw new GetUserNoFoundException("El usuario no existe en la base de datos");
        }
    }
}