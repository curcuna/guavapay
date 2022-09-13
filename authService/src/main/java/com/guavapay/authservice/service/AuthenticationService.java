package com.guavapay.authservice.service;

import com.guavapay.authservice.exception.DuplicateUsernameException;
import com.guavapay.authservice.exception.UnknownException;
import com.guavapay.authservice.model.dto.request.LoginRequest;
import com.guavapay.authservice.model.dto.request.SignupRequest;
import com.guavapay.authservice.model.dto.request.ValidateTokenRequest;
import com.guavapay.authservice.model.dto.response.LoginResponse;
import com.guavapay.authservice.model.dto.response.ValidateTokenResponse;
import com.guavapay.authservice.model.entity.Person;
import com.guavapay.authservice.model.entity.Role;
import com.guavapay.authservice.repository.UserRepository;
import com.guavapay.authservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity signup(SignupRequest signupRequest) {
        System.out.println("**********************************" + signupRequest.getUsername() );
        Person checkUsername = userRepository.findByUsername(signupRequest.getUsername());
        if(checkUsername != null){
            throw new DuplicateUsernameException("username already exists");
        }
        Person newPerson = Person.builder().username(signupRequest.getUsername()).password(signupRequest.getPassword()).build();
        newPerson.getRoles().add(Role.ROLE_USER);
        Person person = userRepository.save(newPerson);
        if(person != null){
            return ResponseEntity.ok().build();
        }
        throw new UnknownException("unknown exception is occured");
    }

    public ResponseEntity createCourier(SignupRequest signupRequest) {
        Person checkUsername = userRepository.findByUsername(signupRequest.getUsername());
        if(checkUsername != null){
            throw new DuplicateUsernameException("username already exists");
        }
        Person newPerson = Person.builder().username(signupRequest.getUsername()).password(signupRequest.getPassword()).build();
        newPerson.getRoles().add(Role.ROLE_COURIER);
        Person person = userRepository.save(newPerson);
        if(person != null){
            return ResponseEntity.ok().build();
        }
        throw new UnknownException("unknown exception is occured");
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        System.out.println("***************l*******************" + loginRequest.getUsername() );
        LoginResponse response = new LoginResponse();
        Person person = userRepository.retrieveByUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(person != null){
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            if(token != null){
                response.setToken(token);
                response.setPerson(person);
            }
        }
        return response;
    }

    public ValidateTokenResponse validateJwt(String authorizationHeader) {
        ValidateTokenResponse response = new ValidateTokenResponse();
        String username = jwtUtil.validateToken(authorizationHeader);
        if(username != null){
            Person person = userRepository.findByUsername(username);
            response.setRoleSet(person.getRoles());
        }
        return response;
    }

    public ResponseEntity getAllCouriers() {
        List<Person> courierList = userRepository.findAllByRoles(Role.ROLE_COURIER);
        return ResponseEntity.ok().body(courierList);
    }
}
