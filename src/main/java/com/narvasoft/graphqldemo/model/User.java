package com.narvasoft.graphqldemo.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id", description = "A User's id")

    private Long id;
    @GraphQLQuery(name = "nombre", description = "A User's nombre")
    private String nombre;
    @GraphQLQuery(name = "apellido", description = "A User's apellido")
    private String apellido;
    @GraphQLQuery(name = "email", description = "A User's email")
    private String email;

    public User() {

    }

    public User(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    public User(Long id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +

                '}';
    }
}


