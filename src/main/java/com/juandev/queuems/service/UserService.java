package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.Exception.ConflictUsernameException;
import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.GetUserNoFoundException;
import com.juandev.queuems.dto.PatientDTO;
import com.juandev.queuems.dto.UserDTO;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.User;
import com.juandev.queuems.repository.UserRepository;
import com.juandev.queuems.util.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByIdentityCard(userDTO.getIdentityCard());

        //Validar si ya existe la identityCard en la tabla Users.
        if (userOptional.isPresent()){
            throw new ConflictIdentityCardException("El usuario con numero de identificacion "+userDTO.getIdentityCard()+" ya se encuentra registrado");
        } else {
            //Validar si ya existe el username en la tabla Users.
            if (userRepository.findByUsername(userDTO.getUsername()).isPresent()){
                throw new ConflictUsernameException("El nombre de usuario "+userDTO.getUsername()+" ya esta en uso.");
            } else {
                //Crear usuario tabla Users
                User newUser = User.builder()
                        .identityCard(userDTO.getIdentityCard())
                        .firstName(userDTO.getFirstName())
                        .lastName(userDTO.getLastName())
                        .email(userDTO.getEmail())
                        .password(userDTO.getPassword())
                        .username(userDTO.getUsername())
                        .active(userDTO.isActive())
                        .role(userDTO.getRole())
                        .build();

                userRepository.save(newUser);
            }
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()){
            List<UserDTO> userDTOList = new ArrayList<>();

            for (User user: users){
                UserDTO userDTO = UserDTO.builder()
                        .userId(user.getUserId())
                        .identityCard(user.getIdentityCard())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .username(user.getUsername())
                        .active(user.isActive())
                        .role(user.getRole())
                        .build();

                userDTOList.add(userDTO);
            }
            return userDTOList;
        } else {
            throw new GetPatientNotFoundException("Aun no se encuentran pacientes en la base de datos");
        }
    }


    public UserDTO findByIdentityCard(String identityCard) {
        User user = userRepository.findByIdentityCard(identityCard)
                .orElseThrow(()-> new GetUserNoFoundException("El usuario no se encuentra registrado"));

        return UserDTO.builder()
                .userId(user.getUserId())
                .identityCard(user.getIdentityCard())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .active(user.isActive())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new GetPatientNotFoundException("El usuario con id: " + userDTO.getUserId()+ " No se ecuentra registrado"));

        // Verificar si la nueva identityCard ya está en uso
        Optional<User> userIdentityCard = userRepository.findByIdentityCard(user.getIdentityCard());
        //Verifica que el usuario que tiene la identityCard sea diferente al que se actualizara
        if (userIdentityCard.isPresent() && !userIdentityCard.get().getUserId().equals(user.getUserId())) {
            throw new ConflictIdentityCardException("Ya existe un usuario con numero de identidad "+user.getIdentityCard());
        }
        Optional<User> OptionalUserByUsername = userRepository.findByUsername(userDTO.getUsername());
        if (OptionalUserByUsername.isPresent() && !OptionalUserByUsername.get().getUsername().equals(userDTO.getUsername())) {
            throw new ConflictUsernameException("El nombre de usuario " + userDTO.getUsername() + " ya esta en uso.");
        }

        // Actualizar los campos del usuario existente con los nuevos datos
        user.setIdentityCard(userDTO.getIdentityCard());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setActive(userDTO.isActive());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        userRepository.save(user);
    }
}