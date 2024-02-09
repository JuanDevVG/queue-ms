package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.GetUserNoFoundException;
import com.juandev.queuems.model.Patient;
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
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new GetPatientNotFoundException("El usuario con id: " + user.getUserId()+ " No se ecuentra registrado"));

        // Verificar si la nueva identityCard ya está en uso por otro usuario
        Optional<User> userIdentityCard = userRepository.findByIdentityCard(user.getIdentityCard());

        if (userIdentityCard.isPresent() && !userIdentityCard.get().getUserId().equals(user.getUserId())) {
            throw new ConflictIdentityCardException("Ya existe un usuario con numero de identidad "+user.getIdentityCard());
        }

        // Actualizar los campos del usuario existente con los nuevos datos
        existingUser.setIdentityCard(user.getIdentityCard());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setActive(user.isActive());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        // Guardar el usuario actualizado en la base de datos
        return userRepository.save(existingUser);
    }
}