package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;
}
