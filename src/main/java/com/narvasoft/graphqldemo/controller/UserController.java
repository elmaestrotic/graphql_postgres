package com.narvasoft.graphqldemo.controller;

import com.narvasoft.graphqldemo.model.User;
import com.narvasoft.graphqldemo.repository.UserRepository;
import com.narvasoft.graphqldemo.service.UserService;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//@ComponentScan({"com.narvasoft.graphqldemo"})
@EntityScan("com.narvasoft.graphqldemo")
@EnableJpaRepositories("com.narvasoft.graphqldemo.repository")
@GraphQLApi
@RestController
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private UserRepository userRepository;

    @QueryMapping(name = "users")
    public List<User> getUsers() {
        return userservice.getUsers();
    }

    @QueryMapping(name = "user")
    public Optional<User> getUser(@Argument(name = "id") Long id) {
        return userservice.getUser(id);
    }

    /*ejemplo de filter con streams
    @BatchMapping(typeName = "usuarios")

    public List<User> getUsuarios(List<String> usuarios) {
        return userservice.getUsers().stream().filter(user -> usuarios.contains(user.getNombre())).collect(Collectors.toList());
    } */

    @MutationMapping(name = "createUser")
    public User createUser(@Argument String nombre, @Argument String apellido, @Argument String email) {
        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail(email);

        return userservice.createUser(user.getNombre(), user.getApellido(), user.getEmail());
    }


    @MutationMapping(name = "updateUser")
    public User updateUser(@Argument Long id, @Argument String nombre, @Argument String apellido, @Argument String email) {
        return userservice.updateUser(id, nombre, apellido, email);
    }


    @MutationMapping(name = "deleteUser")//DELETE
    public User deleteUser(@Argument(name = "id") Long id) {
        return userservice.deleteUser(id);
    }
}