package com.microservice.hruser.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;

    //usamos o set pra fazer a associação das roles pois não poderá repetir regra no mesmo usuario, Colectio set faz isso
    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(//criando a tabela de relação com a tabela da classe roles
         name ="tb_user_role", //nome da tabela
         // definição das chaves estrangeiras:
         joinColumns = @JoinColumn(name= "user_id"), //definição da chave que referencia a tabela user com o nome da coluna
         inverseJoinColumns = @JoinColumn(name= "role_id")//definição da chave que referencia a tabela roles com o nome da coluna
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String nome, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
