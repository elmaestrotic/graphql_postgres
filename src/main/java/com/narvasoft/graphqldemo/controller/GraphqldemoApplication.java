package com.narvasoft.graphqldemo.controller;

import com.narvasoft.graphqldemo.model.User;
import com.narvasoft.graphqldemo.service.UserService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@GraphQLApi
@SpringBootApplication

@ComponentScan({"com.narvasoft.graphqldemo"})
@EntityScan("com.narvasoft.graphqldemo")
@EnableJpaRepositories("com.narvasoft.graphqldemo.repository")


public class GraphqldemoApplication {
    private static List<User> bbdd = new ArrayList<>();
    static AtomicLong idCounter = new AtomicLong(3L);

    @Autowired
    private UserService userservice;

    public static void main(String[] args) {
        SpringApplication.run(GraphqldemoApplication.class, args);
    }

    @QueryMapping(name= "users")
    public List<User> getUsers() {
        return userservice.getUsers();
    }

    @BatchMapping(typeName = "users")
    public List<User> getUsuarios() {
        return userservice.getUsers();
    }

    @BatchMapping(typeName = "usuarios")
    public List<User> getUsuarios(List<String> usuarios) {
        return userservice.getUsers().stream().filter(user -> usuarios.contains(user.getNombre())).collect(Collectors.toList());
    }

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
        return userservice.createUser(user.getNombre(), user.getApellido(), user.getEmail());
    }

    @MutationMapping(name = "updateUser")//UPDATE
    public User updateUser(@GraphQLArgument(name = "id") Long id,
                           @GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
        User user = new User();
        user.setId(id);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail(email);
        return userservice.updateUser(user.getId(), user.getNombre(), user.getApellido(), user.getEmail());
    }

    @MutationMapping(name = "deleteUser")//DELETE
    public void deleteUser(@GraphQLArgument(name = "id") Long id) {
       userservice.deleteUser(id);

    }


    /*private static List<User> obtenerUsuarios(User otro) {
        var listaBase = new ArrayList<>(listaDeUsuarios());
        if (null != otro)
            listaBase.add(otro);
        return listaBase;
    }

    private static List<User> listaDeUsuarios() {
        return List.of(
                new User(150L, "Levstein", "Videoarte", "mail"),
                new User(250L, "Casile", "Performance", "mail"),
                new User(350L, "Obeid", "Videoarte", "mail"));
    }
*/


  /*  @Bean
    ApplicationRunner init(UserRepository repository) {
        return args -> {
            User user = new User("", "", "");
            user.setNombre("Juan");
            user.setApellido("Perez");
            user.setEmail("jp@gmail.com");

            userservice = new UserService(repository);//sino queda null el objeto userservice
            userservice.createUser(user.getNombre(), user.getApellido(), user.getEmail());

            userservice.getUsers().forEach(System.out::println);
        };

    }*/



    /*@MutationMapping
    Mono<User> agregarUsuario(@Argument User nuevo) {
        var nuevoUser = new User(idCounter.incrementAndGet(), nuevo.getNombre(), nuevo.getApellido(), nuevo.getEmail());
        bbdd = obtenerUsuarios(nuevoUser);
        return Mono.just(nuevoUser);
    }
*/


}

