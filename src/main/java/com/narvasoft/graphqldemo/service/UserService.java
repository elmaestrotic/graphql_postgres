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

    //@Autowired
    //private UserService userservice;
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
/*
    @GraphQLMutation(name = "createUser")//CREATE
     public User createUser(@GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
      var user = new User(nombre, apellido, email);
        //return userRepository.save(user);

        //user.setNombre(nombre);
        //user.setApellido(apellido);
        //user.setEmail(email);


       UserService userservice = new UserService(userRepository);//sino queda null el objeto userservice
       // userservice.createUser(user.getNombre(), user.getApellido(), user.getEmail());
        //userservice.createUser(nombre, apellido,email);
        return userRepository.save(user);
    }*/

    @MutationMapping(name = "createUser")
    public User createUser(
            @Argument String nombre,
            @Argument String apellido,
            @Argument String email){
        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail(email);
                //@Argument Double price) {
        //log.info("Saving book, name {}", name);
        //User user = new  User(nombre, apellido, email);
        return userRepository.save(user);
    }

/*
    @GraphQLMutation(name = "createUser")//CREATE
    public User createUser(@GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
        // 1. Crear una instancia del objeto "User" con los datos proporcionados como argumentos.
        User user = new User(nombre, apellido, email);

        // 2. Persistir la instancia del objeto "User" en la base de datos o en cualquier otro medio de almacenamiento permanente.
        userService.save(user);

        // 3. Devolver la instancia del objeto "User" creado como resultado de la mutación.
        return user;
    }*/



    //@ExceptionHandler(Exception.class)

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
