package com.example.clientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera automaticamente el id incremental
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @Column(unique = true)  //no pueden haber dos clientes con un mismo correo
    @Email(message = "El Email no es valido")
    @NotBlank(message = "El Email es obligatorio")
    private String email;
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer age;
    private Boolean active = true;

    // getters y setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // contructores


    public Cliente() {
    }

    public Cliente(Long id, String nombre, String email, Integer age, Boolean active) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.age = age;
        this.active = active;
    }
}
