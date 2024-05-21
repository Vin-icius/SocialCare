package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usu_id")
    private Long id;
    @Column(name="usu_email")
    private String email;
    @Column(name="usu_senha")
    private String password;
    @Column(name="usu_nivel")
    private int level;
    @Column(name="usu_ativo")
    private boolean active;

    //Foreign Key
    /*@ManyToOne
    @JoinColumn(name="fisica_pessoa_pes_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private FisicalPerson fisicalPerson;*/

    public User() {
        this(0L,"","",0);
        this.active = true;
    }

    public User(Long id, String email, String password, int level) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.level = level;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPassword(String senha) {
        this.password = senha;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int nivel) {
        this.level = nivel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /*public FisicalPerson getFisicalPerson() {
        return fisicalPerson;
    }

    public void setFisicalPerson(FisicalPerson fisicalPerson) {
        this.fisicalPerson = fisicalPerson;
    }*/

    public boolean hasFullAccess() {
        return this.level == 1;
    }
}