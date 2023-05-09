package com.narvasoft.graphqldemo.service;

import com.narvasoft.graphqldemo.model.User;
import com.narvasoft.graphqldemo.repository.UserRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//import static org.hibernate.id.enhanced.StandardOptimizerDescriptor.log;

@Service
@GraphQLApi
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GraphQLQuery(name = "users")//READ ALL
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GraphQLQuery(name = "user")//READ ONE BY ID
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @MutationMapping(name = "createUser") //CREATE
    public User createUser(
            @Argument String nombre,
            @Argument String apellido,
            @Argument String email){
        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User updateUser(Long id, String nombre, String apellido, String email) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setEmail(email);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("No se encontró ningún usuario con el id especificado: " + id);
        }
    }

    @GraphQLMutation(name = "deleteUser")//DELETE
    public User deleteUser(@GraphQLArgument(name = "id") Long id) {

        userRepository.deleteById(id);
        return user;
    }

}