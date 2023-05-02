package com.narvasoft.graphqldemo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class UserService {
    private final UserRepository userRepository;
    private User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GraphQLQuery(name = "users")//READ ALL
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GraphQLQuery(name = "user")//READ ONE BY ID
    public Optional<User> getUser(@GraphQLArgument(name = "id") Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }

    @GraphQLMutation(name = "createUser")//CREATE
    public User createUser(@GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
        user= new User();
        return userRepository.save(user);
    }

    @GraphQLMutation(name = "updateUser")//UPDATE
    public User updateUser(@GraphQLArgument(name = "id") Long id,
                           @GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
        user= new User();
        return userRepository.save(user);
    }

    @GraphQLMutation(name = "deleteUser")//DELETE
    public void deleteUser(@GraphQLArgument(name = "id") Long id) {
        userRepository.deleteById(id);
    }

}
