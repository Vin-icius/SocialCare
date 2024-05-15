package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usu_id")
    private Long id;
    @Column(name="usu_cpf")
    private String cpf;
    @Column(name="usu_email")
    private String email;
    @Column(name="usu_senha")
    private String password;
    @Column(name="usu_nivel")
    private int nivel;

    public User() {
        this(0L,"","","",0);
    }

    public User(Long id, String cpf, String email, String password, int nivel) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}